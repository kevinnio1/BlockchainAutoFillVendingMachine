package be.ordina.controller;

import be.ordina.service.Web3jService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.CipherException;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
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
    private UserController userController;

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
            if(userController.currentUserIsAdmin()){
            res = web3jService.getAccounts();}else {return new ArrayList<>();}
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

    @RequestMapping(value="/getPeerCount",method = RequestMethod.GET)
    public int getPeerCount() throws ExecutionException, InterruptedException {
        //return 1;
        return web3jService.getConnectedPeers();
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
            //check if it is an admin
            boolean isAdmin = userController.currentUserIsAdmin();
            if(isAdmin){
            String currentwalletID = userController.getWalletIDcurrentUser();
            String passwordWallet = userController.getWalletPassword();
            res = web3jService.vendingStockRefill( Integer.parseInt(amount),currentwalletID,passwordWallet);
            }else {
                return getSTock();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (CipherException e) {
            e.printStackTrace();
        }
        return res;
    }
    
    @RequestMapping(value="/buyOne",method = RequestMethod.POST)
    public int buyOne() {
        int res = 0;
        try {
            String currentwalletID = userController.getWalletIDcurrentUser();
            String passwordWallet = userController.getWalletPassword();
            res = web3jService.buyOne(currentwalletID,passwordWallet);
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

    public boolean addNewAdmin(String walletID) throws ExecutionException, InterruptedException {
        return web3jService.addNewAdmin(walletID);
    }

    @RequestMapping(value="/getPercentStock",method = RequestMethod.GET)
    public int getPercentStock() throws InterruptedException, ExecutionException, CipherException, IOException {
        return web3jService.getPercentStock();
    }

    public boolean addNewNormalUser(String walletID) throws ExecutionException, InterruptedException {
            return web3jService.addNewUser(walletID);
    }


    @RequestMapping(value="/getMaxStock",method = RequestMethod.GET)
    public int getMaxStock() {
        int res = 0;

        try {
            res = web3jService.getMaxStock();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping(value="/getBalanceCurrUser",method = RequestMethod.GET)
    public Float getBalanceCurrUser() {
        BigDecimal res=new BigDecimal("0.00");
        try {
            res = web3jService.getBalance(userController.getWalletIDcurrentUser());

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res.floatValue();
    }

    @RequestMapping(value="/getMinStock",method = RequestMethod.GET)
    public int getMinStock() {
        int res = 0;
        try {
            res = web3jService.getMinStock();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return res;
    }



    @RequestMapping(value="/setMinStock/{amount}",method = RequestMethod.POST)
    public int setMinStock(@PathVariable String amount) {
        int res = 0;

        try {
            //check if it is an admin
            boolean isAdmin = userController.currentUserIsAdmin();
            if(isAdmin){
                String currentwalletID = userController.getWalletIDcurrentUser();
                String passwordWallet = userController.getWalletPassword();
                res = web3jService.setMinStock(Integer.parseInt(amount),currentwalletID,passwordWallet);
            }else {
                return getMinStock();
            }
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

    @RequestMapping(value="/setMaxStock/{amount}",method = RequestMethod.POST)
    public int setMaxStock(@PathVariable String amount) {
        int res = 0;

        try {
            //check if it is an admin
            boolean isAdmin = userController.currentUserIsAdmin();
            if(isAdmin){
                String currentwalletID = userController.getWalletIDcurrentUser();
                String passwordWallet = userController.getWalletPassword();
                res = web3jService.setMaxStock(Integer.parseInt(amount),currentwalletID,passwordWallet);
            }else {
                return getMinStock();
            }
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


    public String makeNewWallet(String pass) {

        try {

            return web3jService.makeNewWallet(pass);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
