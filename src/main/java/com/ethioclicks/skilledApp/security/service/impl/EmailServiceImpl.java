package com.ethioclicks.skilledApp.security.service.impl;

import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.model.EmailSendModel;
import com.ethioclicks.skilledApp.security.repo.UserRepo;
import com.ethioclicks.skilledApp.security.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
     UserRepo userRepo;
    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public boolean isEmailExists(String email) {
        User userEmail=userRepo.findByUserName(email);
        if(userEmail!=null)
            return true;
        return false;
    }

    @Override
    public String sendGenericEmail(String token, String email, String subject, String message) throws Exception {
        EmailSendModel emailSendModel= new EmailSendModel() ;
        emailSendModel.setPasswordToken(token);
        emailSendModel.setUserName(email);
        emailSendModel.setSubject(subject);
        emailSendModel.setMessage(message);

        Context context = new Context();

        context.setVariable("emailSendModel", emailSendModel);
        String process = templateEngine.process("sendToken",context);
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(process, true);
        mimeMessageHelper.setTo(emailSendModel.getUserName());

        javaMailSender.send(mimeMessage);
        return "sent";
    }
}
