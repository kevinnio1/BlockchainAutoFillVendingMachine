package be.ordina.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
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

}
