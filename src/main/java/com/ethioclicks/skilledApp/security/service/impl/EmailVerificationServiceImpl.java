package com.ethioclicks.skilledApp.security.service.impl;


import com.ethioclicks.skilledApp.security.repo.UserRepo;
import com.ethioclicks.skilledApp.security.service.EmailVerificationService;
import com.ethioclicks.skilledApp.security.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;


@Service
public class EmailVerificationServiceImpl implements EmailVerificationService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserRegistrationService userRegistrationService;

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    JavaMailSenderImpl mailSender;

    @Autowired
    PasswordResetServiceImpl passwordResetService;

    @Override
    public boolean isEmailExists(String email) {

       return  userRepo.findByUserName(email)==null ? false:true;

    }
}
