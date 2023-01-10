package com.ethioclicks.skilledApp.security.controller;


import com.ethioclicks.skilledApp.security.service.EmailVerificationService;
import com.ethioclicks.skilledApp.security.service.UserRegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WelcomeController {

    @Autowired
    UserRegistrationService userRegistrationService;

    @Autowired
    EmailVerificationService emailVerificationService;

    @GetMapping({"/", "/welcome"})
    @SecurityRequirement(name = "bearerAuth")
    @Operation(description = "This API confirm user's email account")
    public String welcome(Model model) {

        model.addAttribute("welcome", "Hi");

        return "welcome";
    }

   }
