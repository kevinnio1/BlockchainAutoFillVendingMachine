package be.ordina.model;

import java.lang.String;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Future;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
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
    private static final String BINARY = "606060405234156200000d57fe5b5b33600060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506032600481905550600a60058190555060045460068190555060146001819055506200008f336200010e6401000000000262000415176401000000009004565b600860008154809291906001019190505550600a8054806001018281620000b7919062000226565b916000526020600020900160005b33909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505b6200027d565b6000600090505b600980549050811015620001a9578173ffffffffffffffffffffffffffffffffffffffff166009828154811015156200014a57fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156200019a5760006000fd5b5b808060010191505062000115565b60098054806001018281620001bf919062000226565b916000526020600020900160005b84909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550506007600081548092919060010191905055505b5050565b81548183558181151162000250578183600052602060002091820191016200024f919062000255565b5b505050565b6200027a91905b80821115620002765760008160009055506001016200025c565b5090565b90565b612c31806200028d6000396000f30060606040523615610105576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630a3b0a4f1461011b578063168ae483146101515780631785f53c146101875780631b9265b8146101bd5780632538ff6f146101df57806329092d0e146101ff5780633be32f7d14610235578063403037341461024757806341c0e1b5146102675780634f8ced30146102795780635ae0dfe31461029f57806370480275146102c5578063863dac41146102fb57806391b7f5ed14610327578063bdf3c4ae14610347578063d67476eb1461036d578063dde2853e14610393578063e5c42fd1146103b9578063f2020275146103ef575b341561010d57fe5b6101195b60006000fd5b565b005b341561012357fe5b61014f600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610415565b005b341561015957fe5b610185600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610527565b005b341561018f57fe5b6101bb600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190505061066b565b005b6101c5610afc565b604051808215151515815260200191505060405180910390f35b34156101e757fe5b6101fd6004808035906020019091905050611008565b005b341561020757fe5b610233600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506111d2565b005b341561023d57fe5b610245611663565b005b341561024f57fe5b6102656004808035906020019091905050611e00565b005b341561026f57fe5b610277611fca565b005b341561028157fe5b6102896120a3565b6040518082815260200191505060405180910390f35b34156102a757fe5b6102af6120a9565b6040518082815260200191505060405180910390f35b34156102cd57fe5b6102f9600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506120af565b005b610311600480803590602001909190505061245e565b6040518082815260200191505060405180910390f35b341561032f57fe5b610345600480803590602001909190505061287e565b005b341561034f57fe5b610357612925565b6040518082815260200191505060405180910390f35b341561037557fe5b61037d61292b565b6040518082815260200191505060405180910390f35b341561039b57fe5b6103a3612931565b6040518082815260200191505060405180910390f35b34156103c157fe5b6103ed600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050612937565b005b34156103f757fe5b6103ff612a3b565b6040518082815260200191505060405180910390f35b6000600090505b6009805490508110156104ac578173ffffffffffffffffffffffffffffffffffffffff1660098281548110151561044f57fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141561049e5760006000fd5b5b808060010191505061041c565b600980548060010182816104c09190612bb4565b916000526020600020900160005b84909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550506007600081548092919060010191905055505b5050565b3373ffffffffffffffffffffffffffffffffffffffff16600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156106235780600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16600c60006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b5b3373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f1935050505015156106675760006000fd5b5b50565b6000600a8054806020026020016040519081016040528092919081815260200182805480156106ef57602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190600101908083116106a5575b50505050506000600090505b81518110156108e8573373ffffffffffffffffffffffffffffffffffffffff16828281518110151561072957fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1614156108da57600092505b600a805490508310156108d4578373ffffffffffffffffffffffffffffffffffffffff16600a8481548110151561078757fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156108c657600a6001600a80549050038154811015156107e757fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16600a8481548110151561083957fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff165050600a6001600a805490500381548110151561087f57fe5b906000526020600020900160005b6101000a81549073ffffffffffffffffffffffffffffffffffffffff0219169055600860008154809291906001900391905055506108d4565b5b8280600101935050610754565b5b610af6565b5b80806001019150506106fb565b3073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415610aa757600092505b600a80549050831015610aa1578373ffffffffffffffffffffffffffffffffffffffff16600a8481548110151561095457fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415610a9357600a6001600a80549050038154811015156109b457fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16600a84815481101515610a0657fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff165050600a6001600a8054905003815481101515610a4c57fe5b906000526020600020900160005b6101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905560086000815480929190600190039190505550610aa1565b5b8280600101935050610921565b5b610af6565b6000341115610af5573373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f193505050501515610af45760006000fd5b5b5b50505050565b6000600060006009805480602002602001604051908101604052809291908181526020018280548015610b8457602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019060010190808311610b3a575b50505050506000600090505b8151811015610db8573373ffffffffffffffffffffffffffffffffffffffff168282815181101515610bbe57fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff161415610daa5733935060006006541315610d9e5766038d7ea4c6800060015402341115610c5e5766038d7ea4c6800060015402340392508373ffffffffffffffffffffffffffffffffffffffff166108fc849081150290604051809050600060405180830381858888f193505050501515610c595760006000fd5b610cbb565b66038d7ea4c6800060015402341015610cba578373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f1935050505015610cb45760006000fd5b60006000fd5b5b60045460065414158015610cd457506000600b80549050115b15610ce257610ce1612a41565b5b600660008154809291906001900391905055506005546006541415610d95573073ffffffffffffffffffffffffffffffffffffffff1663863dac41600654600454036000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b1515610d7b57fe5b6102c65a03f11515610d8957fe5b50505060405180519050505b60019450610da5565b60006000fd5b5b611001565b5b8080600101915050610b90565b3073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415610fb25733935060006006541315610fa65766038d7ea4c6800060015402341115610e665766038d7ea4c6800060015402340392508373ffffffffffffffffffffffffffffffffffffffff166108fc849081150290604051809050600060405180830381858888f193505050501515610e615760006000fd5b610ec3565b66038d7ea4c6800060015402341015610ec2578373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f1935050505015610ebc5760006000fd5b60006000fd5b5b60045460065414158015610edc57506000600b80549050115b15610eea57610ee9612a41565b5b600660008154809291906001900391905055506005546006541415610f9d573073ffffffffffffffffffffffffffffffffffffffff1663863dac41600654600454036000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b1515610f8357fe5b6102c65a03f11515610f9157fe5b50505060405180519050505b60019450610fad565b60006000fd5b5b611001565b6000341115611000573373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f193505050501515610fff5760006000fd5b5b5b5050505090565b600a80548060200260200160405190810160405280929190818152602001828054801561108a57602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019060010190808311611040575b50505050506000600090505b8151811015611121573373ffffffffffffffffffffffffffffffffffffffff1682828151811015156110c457fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff161415611113578260045412806110fb575082600654125b156111065760006000fd5b826005819055505b6111cd565b5b8080600101915050611096565b3073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141561117e57826004541280611166575082600654125b156111715760006000fd5b826005819055505b6111cd565b60003411156111cc573373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f1935050505015156111cb5760006000fd5b5b5b505050565b6000600a80548060200260200160405190810160405280929190818152602001828054801561125657602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001906001019080831161120c575b50505050506000600090505b815181101561144f573373ffffffffffffffffffffffffffffffffffffffff16828281518110151561129057fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff16141561144157600092505b60098054905083101561143b578373ffffffffffffffffffffffffffffffffffffffff166009848154811015156112ee57fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141561142d57600960016009805490500381548110151561134e57fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166009848154811015156113a057fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff16505060096001600980549050038154811015156113e657fe5b906000526020600020900160005b6101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556007600081548092919060019003919050555061143b565b5b82806001019350506112bb565b5b61165d565b5b8080600101915050611262565b3073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141561160e57600092505b600980549050831015611608578373ffffffffffffffffffffffffffffffffffffffff166009848154811015156114bb57fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156115fa57600960016009805490500381548110151561151b57fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1660098481548110151561156d57fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff16505060096001600980549050038154811015156115b357fe5b906000526020600020900160005b6101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905560076000815480929190600190039190505550611608565b5b8280600101935050611488565b5b61165d565b600034111561165c573373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f19350505050151561165b5760006000fd5b5b5b50505050565b6000600060098054806020026020016040519081016040528092919081815260200182805480156116e957602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001906001019080831161169f575b50505050506000600090505b8151811015611a67573373ffffffffffffffffffffffffffffffffffffffff16828281518110151561172357fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff161415611a5957600093505b6009805490508410156118ce573373ffffffffffffffffffffffffffffffffffffffff1660098581548110151561178157fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156118c05760096001600980549050038154811015156117e157fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1660098581548110151561183357fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff165050600960016009805490500381548110151561187957fe5b906000526020600020900160005b6101000a81549073ffffffffffffffffffffffffffffffffffffffff0219169055600760008154809291906001900391905055506118ce565b5b838060010194505061174e565b600092505b600a80549050831015611a53573373ffffffffffffffffffffffffffffffffffffffff16600a8481548110151561190657fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415611a4557600a6001600a805490500381548110151561196657fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16600a848154811015156119b857fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff165050600a6001600a80549050038154811015156119fe57fe5b906000526020600020900160005b6101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905560086000815480929190600190039190505550611a53565b5b82806001019350506118d3565b5b611dfa565b5b80806001019150506116f5565b3073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415611dab57600093505b600980549050841015611c20573373ffffffffffffffffffffffffffffffffffffffff16600985815481101515611ad357fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415611c12576009600160098054905003815481101515611b3357fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16600985815481101515611b8557fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1650506009600160098054905003815481101515611bcb57fe5b906000526020600020900160005b6101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905560076000815480929190600190039190505550611c20565b5b8380600101945050611aa0565b600092505b600a80549050831015611da5573373ffffffffffffffffffffffffffffffffffffffff16600a84815481101515611c5857fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415611d9757600a6001600a8054905003815481101515611cb857fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16600a84815481101515611d0a57fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff165050600a6001600a8054905003815481101515611d5057fe5b906000526020600020900160005b6101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905560086000815480929190600190039190505550611da5565b5b8280600101935050611c25565b5b611dfa565b6000341115611df9573373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f193505050501515611df85760006000fd5b5b5b50505050565b600a805480602002602001604051908101604052809291908181526020018280548015611e8257602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019060010190808311611e38575b50505050506000600090505b8151811015611f19573373ffffffffffffffffffffffffffffffffffffffff168282815181101515611ebc57fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff161415611f0b57826006541380611ef3575082600554135b15611efe5760006000fd5b826004819055505b611fc5565b5b8080600101915050611e8e565b3073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415611f7657826006541380611f5e575082600554135b15611f695760006000fd5b826004819055505b611fc5565b6000341115611fc4573373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f193505050501515611fc35760006000fd5b5b5b505050565b3373ffffffffffffffffffffffffffffffffffffffff16600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141561205c57600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b5b3373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f1935050505015156120a05760006000fd5b5b565b60045481565b60055481565b6000600a80548060200260200160405190810160405280929190818152602001828054801561213357602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190600101908083116120e9575b50505050506000600090505b81518110156122bb573373ffffffffffffffffffffffffffffffffffffffff16828281518110151561216d57fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1614156122ad5761219c84610415565b600092505b600a80549050831015612231578373ffffffffffffffffffffffffffffffffffffffff16600a848154811015156121d457fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156122235760006000fd5b5b82806001019350506121a1565b600a80548060010182816122459190612bb4565b916000526020600020900160005b86909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550506008600081548092919060010191905055505b612458565b5b808060010191505061213f565b3073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415612409576122f884610415565b600092505b600a8054905083101561238d578373ffffffffffffffffffffffffffffffffffffffff16600a8481548110151561233057fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141561237f5760006000fd5b5b82806001019350506122fd565b600a80548060010182816123a19190612bb4565b916000526020600020900160005b86909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550506008600081548092919060010191905055505b612458565b6000341115612457573373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f1935050505015156124565760006000fd5b5b5b50505050565b6000600a8054806020026020016040519081016040528092919081815260200182805480156124e257602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019060010190808311612498575b50505050506000600090505b81518110156126a2573373ffffffffffffffffffffffffffffffffffffffff16828281518110151561251c57fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff161415612694576000841315801561255857506004548460065401135b156125635760006000fd5b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc66038d7ea4c68000600c60009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16634511c49a6000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b151561263557fe5b6102c65a03f1151561264357fe5b505050604051805190500286029081150290604051809050600060405180830381858888f1935050505015156126795760006000fd5b8360066000828254019250508190555060065492505b612877565b5b80806001019150506124ee565b3073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141561282857600084131580156126ec57506004548460065401135b156126f75760006000fd5b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc66038d7ea4c68000600c60009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16634511c49a6000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b15156127c957fe5b6102c65a03f115156127d757fe5b505050604051805190500286029081150290604051809050600060405180830381858888f19350505050151561280d5760006000fd5b8360066000828254019250508190555060065492505b612877565b6000341115612876573373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f1935050505015156128755760006000fd5b5b5b5050919050565b3373ffffffffffffffffffffffffffffffffffffffff16600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156128dd57806001819055505b5b3373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f1935050505015156129215760006000fd5b5b50565b60065481565b60015481565b60085481565b3373ffffffffffffffffffffffffffffffffffffffff16600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156129f357600b80548060010182816129a19190612bb4565b916000526020600020900160005b83909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505b5b3373ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051809050600060405180830381858888f193505050501515612a375760006000fd5b5b50565b60075481565b60006000600066038d7ea4c68000600c60009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16634511c49a6000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401809050602060405180830381600087803b1515612add57fe5b6102c65a03f11515612aeb57fe5b5050506040518051905060015403029250600b8054905083811515612b0c57fe5b049150600090505b600b80549050811015612bae57600b81815481101515612b3057fe5b906000526020600020900160005b9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc839081150290604051809050600060405180830381858888f193505050501515612ba05760006000fd5b5b8080600101915050612b14565b5b505050565b815481835581811511612bdb57818360005260206000209182019101612bda9190612be0565b5b505050565b612c0291905b80821115612bfe576000816000905550600101612be6565b5090565b905600a165627a7a7230582049398b48ea214bf1fc55445410a502b2b5a38d3585da13b25a61d717613aefa70029";

    private Vending(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private Vending(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public Future<TransactionReceipt> add(Address user) {
        Function function = new Function("add", Arrays.<Type>asList(user), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> setSupplier(Address a) {
        Function function = new Function("setSupplier", Arrays.<Type>asList(a), Collections.<TypeReference<?>>emptyList());
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

    public Future<TransactionReceipt> setMinStock(Int256 newStock) {
        Function function = new Function("setMinStock", Arrays.<Type>asList(newStock), Collections.<TypeReference<?>>emptyList());
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

    public Future<TransactionReceipt> setMaxStock(Int256 newStock) {
        Function function = new Function("setMaxStock", Arrays.<Type>asList(newStock), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> kill() {
        Function function = new Function("kill", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Int256> maxStock() {
        Function function = new Function("maxStock", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Int256> minStock() {
        Function function = new Function("minStock", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
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

    public Future<TransactionReceipt> addStakeholder(Address stakeholder) {
        Function function = new Function("addStakeholder", Arrays.<Type>asList(stakeholder), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
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
}
