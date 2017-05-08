package be.ordina.repository;

import be.ordina.security.AccountCredentials;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * Created by KeLe on 8/05/2017.
 */
public interface IMongoModelEnabledRepository extends MongoRepository<AccountCredentials,String> {

    public AccountCredentials findByUsername(String s);
}
