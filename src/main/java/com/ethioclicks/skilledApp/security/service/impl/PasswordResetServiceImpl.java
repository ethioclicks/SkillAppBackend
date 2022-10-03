package com.ethioclicks.skilledApp.security.service.impl;

import com.ethioclicks.skilledApp.security.entity.EmailSendToken;
import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.repo.PasswordTokenRepo;
import com.ethioclicks.skilledApp.security.repo.UserRepo;
import com.ethioclicks.skilledApp.security.service.EmailService;
import com.ethioclicks.skilledApp.security.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;



@Service
public class PasswordResetServiceImpl implements PasswordResetService {
    @Autowired
    PasswordTokenRepo passwordTokenRepo;
    @Autowired
    EmailService emailService;
    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordResetService passwordResetService;

    @Value( "${expiration_time}" )
    private int expiration_time;
    @Value( "${subject_password_reset}" )
    private String subject_password_reset;
    @Value( "${message_password_reset}" )
    private String message_password_reset;
    @Value( "${message_email_verification}" )
    private String message_email_verification;
    @Value( "${subject_email_verification}" )
    private String subject_email_verification;


    @Override
    public String sendFirstRegistrationToken(String email) throws Exception {
        String tokenString = null;

        EmailSendToken token = passwordTokenRepo.findByEmail(email);

        if (token == null) {
            token = new EmailSendToken();
        }
        tokenString = UUID.randomUUID().toString().substring(0, 6);
        token.setToken(tokenString);
        token.setCreatedDate(LocalDateTime.now());
        token.setEmail(email);
        passwordTokenRepo.save(token);
        emailService.sendGenericEmail(tokenString, email, subject_email_verification, message_email_verification);

        return tokenString;
    }

    @Override
    public void resetUserName(User userChange, String newEmail) {
        User user = userRepo.findByUserName(userChange.getUserName());
        EmailSendToken emailSendToken = passwordTokenRepo.findByEmail(user.getUserName());
        if (emailSendToken != null) {
            user.setUserName(newEmail);
            passwordTokenRepo.delete(emailSendToken);
            userRepo.save(user);
        }
    }

    @Override
    public EmailSendToken getTokenByEmail(String token, String email) {
        return passwordTokenRepo.findByTokenAndEmail(token, email);
    }

    @Override
    public void resetPassword(User user, String newPassword) {
        user.setPassWord(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
        User user1 = userRepo.findByUserName(user.getUserName());
        EmailSendToken emailSendToken = passwordTokenRepo.findByEmail(user1.getUserName());
        if (emailSendToken != null) {
            passwordTokenRepo.delete(emailSendToken);
            userRepo.save(user);
        }
    }

    @Override
    public boolean isTokenExpired(LocalDateTime tokenCreationDate) {
        LocalDateTime now = LocalDateTime.now();
        return tokenCreationDate.plusMinutes(expiration_time).isBefore(now);

    }

    @Override
    public boolean isValidToken(String token, String email) {
        EmailSendToken userToken = passwordResetService.getTokenByEmail(token, email);
        return userToken != null;
    }
}