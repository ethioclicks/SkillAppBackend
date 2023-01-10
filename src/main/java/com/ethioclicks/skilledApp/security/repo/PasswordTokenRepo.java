package com.ethioclicks.skilledApp.security.repo;

import com.ethioclicks.skilledApp.security.entity.EmailSendToken;
import org.springframework.data.repository.CrudRepository;

public interface PasswordTokenRepo extends CrudRepository<EmailSendToken, Integer> {
    EmailSendToken findByTokenAndEmail(String passwordToken,String email);
//    EmailSendToken findByUserId(Integer id);
    EmailSendToken findByEmail(String emailAddress);

}
