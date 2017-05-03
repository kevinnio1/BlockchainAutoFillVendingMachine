package be.ordina.controller;

import be.ordina.service.blockchainService;
import be.ordina.service.web3jService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.CipherException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by KeLe on 27/04/2017.
 */
@CrossOrigin
@RestController
@RequestMapping(value = RequestMappings.BLOCKCHAIN)
public class blockchainController {


    //private final web3jService web3jService;

    @Autowired
    private blockchainService blockchainService;

    @Autowired
    private web3jService web3jService;

    @RequestMapping(value="/getPeersOfNode",method = RequestMethod.GET)
    public String getPeersOfNode() {
           String res = blockchainService.getPeersOfNode("https://83e0829f9b1d4746b9d3e46314c35f57-vp1.us.blockchain.ibm.com:5001");
           System.out.println("respones : " + res);
           return res;

    }

    @RequestMapping(value="/getClientVersion",method = RequestMethod.GET)
    public String getClientVersion() {
        String res = "";
        try {
            res = web3jService.getClientVersion();

        System.out.println("Client version : " + res);
        return res;
        } catch (IOException e) {
            e.printStackTrace();
            return "error has occurred";
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    return res;
    }

    @RequestMapping(value="/getAccounts",method = RequestMethod.GET)
    public List<String> getAccounts() {
        List<String> res = new ArrayList<>();
        try {
            res = web3jService.getAccounts();

            System.out.println("accounts : " + res);
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return res;
    }



    @RequestMapping(value="/stockRefill",method = RequestMethod.GET)
    public String stockRefill() {
        String res = "";

        try {
            res = web3jService.vendingStockRefill();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (CipherException e) {
            e.printStackTrace();
        }

        System.out.println("Result refill stock : " + res);
        return res;
    }


}
