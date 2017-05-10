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
    private static final String BINARY = "606060405234156200000d57fe5b5b33600060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506032600481905550601460018190555073c7764818c6276ae6e145db9143bba535c148d6c3600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555073def240271e9e6b79b06f3a7c4a144d3874e512d2600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506200012833620001a764010000000002620002db176401000000009004565b60066000815480929190600101919050555060088054806001018281620001509190620002bf565b916000526020600020900160005b33909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505b62000316565b6000600090505b60078054905081101562000242578173ffffffffffffffffffffffffffffffffffffffff16600782815481101515620001e357fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415620002335760006000fd5b5b8080600101915050620001ae565b60078054806001018281620002589190620002bf565b916000526020600020900160005b84909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550506005600081548092919060010191905055505b5050565b815481835581811511620002e957818360005260206000209182019101620002e89190620002ee565b5b505050565b6200031391905b808211156200030f576000816000905550600101620002f5565b5090565b90565b6117f580620003266000396000f300606060405236156100c3576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630a3b0a4f146100d95780631785f53c1461010f5780631b9265b81461014557806329092d0e146101675780633be32f7d1461019d57806341c0e1b5146101af57806370480275146101c1578063863dac41146101f757806391b7f5ed14610223578063bdf3c4ae14610243578063d67476eb14610269578063dde2853e1461028f578063f2020275146102b5575b34156100cb57fe5b6100d75b60006000fd5b565b005b34156100e157fe5b61010d600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506102db565b005b341561011757fe5b610143600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506103ed565b005b61014d6106e7565b604051808215151515815260200191505060405180910390f35b341561016f57fe5b61019b600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610a94565b005b34156101a557fe5b6101ad610d8e565b005b34156101b757fe5b6101bf61120f565b005b34156101c957fe5b6101f5600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506112e8565b005b61020d6004808035906020019091905050611571565b6040518082815260200191505060405180910390f35b341561022b57fe5b61024160048080359060200190919050506116b9565b005b341561024b57fe5b610253611760565b6040518082815260200191505060405180910390f35b341561027157fe5b610279611766565b6040518082815260200191505060405180910390f35b341561029757fe5b61029f61176c565b6040518082815260200191505060405180910390f35b34156102bd57fe5b6102c5611772565b6040518082815260200191505060405180910390f35b6000600090505b600780549050811015610372578173ffffffffffffffffffffffffffffffffffffffff1660078281548110151561031557fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156103645760006000fd5b5b80806001019150506102e2565b600780548060010182816103869190611778565b916000526020600020900160005b84909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550506005600081548092919060010191905055505b5050565b6000600880548060200260200160405190810160405280929190818152602001828054801561047157602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019060010190808311610427575b50505050506000600090505b600780549050811015610692573373ffffffffffffffffffffffffffffffffffffffff166007828154811015156104b057fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141561068457600092505b60088054905083101561067e578373ffffffffffffffffffffffffffffffffffffffff1660088481548110151561053157fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141561067057600860016008805490500381548110151561059157fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166008848154811015156105e357fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff165050600860016008805490500381548110151561062957fe5b906000526020600020900160005b6101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556006600081548092919060019003919050555061067e565b5b82806001019350506104fe565b5b6106e1565b5b808060010191505061047d565b60003411156106e0573373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f1935050505015156106df5760006000fd5b5b5b50505050565b600060006000600780548060200260200160405190810160405280929190818152602001828054801561076f57602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019060010190808311610725575b50505050506000600090505b600780549050811015610a3e573373ffffffffffffffffffffffffffffffffffffffff166007828154811015156107ae57fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415610a3057339350600060045413156109d15766038d7ea4c68000600154023411156108715766038d7ea4c6800060015402340392508373ffffffffffffffffffffffffffffffffffffffff166108fc849081150290604051809050600060405180830381858888f19350505050151561086c5760006000fd5b6108de565b66038d7ea4c68000600154023410156108dd573373ffffffffffffffffffffffffffffffffffffffff167fd603b97d3ae4e4d8e631e5792633a0c596cb7a96ad034371f9edfb1f66b7b30a6000604051808215151515815260200191505060405180910390a260006000fd5b5b600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc662386f26fc100009081150290604051809050600060405180830381858888f19350505050151561094b5760006000fd5b60046000815480929190600190039190505550600a6004541415610975576109736028611571565b505b3373ffffffffffffffffffffffffffffffffffffffff167fd603b97d3ae4e4d8e631e5792633a0c596cb7a96ad034371f9edfb1f66b7b30a6001604051808215151515815260200191505060405180910390a260019450610a2b565b3373ffffffffffffffffffffffffffffffffffffffff167fd603b97d3ae4e4d8e631e5792633a0c596cb7a96ad034371f9edfb1f66b7b30a6000604051808215151515815260200191505060405180910390a260006000fd5b5b610a8d565b5b808060010191505061077b565b6000341115610a8c573373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f193505050501515610a8b5760006000fd5b5b5b5050505090565b60006008805480602002602001604051908101604052809291908181526020018280548015610b1857602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019060010190808311610ace575b50505050506000600090505b600780549050811015610d39573373ffffffffffffffffffffffffffffffffffffffff16600782815481101515610b5757fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415610d2b57600092505b600780549050831015610d25578373ffffffffffffffffffffffffffffffffffffffff16600784815481101515610bd857fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415610d17576007600160078054905003815481101515610c3857fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16600784815481101515610c8a57fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1650506007600160078054905003815481101515610cd057fe5b906000526020600020900160005b6101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905560056000815480929190600190039190505550610d25565b5b8280600101935050610ba5565b5b610d88565b5b8080600101915050610b24565b6000341115610d87573373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f193505050501515610d865760006000fd5b5b5b50505050565b600060006007805480602002602001604051908101604052809291908181526020018280548015610e1457602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019060010190808311610dca575b50505050506000600090505b6007805490508110156111ba573373ffffffffffffffffffffffffffffffffffffffff16600782815481101515610e5357fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156111ac57600093505b600780549050841015611021573373ffffffffffffffffffffffffffffffffffffffff16600785815481101515610ed457fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415611013576007600160078054905003815481101515610f3457fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16600785815481101515610f8657fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1650506007600160078054905003815481101515610fcc57fe5b906000526020600020900160005b6101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905560056000815480929190600190039190505550611021565b5b8380600101945050610ea1565b600092505b6008805490508310156111a6573373ffffffffffffffffffffffffffffffffffffffff1660088481548110151561105957fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156111985760086001600880549050038154811015156110b957fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1660088481548110151561110b57fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff165050600860016008805490500381548110151561115157fe5b906000526020600020900160005b6101000a81549073ffffffffffffffffffffffffffffffffffffffff0219169055600660008154809291906001900391905055506111a6565b5b8280600101935050611026565b5b611209565b5b8080600101915050610e20565b6000341115611208573373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f1935050505015156112075760006000fd5b5b5b50505050565b3373ffffffffffffffffffffffffffffffffffffffff16600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156112a157600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b5b3373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f1935050505015156112e55760006000fd5b5b565b6000600880548060200260200160405190810160405280929190818152602001828054801561136c57602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019060010190808311611322575b50505050506000600090505b60078054905081101561151c573373ffffffffffffffffffffffffffffffffffffffff166007828154811015156113ab57fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141561150e576113fd846102db565b600092505b600880549050831015611492578373ffffffffffffffffffffffffffffffffffffffff1660088481548110151561143557fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156114845760006000fd5b5b8280600101935050611402565b600880548060010182816114a69190611778565b916000526020600020900160005b86909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550506006600081548092919060010191905055505b61156b565b5b8080600101915050611378565b600034111561156a573373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f1935050505015156115695760006000fd5b5b5b50505050565b60003373ffffffffffffffffffffffffffffffffffffffff16600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141561166f57600082131580156115de575060328260045401135b156115e95760006000fd5b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc662386f26fc1000084029081150290604051809050600060405180830381858888f1935050505015156116585760006000fd5b8160046000828254019250508190555060045490505b5b3373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f1935050505015156116b35760006000fd5b5b919050565b3373ffffffffffffffffffffffffffffffffffffffff16600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141561171857806001819055505b5b3373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f19350505050151561175c5760006000fd5b5b50565b60045481565b60015481565b60065481565b60055481565b81548183558181151161179f5781836000526020600020918201910161179e91906117a4565b5b505050565b6117c691905b808211156117c25760008160009055506001016117aa565b5090565b905600a165627a7a7230582021eed2b2548f4f5a1b163a80120bd208eb663b6af7e4bfb7771c817215cb07f70029\r\n";

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

    public Future<TransactionReceipt> add(Address user) {
        Function function = new Function("add", Arrays.<Type>asList(user), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> removeAdmin(Address admin) {
        Function function = new Function("removeAdmin", Arrays.<Type>asList(admin), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> pay() {
        Function function = new Function("pay", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> remove(Address user) {
        Function function = new Function("remove", Arrays.<Type>asList(user), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> deleteAccount() {
        Function function = new Function("deleteAccount", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> kill() {
        Function function = new Function("kill", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> addAdmin(Address admin) {
        Function function = new Function("addAdmin", Arrays.<Type>asList(admin), Collections.<TypeReference<?>>emptyList());
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

    public Future<Uint256> finneyPrice() {
        Function function = new Function("finneyPrice", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Int256> adminUsers() {
        Function function = new Function("adminUsers", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Int256> users() {
        Function function = new Function("users", 
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

    public static class IsPayedEventResponse {
        public Address _from;

        public Bool _value;
    }
}
