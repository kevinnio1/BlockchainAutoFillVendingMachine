package be.ordina.controller;

import be.ordina.service.Web3jService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
public class BlockchainController {



    @Autowired
    private Web3jService web3jService;

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

    @RequestMapping(value="/getStock",method = RequestMethod.GET)
    public int getSTock() {
        int res = 0;

        try {
            res = web3jService.getStock();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (CipherException e) {
            e.printStackTrace();
        }

        //System.out.println("Result get stock : " + res);
        return res;
    }

    @RequestMapping(value="/stockRefill/{amount}",method = RequestMethod.POST)
    public int stockRefill(@PathVariable String amount) {
        int res = 0;

        try {
            res = web3jService.vendingStockRefill( Integer.parseInt(amount));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (CipherException e) {
            e.printStackTrace();
        }

        //System.out.println("Result refill stock : " + res);
        return res;
    }
    
    @RequestMapping(value="/buyOne",method = RequestMethod.POST)
    public int buyOne() {
        int res = 0;
        try {
            res = web3jService.buyOne();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (CipherException e) {
            e.printStackTrace();
        }

        System.out.println("Result buyOne: " + res);
        return res;
    }

}
