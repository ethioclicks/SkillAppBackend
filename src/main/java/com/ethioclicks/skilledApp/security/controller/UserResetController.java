package com.ethioclicks.skilledApp.security.controller;

import com.ethioclicks.skilledApp.security.service.UserResetService;
import com.ethioclicks.skilledApp.security.model.UserResetModel;
import com.ethioclicks.skilledApp.security.service.UserRegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User Reset", description = "This Service Used for user reset username and password.")
@RestController
@RequestMapping("/public")
@Slf4j
public class UserResetController {

    @Autowired
    UserResetService userResetService;

    @Autowired
    UserRegistrationService userRegistrationService;


    @PostMapping("/user-name-reset")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(description = "This API receive User's Phone Number then reset User's username return the user's profile.")
    ResponseEntity resetUser(@Parameter(description = "User's Phone Number") @RequestBody UserResetModel userResetModel) {

        try {
            userResetService.userNameReset(userResetModel);
            return new ResponseEntity("Reset Success", HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/user-password-reset")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(description = "This API receive User's Phone Number then reset User's password and return the user's profile.")
    ResponseEntity resetUserPassword(@Parameter(description = "User's Phone Number") @RequestBody UserResetModel userResetModel) {

        try {
            userResetService.userPasswordReset(userResetModel);
            return new ResponseEntity("Reset Success", HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
