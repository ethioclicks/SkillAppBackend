package com.ethioclicks.skilledApp.security.service;


import org.springframework.stereotype.Service;



@Service
public interface EmailVerificationService {

    boolean isEmailExists(String email);
}
