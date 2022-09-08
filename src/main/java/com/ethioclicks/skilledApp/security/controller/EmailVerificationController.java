package com.ethioclicks.skilledApp.security.controller;

import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.service.EmailVerificationService;
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

    @PostMapping("/verify-email")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(description = "This API verify user's email account")
    public ResponseEntity<User> verifyEmail(@Parameter(description = "User's Verified Email") @RequestHeader("pid") String pid, @RequestBody User verifiedEmail) throws Exception {

        User user = userRegistrationService.getUser(pid);

        if(emailVerificationService.isEmailExists(user.getUserName())) {
           if (!user.getIsEmailVerified() == true){
               emailVerificationService.verifyEmail(pid, verifiedEmail.getVerifiedEmail());
               return new ResponseEntity ("Check your email and verify your account", HttpStatus.OK);
           }
           else {
               return new ResponseEntity ("Your Email Account is Already Verified", HttpStatus.OK);
           }
        }else{
            return new ResponseEntity("User Name Does Not Exist Change it and Try Again Please", HttpStatus.NOT_FOUND);
        }
    }

}
