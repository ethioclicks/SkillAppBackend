package com.ethioclicks.skilledApp.security.controller;

import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.exception.UNAuthorizedException;
import com.ethioclicks.skilledApp.security.model.AuthenticationRequest;
import com.ethioclicks.skilledApp.security.model.CustomUserDetails;
import com.ethioclicks.skilledApp.security.model.NewUserDetail;
import com.ethioclicks.skilledApp.security.model.TokenPayLoad;
import com.ethioclicks.skilledApp.security.repo.UserRepo;
import com.ethioclicks.skilledApp.security.service.UserRegistrationService;
import com.ethioclicks.skilledApp.security.utils.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

@Tag(name = "Authentication Controller", description = "This Service Used for user registration and authentication.")
@RestController
@RequestMapping("/public")
public class AuthenticationController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserRegistrationService userRegistrationService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/authenticate")
    @Operation(description = "This API receive user's User Name and  Password and return the user's profile.")
    public TokenPayLoad authenticate(@Parameter(description = "User's Public Id") @RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUserName(),
                            authenticationRequest.getPassword()));
        } catch (Exception e) {
            throw new UNAuthorizedException("Username or/and password is not correct.");
        }
        User userByUserName = userRepo.findByUserName(authenticationRequest.getUserName());
        CustomUserDetails customUserDetails = new CustomUserDetails(userByUserName);
        String generatedToken = jwtTokenUtil.generateToken(customUserDetails);
        return new TokenPayLoad(generatedToken,userByUserName);

    }
    @PostMapping(value = "/createUser")
    @Operation(description = "This API receive User's Information and then Create New USer and return the user's profile.")
    ResponseEntity<NewUserDetail> createUser(@Parameter(description = "User's Information") @Valid @RequestBody NewUserDetail userDetail) throws Exception{
        NewUserDetail savedUserDetail=null;
        Map<String, String> checkingResult = userRegistrationService.checkUserData(userDetail);

        if(!checkingResult.isEmpty()) {
            return new ResponseEntity(checkingResult, HttpStatus.NOT_ACCEPTABLE);
        }
        savedUserDetail = userRegistrationService.saveUser(userDetail);
        return ResponseEntity.created(new URI("/users/createUser"))
                .body(savedUserDetail);

    }

    @PostMapping(value = "/checkUserData")
    @Operation(description = "This API receive User's Information and then Create New USer and return the user's profile.")
    ResponseEntity<Map<String,String>> checkUserModel(@Parameter(description = "User's Information") @RequestBody NewUserDetail userDetail) throws Exception {
        Map<String, String> checkingResult = userRegistrationService.checkUserData(userDetail);
        if(checkingResult.isEmpty()) {
            return new ResponseEntity(checkingResult, HttpStatus.OK);
        } else {
            return new ResponseEntity(checkingResult, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    }
