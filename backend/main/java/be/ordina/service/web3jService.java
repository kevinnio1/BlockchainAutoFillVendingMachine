package be.ordina.service;

import be.ordina.model.Vending;
import jdk.nashorn.internal.ir.Block;
import oracle.jrockit.jfr.settings.EventDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.web3j.abi.EventValues;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Int256;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.request.RawTransaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;
import org.web3j.protocol.parity.methods.response.NewAccountIdentifier;
import org.web3j.tx.Contract;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by KeLe on 2/05/2017.
 */


@Service
public class web3jService {

    public Vending initConnectionVending() throws IOException, CipherException {
        Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/
        Credentials credentials = WalletUtils.loadCredentials(BlockchainLocalSettings.VENDING_PASSWORD, BlockchainLocalSettings.WALLET_MACHINE);
        BigInteger gasprice = BigInteger.valueOf(150000);
        BigInteger gaslimit = BigInteger.valueOf(300000);
        Vending vendingContract = Vending.load(BlockchainLocalSettings.VENDING_CONTRACT,web3,credentials,gasprice, gaslimit);
        return vendingContract;
    }

    public String getClientVersion() throws IOException, ExecutionException, InterruptedException {

        Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/
        Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        EthAccounts accounts =  web3.ethAccounts().sendAsync().get();
        return clientVersion;

    }
    public List<String> getAccounts() throws IOException, ExecutionException, InterruptedException {

        Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/
        EthAccounts accounts =  web3.ethAccounts().sendAsync().get();
        return accounts.getAccounts();
    }
    public Integer getStock() throws IOException, ExecutionException, InterruptedException, CipherException {
        Vending contract = initConnectionVending();
        Type result = contract.stock().get();
        return Integer.parseInt(result.getValue().toString());
    }

    public Integer vendingStockRefill(int amount) throws IOException, ExecutionException, InterruptedException, CipherException {


        Vending contract = initConnectionVending();
        BigInteger am = BigInteger.valueOf(amount);
        TransactionReceipt transactionReceipt = contract.stockUp(new Int256(am)).get();
        Type result = contract.stock().get();
        return Integer.parseInt(result.getValue().toString());

    }


    public String buyOne() throws IOException, CipherException, ExecutionException, InterruptedException {
        Vending contract = initConnectionVending();
       // RawTransaction rt = new RawTransaction();
        TransactionReceipt transactionReceipt = contract.pay().get();
        Type result = contract.stock().get();
        return result.getValue().toString();
    }

}
