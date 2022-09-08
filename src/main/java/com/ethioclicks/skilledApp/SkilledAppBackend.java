package com.ethioclicks.skilledApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SkilledAppBackend {

    public static void main(String[] args) {
        SpringApplication.run(SkilledAppBackend.class, args);
    }

}
