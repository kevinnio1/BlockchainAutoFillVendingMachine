package be.ordina.service;

import be.ordina.repository.IMongoModelEnabledRepository;
import be.ordina.security.AccountCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by KeLe on 8/05/2017.
 */
@Service
public class UserService {

    @Autowired
    private IMongoModelEnabledRepository mongoRespository;

    public boolean createUser(AccountCredentials user){

        try {

        mongoRespository.save(user);
            System.out.println("user saved");
        return true;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("error while creating user");
            return false;
        }
    }


}
