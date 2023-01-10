package com.ethioclicks.skilledApp.security.service;

import com.ethioclicks.skilledApp.security.entity.User;

public interface EmailService {

    String sendEmail(User savedUser) throws Exception;
    String sendGenericEmail(String token, String email, String subject, String message) throws Exception;
    
}
