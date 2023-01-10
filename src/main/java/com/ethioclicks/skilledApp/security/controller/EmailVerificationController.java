package com.ethioclicks.skilledApp.security.controller;

import com.ethioclicks.skilledApp.security.entity.EmailSendToken;
import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.service.EmailVerificationService;
import com.ethioclicks.skilledApp.security.service.PasswordResetService;
import com.ethioclicks.skilledApp.security.service.UserRegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag( name = "Email Verification", description = "This Service Used for user email verification" )
@RestController
@RequestMapping( "/public" )
public class EmailVerificationController {

    @Autowired
    UserRegistrationService userRegistrationService;

    @Autowired
    EmailVerificationService emailVerificationService;

    @Autowired
    PasswordResetService passwordResetService;

    @PostMapping( "/send-email-conformation" )
    @SecurityRequirement( name = "bearerAuth" )
    @Operation( description = "This API verify user's email account" )
    public ResponseEntity<User> conformEmail( @RequestBody User user) throws Exception {


        if (emailVerificationService.isEmailExists(user.getUserName())) {
            return new ResponseEntity("Your Email Account is Already exists Please use another email", HttpStatus.CONFLICT);
        } else {
            passwordResetService.sendFirstRegistrationToken(user.getUserName());
            return new ResponseEntity("conformation email send to your email , pls check and conform", HttpStatus.OK);
        }
    }

    @GetMapping( value = "/checkToken" )
    @Operation( description = "This API receive reset password  token as Parameter and update password." )
    public ResponseEntity<String> checkToken(@RequestParam( "token" ) String token, @RequestParam( "email" )String email) throws Exception {
        EmailSendToken userToken = passwordResetService.getTokenByEmail(token, email);
        if(userToken==null){
            return new ResponseEntity("Incorrect token", HttpStatus.BAD_REQUEST);
        }
        if (passwordResetService.isTokenExpired(userToken.getCreatedDate())) {
            return new ResponseEntity(" Token is Expired ", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Correct Token", HttpStatus.OK);

   }

}
