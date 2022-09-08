package com.ethioclicks.skilledApp.security.controller;

import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.model.AuthenticationRequest;
import com.ethioclicks.skilledApp.security.model.UserProfileModel;
import com.ethioclicks.skilledApp.security.service.UserRegistrationService;
import com.ethioclicks.skilledApp.security.service.UserProfileService;
import com.ethioclicks.skilledApp.security.repo.UserRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "User Profile Controller", description = "This Service Used for user verification and change password.")
@RestController
@RequestMapping("/user")
public class UserProfileController {

    @Autowired
    UserRegistrationService userRegistrationService;

    @Autowired
    BCryptPasswordEncoder bycryptPasswordEncoder;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserProfileService userProfileService;

    @GetMapping("")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(description = "This API receive User's Public Id as Header Parameter and return the user's profile.")
    ResponseEntity<UserProfileModel> getUserProfile(@Parameter(description = "User's Public Id") @RequestHeader(value = "pid",required = true) String pid)
    {
        return new ResponseEntity(userProfileService.getUserProfile(pid),null,HttpStatus.OK);
    }

    @PostMapping("")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(description = "This API receive User's Public Id as Header Parameter, User's Information as Request Body then change/update User's Info and return the user's profile.")
    ResponseEntity<UserProfileModel> updateUserProfile(@Parameter(description = "User's Public Id") @RequestHeader(value = "pid",required = true) String pid, @RequestBody UserProfileModel userProfileModel)
    {
        return new ResponseEntity(userProfileService.updateUserProfile(pid, userProfileModel),null, HttpStatus.OK);
    }

    @PostMapping(value = "/updatePassword")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(description = "This API receive User's Public Id as Header Parameter and User's User Name and Password as Request Body and then change/update user's password.")
    ResponseEntity<String> createUser(@Parameter(description = "User's Public Id, username & password") @RequestHeader(value = "pid",required = true) String pid, @RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        System.out.println("it is coming to save the user");
        Optional<User> userByUserName = userRepo.findByUserPublicId(pid);
        if (userByUserName.isPresent()) {
            User user=userByUserName.get();
            user.setPassWord(authenticationRequest.getPassword());
            User updatedUser = userRegistrationService.saveUser(user);
            if (updatedUser != null) {
                return new ResponseEntity("updated", null, HttpStatus.OK);
            }
        }
        return new ResponseEntity("not updated", HttpStatus.NOT_FOUND);
    }

}
