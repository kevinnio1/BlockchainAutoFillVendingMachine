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
import org.web3j.abi.datatypes.generated.Uint256;
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
    private static final String BINARY = "6060604052341561000c57fe5b5b33600060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506032600581905550601460028190555073c7764818c6276ae6e145db9143bba535c148d6c3600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555073def240271e9e6b79b06f3a7c4a144d3874e512d2600460006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b5b6104d9806101196000396000f30060606040523615610076576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680631b9265b81461008c57806341c0e1b5146100ae578063863dac41146100c057806391b7f5ed146100ec578063bdf3c4ae1461010c578063c095aeb214610132575b341561007e57fe5b61008a5b60006000fd5b565b005b610094610158565b604051808215151515815260200191505060405180910390f35b34156100b657fe5b6100be6102fb565b005b6100d6600480803590602001909190505061038f565b6040518082815260200191505060405180910390f35b34156100f457fe5b61010a600480803590602001909190505061043b565b005b341561011457fe5b61011c6104a1565b6040518082815260200191505060405180910390f35b341561013a57fe5b6101426104a7565b6040518082815260200191505060405180910390f35b6000600033600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600060055413156102f05766038d7ea4c68000600254023411156102365766038d7ea4c680006002540234039050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc829081150290604051809050600060405180830381858888f1935050505015156102315760006000fd5b610250565b66038d7ea4c680006002540234101561024f5760006000fd5b5b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc662386f26fc100009081150290604051809050600060405180830381858888f1935050505015156102bd5760006000fd5b60056000815480929190600190039190505550600a60055414156102e7576102e5602861038f565b505b600191506102f7565b60006000fd5b5b5090565b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141561038c57600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b5b565b6000600082131580156103a6575060328260055401135b156103b15760006000fd5b600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc662386f26fc1000084029081150290604051809050600060405180830381858888f1935050505015156104205760006000fd5b8160056000828254019250508190555060055490505b919050565b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141561049d5766038d7ea4c680008102505b5b50565b60055481565b600254815600a165627a7a7230582086fcb33cfb2d8da75a71f95805543dce1c9e5a2b1da55c28e42c57145a7810ce0029";

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

    public Future<TransactionReceipt> setPrice(Uint256 newPrice) {
        Function function = new Function("setPrice", Arrays.<Type>asList(newPrice), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Int256> stock() {
        Function function = new Function("stock", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> price_finney() {
        Function function = new Function("price_finney", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
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
