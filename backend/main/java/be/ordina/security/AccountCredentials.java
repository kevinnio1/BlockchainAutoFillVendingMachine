package be.ordina.security;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by KeLe on 5/05/2017.
 */

public class AccountCredentials {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("walletID")
    private String walletID;

    public AccountCredentials(String username, String password, String walletID) {
        this.username = username;
        this.password = password;
        this.walletID = walletID;
    }

    public void setWalletID(String walletID) {
        this.walletID = walletID;
    }

    public String getWalletID() {

        return walletID;
    }



   public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {

        return username;
    }

    public String getPassword() {
        return password;
    }
}
