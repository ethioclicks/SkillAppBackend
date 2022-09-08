package com.ethioclicks.skilledApp.security.controller;

import com.ethioclicks.skilledApp.security.model.MockUserResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/user")
@CrossOrigin("http://localhost:4200")
public class UserResource {
    @GetMapping("/resource")
    public ResponseEntity<MockUserResource> resource(){
        System.out.println("coming in the resource");
        MockUserResource responsebody=new MockUserResource("something","otherthing");
        return new ResponseEntity<MockUserResource>(
                responsebody, HttpStatus.OK);
    }

    @GetMapping("/dosomething")
    public String usertesting(){
        return "user Testing works";
    }

}
