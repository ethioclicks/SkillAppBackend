package com.ethioclicks.skilledApp.security.service.impl;

import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.model.EmailSendModel;
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
    TemplateEngine templateEngine;
    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public String sendEmail(User savedUser) throws Exception {
        Context context = new Context();
        context.setVariable("user", savedUser);
        String process = templateEngine.process("emails/welcome", context);
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("Welcome " + savedUser.getFirstName());
        helper.setText(process, true);
        helper.setTo(savedUser.getUserName());
        helper.setFrom("no-reply@betdelala.com");
        javaMailSender.send(mimeMessage);

        return "Sent";
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
        String process = templateEngine.process("emails/sendToken",context);
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(process, true);
        mimeMessageHelper.setTo(emailSendModel.getUserName());

        javaMailSender.send(mimeMessage);
        return "sent";
    }
}
