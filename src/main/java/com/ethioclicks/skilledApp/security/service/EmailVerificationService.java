package com.ethioclicks.skilledApp.security.service;

import com.ethioclicks.skilledApp.security.entity.User;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public interface EmailVerificationService {

    boolean isEmailExists(String email);
    User verifyEmail(String pid, String verifiedEmail) throws MessagingException;
    User verifiedEmail(String pid);
}
