package be.ordina.model;

import java.lang.Override;
import java.lang.String;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Int256;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version 2.1.0.
 */
public final class Vending extends Contract {
    private static final String BINARY = "6060604052341561000c57fe5b5b33600060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506032600581905550601460028190555073c7764818c6276ae6e145db9143bba535c148d6c3600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555073def240271e9e6b79b06f3a7c4a144d3874e512d2600460006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b5b6105d2806101196000396000f30060606040523615610076576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680631b9265b81461008c57806341c0e1b5146100ae578063863dac41146100c057806391b7f5ed146100ec578063bdf3c4ae1461010c578063c095aeb214610132575b341561007e57fe5b61008a5b60006000fd5b565b005b610094610158565b604051808215151515815260200191505060405180910390f35b34156100b657fe5b6100be6103f4565b005b6100d66004808035906020019091905050610488565b6040518082815260200191505060405180910390f35b34156100f457fe5b61010a6004808035906020019091905050610534565b005b341561011457fe5b61011c61059a565b6040518082815260200191505060405180910390f35b341561013a57fe5b6101426105a0565b6040518082815260200191505060405180910390f35b6000600033600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600060055413156103965766038d7ea4c68000600254023411156102365766038d7ea4c680006002540234039050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc829081150290604051809050600060405180830381858888f1935050505015156102315760006000fd5b6102a3565b66038d7ea4c68000600254023410156102a2573373ffffffffffffffffffffffffffffffffffffffff167fd603b97d3ae4e4d8e631e5792633a0c596cb7a96ad034371f9edfb1f66b7b30a6000604051808215151515815260200191505060405180910390a260006000fd5b5b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc662386f26fc100009081150290604051809050600060405180830381858888f1935050505015156103105760006000fd5b60056000815480929190600190039190505550600a600554141561033a576103386028610488565b505b3373ffffffffffffffffffffffffffffffffffffffff167fd603b97d3ae4e4d8e631e5792633a0c596cb7a96ad034371f9edfb1f66b7b30a6001604051808215151515815260200191505060405180910390a2600191506103f0565b3373ffffffffffffffffffffffffffffffffffffffff167fd603b97d3ae4e4d8e631e5792633a0c596cb7a96ad034371f9edfb1f66b7b30a6000604051808215151515815260200191505060405180910390a260006000fd5b5b5090565b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141561048557600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b5b565b60006000821315801561049f575060328260055401135b156104aa5760006000fd5b600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc662386f26fc1000084029081150290604051809050600060405180830381858888f1935050505015156105195760006000fd5b8160056000828254019250508190555060055490505b919050565b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614156105965766038d7ea4c680008102505b5b50565b60055481565b600254815600a165627a7a72305820f964e21f3421a8304d8fe81271b307b47af9956a9c78a606391f9f28c07156a70029";

    private Vending(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private Vending(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<IsPayedEventResponse> getIsPayedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("isPayed", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<EventValues> valueList = extractEventParameters(event,transactionReceipt);
        ArrayList<IsPayedEventResponse> responses = new ArrayList<IsPayedEventResponse>(valueList.size());
        for(EventValues eventValues : valueList) {
            IsPayedEventResponse typedResponse = new IsPayedEventResponse();
            typedResponse._from = (Address)eventValues.getIndexedValues().get(0);
            typedResponse._value = (Bool)eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<IsPayedEventResponse> isPayedEventObservable() {
        final Event event = new Event("isPayed", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST,DefaultBlockParameterName.LATEST, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, IsPayedEventResponse>() {
            @Override
            public IsPayedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                IsPayedEventResponse typedResponse = new IsPayedEventResponse();
                typedResponse._from = (Address)eventValues.getIndexedValues().get(0);
                typedResponse._value = (Bool)eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
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

    public static class IsPayedEventResponse {
        public Address _from;

        public Bool _value;
    }
}
