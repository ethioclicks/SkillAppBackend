package com.ethioclicks.skilledApp.security.controller;

import com.ethioclicks.skilledApp.security.entity.ConfirmationToken;
import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.model.UserResetModel;
import com.ethioclicks.skilledApp.security.repo.ConfirmationTokenRepository;
import com.ethioclicks.skilledApp.security.repo.UserRepo;
import com.ethioclicks.skilledApp.security.service.UserRegistrationService;
import com.ethioclicks.skilledApp.security.service.UserResetService;
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

    @Autowired
    UserRepo userRepo;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;


    @PostMapping("/phone-number-check")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(description = "This API checks for user exist by the phone number")
    ResponseEntity checkPhoneNumber(@Parameter(description = "User's Phone Number") @RequestBody UserResetModel userResetModel) {

        try {
            if (userResetModel.getAttempt() < 3){
                if(userRegistrationService.isPhoneNumberExists(userResetModel.getPhoneNumber())){
                    userResetModel.setAttempt(0);
                    return new ResponseEntity("User Found", HttpStatus.OK);
                }else {
                    userResetModel.setAttempt(userResetModel.getAttempt()+1);
                    return new ResponseEntity("User Not Found | Attempt "+userResetModel.getAttempt(), HttpStatus.NOT_FOUND);
                }
            }else {
                return new ResponseEntity("Too many attempts: "+userResetModel.getAttempt(), HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

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

    @GetMapping(value = "/confirmAccount")
    @Operation(description = "This API receive User's confirmation token as Parameter and activate user's account.")
    public ResponseEntity<String> confirmUserAccount(@RequestParam("token") String confirmationToken) throws Exception {
    ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
    if (token != null) {
        User user = userRepo.findByUserName(token.getUser().getUserName());
        user.setEnabled(true);
        userRegistrationService.updateStatus(user);
        System.out.println("The account is activated");
        return new ResponseEntity("updated", HttpStatus.OK);
    }
    System.out.println("The link is invalid or broken!");
    return new ResponseEntity("not updated", HttpStatus.NOT_FOUND);
}


}
