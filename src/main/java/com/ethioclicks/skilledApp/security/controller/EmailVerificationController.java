package com.ethioclicks.skilledApp.security.controller;

import com.ethioclicks.skilledApp.security.entity.EmailSendToken;
import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.service.EmailService;
import com.ethioclicks.skilledApp.security.service.EmailVerificationService;
import com.ethioclicks.skilledApp.security.service.PasswordResetService;
import com.ethioclicks.skilledApp.security.service.UserRegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Email Verification", description = "This Service Used for user email verification")
@RestController
@RequestMapping("/public")
public class EmailVerificationController {

    @Autowired
    UserRegistrationService userRegistrationService;

    @Autowired
    EmailVerificationService emailVerificationService;

    @Autowired
    EmailService emailService;

    @Autowired
    PasswordResetService passwordResetService;
    @PostMapping( "/send-email-conformation" )
    @SecurityRequirement( name = "bearerAuth" )
    @Operation( description = "This API verify user's email account" )
    public ResponseEntity<User> conformEmail(@Parameter( description = "User's conform Email" ) @RequestBody User user) throws Exception {

        if (emailService.isEmailExists(user.getUserName())) {
            return new ResponseEntity("Your Email Account is Already exists Please use another email", HttpStatus.CONFLICT);
        } else {
            passwordResetService.sendFirstRegistrationToken(user.getUserName());
            return new ResponseEntity("conformation email send to your email , pls check and conform", HttpStatus.OK);
        }
    }
    @GetMapping( value = "/check-token" )
    @Operation( description = "This API receive reset password  token as Parameter and update password." )
    public ResponseEntity<String> checkToken(@RequestParam( "token" ) String token, @RequestParam( "email" ) String email) throws Exception {
        EmailSendToken userToken = passwordResetService.getTokenByEmail(token, email);
        if(userToken==null){
            return new ResponseEntity("Incorrect token", HttpStatus.BAD_REQUEST);
        }
        if (passwordResetService.isTokenExpired(userToken.getCreatedDate())) {
            return new ResponseEntity(" Token is Expired ", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Correct Token", HttpStatus.OK);

    }
    @PostMapping("/verify-email")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(description = "This API verify user's email account")
    public ResponseEntity<User> verifyEmail(@Parameter(description = "User's Verified Email") @RequestHeader("pid") String pid, @RequestBody User verifiedEmail) throws Exception {

        User user = userRegistrationService.getUser(pid);
        if (user != null) {
            if (emailVerificationService.isEmailExists(user.getUserName())) {
                if (!user.getIsEmailVerified() == true) {
                    emailVerificationService.verifyEmail(pid, verifiedEmail.getVerifiedEmail());
                    return new ResponseEntity("Check your email and verify your account", HttpStatus.OK);
                } else {
                    return new ResponseEntity("Your Email Account is Already Verified", HttpStatus.OK);
                }
            } else {
                return new ResponseEntity("User Name Does Not Exist Change it and Try Again Please", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity("User Does Not Exist  Try Again Please", HttpStatus.NOT_FOUND);
    }
}
