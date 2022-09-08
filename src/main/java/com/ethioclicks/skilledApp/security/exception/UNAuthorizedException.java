package com.ethioclicks.skilledApp.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.UNAUTHORIZED, reason="Unauthorized")
public class UNAuthorizedException extends Exception {

    public UNAuthorizedException(String msg){
        super(msg);
    }
}
