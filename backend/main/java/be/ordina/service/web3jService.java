package be.ordina.service;

import be.ordina.model.Vending;
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
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
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
    public String vendingStockRefill() throws IOException, ExecutionException, InterruptedException, CipherException {

        Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/
        Credentials credentials = WalletUtils.loadCredentials("cola", "C:\\private-ethereum-chain\\chain\\keystore\\UTC--2017-05-02T07-48-09.999839600Z--1c6b88a198a06868d9fab6e54056f04195cfce8c");
        BigInteger gasprice = BigInteger.valueOf(150000);
        BigInteger gaslimit = BigInteger.valueOf(300000);
        BigInteger amount = BigInteger.valueOf(5);

        Vending vendingContract = Vending.load("0xA3802FbcdF17917AeD6D858775D6A082496c273F",web3,credentials,gasprice, gaslimit);
        //does not alter value. Like a "getter" from stock

        TransactionReceipt transactionReceipt = vendingContract.stockUp(new Int256(amount)).get();
        Type result = vendingContract.stock().get();


        //System.out.println("result van contract: " + result);
        //return result.getValue().toString();
        return result.getValue().toString();

    }

}
