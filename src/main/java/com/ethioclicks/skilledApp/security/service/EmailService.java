package com.ethioclicks.skilledApp.security.service;

public interface EmailService {
    boolean isEmailExists(String email);
    String sendGenericEmail(String token, String email, String subject, String message) throws Exception;

}
