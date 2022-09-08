package com.ethioclicks.skilledApp.security.service.impl;

import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.repo.UserRepo;
import com.ethioclicks.skilledApp.security.service.EmailVerificationService;
import com.ethioclicks.skilledApp.security.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

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

    @Override
    public boolean isEmailExists(String email) {

        User userEmail=userRepo.findByUserName(email);
        if(userEmail!=null)
            return true;
        return false;
    }

    @Override
    public User verifyEmail(String pid, String verifiedEmail) throws MessagingException {

        Context context = new Context();
        String verifyEmail = "";

        User user = userRegistrationService.getUser(pid);
        user.setVerifiedEmail(verifiedEmail);

        userRepo.save(user);

        context.setVariable("verifyemail", user);
        verifyEmail = templateEngine.process("verifyemail", context);

        javax.mail.internet.MimeMessage mimemessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimemessage, true);
        mimeMessageHelper.setSubject("Confirm Your Email Account");
        mimeMessageHelper.setText(verifyEmail, true);
        mimeMessageHelper.setTo(user.getVerifiedEmail());

        mailSender.send(mimemessage);

        return user;
    }

    public User verifiedEmail(String pid){

        Context context = new Context();
        String welcome = "";

        User user = userRegistrationService.getUser(pid);;
        user.setIsEmailVerified(true);

        context.setVariable("welcome", user);
        welcome = templateEngine.process("welcome", context);

        userRepo.save(user);

        return user;
    }
}
