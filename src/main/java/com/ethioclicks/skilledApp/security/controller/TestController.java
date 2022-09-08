package com.ethioclicks.skilledApp.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/aboutus")
    public String aboutus(){
        return "aboutus Testing works";
    }



    @GetMapping("/admin/addadmin")
    public String admintesting(){
        return "admin Testing works";
    }


}
