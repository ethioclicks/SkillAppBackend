package com.ethioclicks.skilledApp.security.controller;

import com.ethioclicks.skilledApp.security.entity.EmailSendToken;
import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.model.ResetPasswordModel;
import com.ethioclicks.skilledApp.security.repo.PasswordTokenRepo;
import com.ethioclicks.skilledApp.security.repo.UserRepo;
import com.ethioclicks.skilledApp.security.service.EmailService;
import com.ethioclicks.skilledApp.security.service.PasswordResetService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping( "/public" )
public class UserPasswordResetController {

    @Autowired
    PasswordResetService passwordResetService;
    @Autowired
    PasswordTokenRepo passwordTokenRepo;

    @Autowired
    EmailService emailService;

    @Autowired
    UserRepo userRepo;

    @PostMapping( "/forget_password" )
    public ResponseEntity resetUserPassword(@RequestBody User user) throws Exception {

        String token = passwordResetService.sendPasswordResetToken(user.getUserName());
        if (token.equals("not found")) {
            return  new ResponseEntity("User with email provided is not found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("We have sent a reset password code to your email Please check ", HttpStatus.OK);

    }

    @PostMapping( value = "/reset-password" )
    @Operation( description = "This API receive reset password  token as Parameter and update password." )
    public ResponseEntity<String> processResetPassword( @RequestBody ResetPasswordModel resetPasswordModel) throws Exception {

        EmailSendToken userToken = passwordResetService.getTokenByEmail(resetPasswordModel.getToken(), resetPasswordModel.getEmail());
        if (userToken != null && userToken.getEmail() != null) {
            User userFound = userRepo.findByUserName(userToken.getEmail());
            passwordResetService.resetPassword(userFound, resetPasswordModel.getPassword());
            return new ResponseEntity("Reset Successfully ", HttpStatus.OK);
        }
        return new ResponseEntity("Token not found", HttpStatus.NOT_FOUND);
    }
}
