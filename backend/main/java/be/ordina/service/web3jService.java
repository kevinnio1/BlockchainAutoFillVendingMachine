package be.ordina.service;

import be.ordina.model.Vending;
import com.sun.javafx.scene.control.skin.VirtualFlow;
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
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;
import org.web3j.protocol.parity.methods.response.PersonalUnlockAccount;
import org.web3j.utils.Convert;
import rx.Subscription;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
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
    boolean minedTransaction = false;

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
        List<String> list = new ArrayList<>();
        EthAccounts accounts =  web3.ethAccounts().sendAsync().get();
        for (String s : accounts.getAccounts()) {


            BigInteger balance = web3.ethGetBalance(s,DefaultBlockParameterName.LATEST).sendAsync().get().getBalance();

            String accAndBalance = s.concat("  ").concat(Convert.fromWei(balance.toString(), Convert.Unit.ETHER).toString()).concat("ETHER");
            list.add(accAndBalance);
        }



        return list;
        //return accounts.getAccounts();
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

//todo: na deze methode aan te roepen moet de getAccounts terug aangeroepen worden in de angular en upgedate worden.
    public int buyOne() throws IOException, CipherException, ExecutionException, InterruptedException {
        if (getStock() == 0) {
            return 0;
        } else {


            BigInteger duration = BigInteger.valueOf(3600);//one hour
            BigInteger ether = Convert.toWei("2.0", Convert.Unit.ETHER).toBigInteger();


            //todo: check first if the accounts are locked
            //unlock accounts
            PersonalUnlockAccount cola = parity.personalUnlockAccount("0x1c6B88A198a06868D9fAB6e54056F04195CfCe8C", "cola", duration).sendAsync().get();
            //PersonalUnlockAccount ordina = parity.personalUnlockAccount("0xDEF240271e9E6b79b06f3a7C4A144D3874e512d2","ordina", duration).send();
            

            //String transactionHash = "";

            EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount("0x1c6B88A198a06868D9fAB6e54056F04195CfCe8C", DefaultBlockParameterName.LATEST).sendAsync().get();
            BigInteger nonce = ethGetTransactionCount.getTransactionCount();
            Function function = new Function("pay", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
            String encodedFunction = FunctionEncoder.encode(function);
            org.web3j.protocol.core.methods.request.Transaction transaction = org.web3j.protocol.core.methods.request.Transaction.createFunctionCallTransaction("0x1c6B88A198a06868D9fAB6e54056F04195CfCe8C", nonce, gasprice, gaslimit, BlockchainLocalSettings.VENDING_CONTRACT, ether, encodedFunction);
            org.web3j.protocol.core.methods.response.EthSendTransaction transactionResponse = web3.ethSendTransaction(transaction).send();
            final String transactionHash = transactionResponse.getTransactionHash();
            //System.out.println("TransactionHash " + transactionHash);

            //todo: find an more performant way to see if block is mined
            Subscription subscription = web3.blockObservable(false).subscribe(block -> {
                for (EthBlock.TransactionResult tr :
                        block.getBlock().getTransactions()) {
                    //System.out.println("Transaction: hashcode" + tr.hashCode());
                    if (tr.get().toString().equalsIgnoreCase(transactionHash)) {
                        changeMined();
                    }

                }
            });

            do {
                //wait till block is appended to transaction
            } while (!minedTransaction);
            minedTransaction = false;
            subscription.unsubscribe();
            return getStock();

        }
    }


    public void changeMined(){
        minedTransaction = true;
    }

}
