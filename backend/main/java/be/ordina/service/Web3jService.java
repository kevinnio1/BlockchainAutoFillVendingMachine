package be.ordina.service;

import be.ordina.model.Vending;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.generated.Int256;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;
import org.web3j.protocol.parity.methods.response.PersonalUnlockAccount;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
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
    BigInteger duration = BigInteger.valueOf(3600);//one hour




    public Web3jService() throws IOException, CipherException {
        this.web3  = Web3j.build(new HttpService());
        this.parity = Parity.build(new HttpService());
        this.credentials  = WalletUtils.loadCredentials(BlockchainLocalSettings.VENDING_PASSWORD, BlockchainLocalSettings.WALLET_MACHINE);
        vendingContract = Vending.load(BlockchainLocalSettings.VENDING_CONTRACT,web3,credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
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
    }

    public Integer getStock() throws IOException, ExecutionException, InterruptedException, CipherException {
        Type result = vendingContract.stock().get();
        return Integer.parseInt(result.getValue().toString());
    }

    public Integer vendingStockRefill(int amount,String currentwalletID, String passwordWallet) throws IOException, ExecutionException, InterruptedException, CipherException {
        return doEthFunction(currentwalletID,passwordWallet,"stockup",amount);
    }


    public int doEthFunction(String currentwalletID,String passwordWallet, String func,int amountStockup) throws InterruptedException, ExecutionException, CipherException, IOException {
        Function function=null;
        BigInteger ether = Convert.toWei("0.3", Convert.Unit.ETHER).toBigInteger();;
        BigInteger am = BigInteger.valueOf(amountStockup);
        int stock = getStock();

        if(func.equalsIgnoreCase("pay")){
            if(stock==0) return 0;

            function = new Function("pay", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
            //todo: get price from contract instead of hardcoded
            ether = Convert.toWei("0.02", Convert.Unit.ETHER).toBigInteger().add(Transaction.DEFAULT_GAS.multiply(gaslimit));
            System.out.println("Ether dat je meegeeft bij betalen blikje: " + Convert.fromWei(ether.toString() , Convert.Unit.ETHER) );
            //todo: check current wallet enough credit

        }else if(func.equalsIgnoreCase("stockup")) {
            //no stock check needed because the blockchain smart contract has a max value + not enogh coins to buy more
            function = new Function("stockUp", Arrays.<Type>asList(new Int256(am)), Collections.<TypeReference<?>>emptyList());
            ether = Convert.toWei("0.0", Convert.Unit.ETHER).toBigInteger();
        }
        //maximum wei meegeven
        //todo;op basis van de wei prijs + gasprice * gaslimit de juiste hoeveelheid ether meegeven.



        //unlock accounts
        PersonalUnlockAccount currentacc = parity.personalUnlockAccount(currentwalletID,passwordWallet, duration).send();

        if (currentacc.accountUnlocked()) {

            EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(currentwalletID, DefaultBlockParameterName.LATEST).sendAsync().get();
            BigInteger nonce = ethGetTransactionCount.getTransactionCount();
            String encodedFunction = FunctionEncoder.encode(function);
            org.web3j.protocol.core.methods.request.Transaction transaction = org.web3j.protocol.core.methods.request.Transaction.createFunctionCallTransaction(currentwalletID, nonce, Transaction.DEFAULT_GAS, gaslimit, BlockchainLocalSettings.VENDING_CONTRACT, ether, encodedFunction);
            org.web3j.protocol.core.methods.response.EthSendTransaction transactionResponse =parity.personalSignAndSendTransaction(transaction,passwordWallet).send();
            final String transactionHash = transactionResponse.getTransactionHash();
            if (transactionHash == null) {
                System.out.println(transactionResponse.getError().getMessage());
                return getStock();
            }
            EthGetTransactionReceipt transactionReceipt = null;
            //todo: indien niet toegevoegd door error moet deze niet wachten op de transactionreceipt. Dus een timeout hierop plaatsen?
            do {
                transactionReceipt = web3.ethGetTransactionReceipt(transactionHash).sendAsync().get();
            } while (!transactionReceipt.getTransactionReceipt().isPresent());
            return getStock();
        }else{
            System.out.println("account is locked");
            return getStock();
        }


    }


    public int buyOne(String currentwalletID,String passwordWallet) throws IOException, CipherException, ExecutionException, InterruptedException {
        return doEthFunction(currentwalletID,passwordWallet,"pay",0);
    }

    public boolean addNewAdmin(String walletID) throws ExecutionException, InterruptedException {

        Address newAddress = new Address(walletID);
        //will wait till block is mined
        TransactionReceipt transactionReceipt= vendingContract.addAdmin(newAddress).get();
        return true;
    }

    public boolean addNewUser(String walletID) throws ExecutionException, InterruptedException {
        Address newAddress = new Address(walletID);
        //will wait till block is mined
        TransactionReceipt transactionReceipt= vendingContract.add(newAddress).get();
        return true;
    }
}
