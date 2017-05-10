package be.ordina.service;

import be.ordina.model.Vending;
import org.springframework.stereotype.Service;
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
public class Web3jService {


    private Web3j web3; //defaults to http://localhost:8545
    private Credentials credentials;
    BigInteger gasprice = BigInteger.valueOf(150000);
    BigInteger gaslimit = BigInteger.valueOf(300000);
    Vending vendingContract;
    Parity parity;
    boolean minedTransaction = false;
    Subscription subscription;
    Subscription subscription1;
    Subscription subscription2;




    public Web3jService() throws IOException, CipherException {
        this.web3  = Web3j.build(new HttpService());
        this.parity = Parity.build(new HttpService());
        this.credentials  = WalletUtils.loadCredentials(BlockchainLocalSettings.VENDING_PASSWORD, BlockchainLocalSettings.WALLET_MACHINE);
        vendingContract = Vending.load(BlockchainLocalSettings.VENDING_CONTRACT,web3,credentials,gasprice, gaslimit);
        subscribeToTransactionsandBlocks();
    }

    public void unsubscribeTransAndBlocks(){
        System.out.println("unsubscribed");
        subscription.unsubscribe();
        subscription1.unsubscribe();
        subscription2.unsubscribe();
    }
    public void subscribeToTransactionsandBlocks(){
        System.out.println("started subscription");
        //pending transactions
        subscription = web3.pendingTransactionObservable().subscribe(tx -> {

            System.out.println("is pending: " + tx.getHash());

        });
        //added to the blockchain
        subscription1 = web3.transactionObservable().subscribe(tx -> {

            System.out.println("added to the blockchain: " + tx.getHash());

        });

        subscription2 = web3.blockObservable(false).subscribe(block -> {
            for (EthBlock.TransactionResult transactionResult:
                    block.getBlock().getTransactions() ) {
                System.out.println("transaction in block equals?: " + transactionResult.get().hashCode());
            }
        });

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
        return getStock();

    }


    public int buyOne(String currentwalletID,String passwordWallet) throws IOException, CipherException, ExecutionException, InterruptedException {
        if (getStock() == 0) {
            return 0;
        } else {


            BigInteger duration = BigInteger.valueOf(3600);//one hour
            BigInteger ether = Convert.toWei("2.0", Convert.Unit.ETHER).toBigInteger();


            //todo: check first if the accounts are locked
            //unlock accounts
            PersonalUnlockAccount cola = parity.personalUnlockAccount("0x1c6B88A198a06868D9fAB6e54056F04195CfCe8C", "cola", duration).sendAsync().get();
            PersonalUnlockAccount ordina = parity.personalUnlockAccount("0xDEF240271e9E6b79b06f3a7C4A144D3874e512d2","ordina", duration).send();
            //PersonalUnlockAccount betaler = parity.personalUnlockAccount("0x08796f22807D8aE6d3B5C4427d84FC49E9551f24", "betaler", duration).sendAsync().get();

            EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount("0x1c6B88A198a06868D9fAB6e54056F04195CfCe8C", DefaultBlockParameterName.LATEST).sendAsync().get();
            BigInteger nonce = ethGetTransactionCount.getTransactionCount();
            Function function = new Function("pay", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
            String encodedFunction = FunctionEncoder.encode(function);
            org.web3j.protocol.core.methods.request.Transaction transaction = org.web3j.protocol.core.methods.request.Transaction.createFunctionCallTransaction("0xDEF240271e9E6b79b06f3a7C4A144D3874e512d2", nonce, gasprice, gaslimit, BlockchainLocalSettings.VENDING_CONTRACT, ether, encodedFunction);
            org.web3j.protocol.core.methods.response.EthSendTransaction transactionResponse = web3.ethSendTransaction(transaction).sendAsync().get();

            final String transactionHash = transactionResponse.getTransactionHash();
            if(transactionHash==null){
                System.out.println(transactionResponse.getError().getMessage());
                return getStock();
            }
            EthGetTransactionReceipt transactionReceipt= null;
            //todo: indien niet toegevoegd door error moet deze niet wachten op de transactionreceipt. Dus een timeout hierop plaatsen?
            do{
               transactionReceipt = web3.ethGetTransactionReceipt(transactionHash).sendAsync().get();
            }while(!transactionReceipt.getTransactionReceipt().isPresent());
            return getStock();
            

        }
    }


    public void changeMined(){
        minedTransaction = true;
    }

}