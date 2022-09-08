package com.ethioclicks.skilledApp.businesslogic.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ExceptionResponse {
    private String message;
    private HttpStatus statusCode;

}
