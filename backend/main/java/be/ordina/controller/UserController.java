package be.ordina.controller;

import be.ordina.security.AccountCredentials;
import be.ordina.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by KeLe on 5/05/2017.
 */
@RestController
@RequestMapping(value = RequestMappings.USERS)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BlockchainController blockchainController;

    @RequestMapping(value="/register",method = RequestMethod.POST)
    public boolean register(@RequestBody final UserRegistration userRegistration) {
        System.out.println("in the register ");
        AccountCredentials newUser = new AccountCredentials(userRegistration.getUsername(),userRegistration.getPassword(),userRegistration.getWalletID());


        boolean success = userService.createUser(newUser);
        if(success){
            //todo: toevoegen aan contractlijst
            //toevoegen aan contract in de lijst
            try{
           return blockchainController.addNewAdmin(newUser.getWalletID());
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
        }else {return false;}


    }

    public String getWalletIDcurrentUser(){
        return userService.getCurrentUser().getWalletID();
    }

    public String getWalletPassword() {

        return userService.getCurrentUser().getPassword();
    }


    public static class UserRegistration {
        String username;
        String password;
        String walletID;

        public UserRegistration(String username, String password) {
            this.username = username;
            this.password = password;
            this.walletID = "";
        }

        public UserRegistration(String username, String password, String walletID) {
            this.username = username;
            this.password = password;
            this.walletID = walletID;
        }

        public UserRegistration() {
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getWalletID() {
            return walletID;
        }
    }

}
