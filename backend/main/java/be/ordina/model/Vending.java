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
    private static final String BINARY = "606060405234156200000d57fe5b5b33600060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506032600581905550601460028190555073c7764818c6276ae6e145db9143bba535c148d6c3600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555073def240271e9e6b79b06f3a7c4a144d3874e512d2600460006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506200012833620002316401000000000262000431176401000000009004565b6007600081548092919060010191905055506009805480600101828162000150919062000349565b916000526020600020900160005b33909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505060086000815481101515620001b157fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff16600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600880549050600a819055505b620003a0565b6000600090505b600880549050811015620002cc578173ffffffffffffffffffffffffffffffffffffffff166008828154811015156200026d57fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415620002bd5760006000fd5b5b808060010191505062000238565b60088054806001018281620002e2919062000349565b916000526020600020900160005b84909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550506006600081548092919060010191905055505b5050565b815481835581811511620003735781836000526020600020918201910162000372919062000378565b5b505050565b6200039d91905b80821115620003995760008160009055506001016200037f565b5090565b90565b6119dd80620003b06000396000f300606060405236156100ef576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630a3b0a4f146101055780631785f53c1461013b5780631b9265b81461017157806329092d0e146101935780632986c0e5146101c95780633be32f7d146101ef57806341c0e1b5146102015780636d4b76af146102135780637048027514610265578063863dac411461029b5780638da5cb5b146102c757806391b7f5ed14610319578063bdf3c4ae14610339578063d67476eb1461035f578063dde2853e14610385578063f2020275146103ab578063f2a40db8146103d1575b34156100f757fe5b6101035b60006000fd5b565b005b341561010d57fe5b610139600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610431565b005b341561014357fe5b61016f600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610543565b005b61017961083d565b604051808215151515815260200191505060405180910390f35b341561019b57fe5b6101c7600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610bea565b005b34156101d157fe5b6101d9610ee4565b6040518082815260200191505060405180910390f35b34156101f757fe5b6101ff610eea565b005b341561020957fe5b61021161136b565b005b341561021b57fe5b610223611444565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561026d57fe5b610299600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190505061146a565b005b6102b160048080359060200190919050506116f3565b6040518082815260200191505060405180910390f35b34156102cf57fe5b6102d761183b565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561032157fe5b6103376004808035906020019091905050611861565b005b341561034157fe5b610349611908565b6040518082815260200191505060405180910390f35b341561036757fe5b61036f61190e565b6040518082815260200191505060405180910390f35b341561038d57fe5b610395611914565b6040518082815260200191505060405180910390f35b34156103b357fe5b6103bb61191a565b6040518082815260200191505060405180910390f35b34156103d957fe5b6103ef6004808035906020019091905050611920565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6000600090505b6008805490508110156104c8578173ffffffffffffffffffffffffffffffffffffffff1660088281548110151561046b57fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156104ba5760006000fd5b5b8080600101915050610438565b600880548060010182816104dc9190611960565b916000526020600020900160005b84909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550506006600081548092919060010191905055505b5050565b600060098054806020026020016040519081016040528092919081815260200182805480156105c757602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001906001019080831161057d575b50505050506000600090505b6008805490508110156107e8573373ffffffffffffffffffffffffffffffffffffffff1660088281548110151561060657fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156107da57600092505b6009805490508310156107d4578373ffffffffffffffffffffffffffffffffffffffff1660098481548110151561068757fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156107c65760096001600980549050038154811015156106e757fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1660098481548110151561073957fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff165050600960016009805490500381548110151561077f57fe5b906000526020600020900160005b6101000a81549073ffffffffffffffffffffffffffffffffffffffff0219169055600760008154809291906001900391905055506107d4565b5b8280600101935050610654565b5b610837565b5b80806001019150506105d3565b6000341115610836573373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f1935050505015156108355760006000fd5b5b5b50505050565b60006000600060088054806020026020016040519081016040528092919081815260200182805480156108c557602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001906001019080831161087b575b50505050506000600090505b600880549050811015610b94573373ffffffffffffffffffffffffffffffffffffffff1660088281548110151561090457fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415610b865733935060006005541315610b275766038d7ea4c68000600254023411156109c75766038d7ea4c6800060025402340392508373ffffffffffffffffffffffffffffffffffffffff166108fc849081150290604051809050600060405180830381858888f1935050505015156109c25760006000fd5b610a34565b66038d7ea4c6800060025402341015610a33573373ffffffffffffffffffffffffffffffffffffffff167fd603b97d3ae4e4d8e631e5792633a0c596cb7a96ad034371f9edfb1f66b7b30a6000604051808215151515815260200191505060405180910390a260006000fd5b5b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc662386f26fc100009081150290604051809050600060405180830381858888f193505050501515610aa15760006000fd5b60056000815480929190600190039190505550600a6005541415610acb57610ac960286116f3565b505b3373ffffffffffffffffffffffffffffffffffffffff167fd603b97d3ae4e4d8e631e5792633a0c596cb7a96ad034371f9edfb1f66b7b30a6001604051808215151515815260200191505060405180910390a260019450610b81565b3373ffffffffffffffffffffffffffffffffffffffff167fd603b97d3ae4e4d8e631e5792633a0c596cb7a96ad034371f9edfb1f66b7b30a6000604051808215151515815260200191505060405180910390a260006000fd5b5b610be3565b5b80806001019150506108d1565b6000341115610be2573373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f193505050501515610be15760006000fd5b5b5b5050505090565b60006009805480602002602001604051908101604052809291908181526020018280548015610c6e57602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019060010190808311610c24575b50505050506000600090505b600880549050811015610e8f573373ffffffffffffffffffffffffffffffffffffffff16600882815481101515610cad57fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415610e8157600092505b600880549050831015610e7b578373ffffffffffffffffffffffffffffffffffffffff16600884815481101515610d2e57fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415610e6d576008600160088054905003815481101515610d8e57fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16600884815481101515610de057fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1650506008600160088054905003815481101515610e2657fe5b906000526020600020900160005b6101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905560066000815480929190600190039190505550610e7b565b5b8280600101935050610cfb565b5b610ede565b5b8080600101915050610c7a565b6000341115610edd573373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f193505050501515610edc5760006000fd5b5b5b50505050565b600a5481565b600060006008805480602002602001604051908101604052809291908181526020018280548015610f7057602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019060010190808311610f26575b50505050506000600090505b600880549050811015611316573373ffffffffffffffffffffffffffffffffffffffff16600882815481101515610faf57fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141561130857600093505b60088054905084101561117d573373ffffffffffffffffffffffffffffffffffffffff1660088581548110151561103057fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141561116f57600860016008805490500381548110151561109057fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166008858154811015156110e257fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff165050600860016008805490500381548110151561112857fe5b906000526020600020900160005b6101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556006600081548092919060019003919050555061117d565b5b8380600101945050610ffd565b600092505b600980549050831015611302573373ffffffffffffffffffffffffffffffffffffffff166009848154811015156111b557fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156112f457600960016009805490500381548110151561121557fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1660098481548110151561126757fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff16505060096001600980549050038154811015156112ad57fe5b906000526020600020900160005b6101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905560076000815480929190600190039190505550611302565b5b8280600101935050611182565b5b611365565b5b8080600101915050610f7c565b6000341115611364573373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f1935050505015156113635760006000fd5b5b5b50505050565b3373ffffffffffffffffffffffffffffffffffffffff16600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156113fd57600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b5b3373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f1935050505015156114415760006000fd5b5b565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600060098054806020026020016040519081016040528092919081815260200182805480156114ee57602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190600101908083116114a4575b50505050506000600090505b60088054905081101561169e573373ffffffffffffffffffffffffffffffffffffffff1660088281548110151561152d57fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156116905761157f84610431565b600092505b600980549050831015611614578373ffffffffffffffffffffffffffffffffffffffff166009848154811015156115b757fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156116065760006000fd5b5b8280600101935050611584565b600980548060010182816116289190611960565b916000526020600020900160005b86909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550506007600081548092919060010191905055505b6116ed565b5b80806001019150506114fa565b60003411156116ec573373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f1935050505015156116eb5760006000fd5b5b5b50505050565b60003373ffffffffffffffffffffffffffffffffffffffff16600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156117f15760008213158015611760575060328260055401135b1561176b5760006000fd5b600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc662386f26fc1000084029081150290604051809050600060405180830381858888f1935050505015156117da5760006000fd5b8160056000828254019250508190555060055490505b5b3373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f1935050505015156118355760006000fd5b5b919050565b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b3373ffffffffffffffffffffffffffffffffffffffff16600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156118c057806002819055505b5b3373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f1935050505015156119045760006000fd5b5b50565b60055481565b60025481565b60075481565b60065481565b60088181548110151561192f57fe5b906000526020600020900160005b915054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b81548183558181151161198757818360005260206000209182019101611986919061198c565b5b505050565b6119ae91905b808211156119aa576000816000905550600101611992565b5090565b905600a165627a7a72305820431b7e485eb924d135d8ca7274286f091eed8611c0ed56dbe7c24b558f4d015f0029\r\n";

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

    public Future<Uint256> index() {
        Function function = new Function("index", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> deleteAccount() {
        Function function = new Function("deleteAccount", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> kill() {
        Function function = new Function("kill", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Address> firstAccount() {
        Function function = new Function("firstAccount", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> addAdmin(Address admin) {
        Function function = new Function("addAdmin", Arrays.<Type>asList(admin), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> stockUp(Int256 amount) {
        Function function = new Function("stockUp", Arrays.<Type>asList(amount), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Address> owner() {
        Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
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

    public Future<Address> accounts(Uint256 param0) {
        Function function = new Function("accounts", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
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
