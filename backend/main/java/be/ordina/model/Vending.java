package be.ordina.model;

import java.lang.String;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Future;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Int256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version 2.1.0.
 */
public final class Vending extends Contract {
    private static final String BINARY = "606060405234610000575b33600060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060326002819055505b5b6102f0806100646000396000f30060606040523615610060576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680631b9265b81461007257806341c0e1b514610094578063863dac41146100a3578063bdf3c4ae146100cf575b34610000576100705b610000565b565b005b61007a6100f2565b604051808215151515815260200191505060405180910390f35b34610000576100a16101fe565b005b6100b96004808035906020019091905050610292565b6040518082815260200191505060405180910390f35b34610000576100dc6102be565b6040518082815260200191505060405180910390f35b6000600033600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600060025413156101f45766470de4df8200003411156101c75766470de4df82000034039050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc829081150290604051809050600060405180830381858888f1935050505015156101c257610000565b6101dc565b66470de4df8200003410156101db57610000565b5b600260008154809291906001900391905055506101f9565b610000565b5b5090565b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141561028f57600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b5b565b60006000821315156102a357610000565b8160026000828254019250508190555060025490505b919050565b600254815600a165627a7a723058209a856782fdf2c03bbc5af26496c5c51e23f043d00359307470d7aec69c76d04e0029";

    private Vending(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private Vending(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public Future<TransactionReceipt> pay() {
        Function function = new Function("pay", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> kill() {
        Function function = new Function("kill", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> stockUp(Int256 amount) {
        Function function = new Function("stockUp", Arrays.<Type>asList(amount), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Int256> stock() {
        Function function = new Function("stock", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public static Future<Vending> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue) {
        return deployAsync(Vending.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialValue);
    }

    public static Future<Vending> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue) {
        return deployAsync(Vending.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialValue);
    }

    public static Vending load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Vending(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Vending load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Vending(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
