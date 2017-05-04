package be.ordina.service;

import be.ordina.model.Vending;
import jdk.nashorn.internal.ir.Block;
import oracle.jrockit.jfr.settings.EventDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Int256;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.*;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;
import org.web3j.protocol.parity.methods.response.NewAccountIdentifier;
import org.web3j.protocol.parity.methods.response.PersonalListAccounts;
import org.web3j.protocol.parity.methods.response.PersonalUnlockAccount;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import rx.Subscription;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by KeLe on 2/05/2017.
 */


@Service
public class web3jService {


    private Web3j web3; //defaults to http://localhost:8545
    private Credentials credentials;
    BigInteger gasprice = BigInteger.valueOf(150000);
    BigInteger gaslimit = BigInteger.valueOf(300000);
    Vending vendingContract;
    Parity parity;

    public web3jService() throws IOException, CipherException {
        this.web3  = Web3j.build(new HttpService());
        this.parity = Parity.build(new HttpService());
        this.credentials  = WalletUtils.loadCredentials(BlockchainLocalSettings.VENDING_PASSWORD, BlockchainLocalSettings.WALLET_MACHINE);
        vendingContract = Vending.load(BlockchainLocalSettings.VENDING_CONTRACT,web3,credentials,gasprice, gaslimit);
    }


    public String getClientVersion() throws IOException, ExecutionException, InterruptedException {

        Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        return clientVersion;

    }
    public List<String> getAccounts() throws IOException, ExecutionException, InterruptedException {

        EthAccounts accounts =  web3.ethAccounts().sendAsync().get();
        return accounts.getAccounts();
    }
    public Integer getStock() throws IOException, ExecutionException, InterruptedException, CipherException {
        Type result = vendingContract.stock().get();
        return Integer.parseInt(result.getValue().toString());
    }

    public Integer vendingStockRefill(int amount) throws IOException, ExecutionException, InterruptedException, CipherException {


        BigInteger am = BigInteger.valueOf(amount);
        TransactionReceipt transactionReceipt = vendingContract.stockUp(new Int256(am)).get();
        Type result = vendingContract.stock().get();
        return Integer.parseInt(result.getValue().toString());

    }


    public int buyOne() throws IOException, CipherException, ExecutionException, InterruptedException {
        BigInteger duration = BigInteger.valueOf(3600);//one hour
        BigInteger ether = Convert.toWei("2.0", Convert.Unit.ETHER).toBigInteger();


        //todo: check first if the accounts are locked
        //unlock accounts
        PersonalUnlockAccount cola = parity.personalUnlockAccount("0x1c6B88A198a06868D9fAB6e54056F04195CfCe8C","cola", duration).send();
        PersonalUnlockAccount ordina = parity.personalUnlockAccount("0xDEF240271e9E6b79b06f3a7C4A144D3874e512d2","ordina", duration).send();


        String transactionHash = "";
        if(cola.accountUnlocked() || ordina.accountUnlocked()){
        EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount("0x1c6B88A198a06868D9fAB6e54056F04195CfCe8C", DefaultBlockParameterName.LATEST).sendAsync().get();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        Function function = new Function("pay", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        String encodedFunction = FunctionEncoder.encode(function);
        org.web3j.protocol.core.methods.request.Transaction transaction = org.web3j.protocol.core.methods.request.Transaction.createFunctionCallTransaction("0x1c6B88A198a06868D9fAB6e54056F04195CfCe8C",nonce, gasprice, gaslimit, BlockchainLocalSettings.VENDING_CONTRACT, ether, encodedFunction);
        org.web3j.protocol.core.methods.response.EthSendTransaction transactionResponse = web3.ethSendTransaction(transaction).send();
        transactionHash = transactionResponse.getTransactionHash();
            System.out.println("TransactionHash " + transactionHash);
        }
        // is not null when submitted
        EthGetTransactionReceipt transactionReceipt = web3.ethGetTransactionReceipt(transactionHash).send();
        
        return getStock();

    }

}
