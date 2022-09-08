package com.ethioclicks.skilledApp.businesslogic.exception;

import lombok.extern.java.Log;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@Log
public class ExceptionHandlerAdvise {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> genericException(Exception e){
        ExceptionResponse response=new ExceptionResponse();
        response.setMessage("Generic exception cause by:"+e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<ExceptionResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> badRequestException(BadRequestException e){
        ExceptionResponse response=new ExceptionResponse();
        response.setMessage("Cause by:"+e.getMessage());
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        e.printStackTrace();
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ExceptionResponse> badRequestException(InternalServerException e){
        ExceptionResponse response=new ExceptionResponse();
        response.setMessage("Cause by:"+e.getMessage());
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        e.printStackTrace();
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException e){

        Map<String, List<String>> body = new HashMap<>();

        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

    }


}
