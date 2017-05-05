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
