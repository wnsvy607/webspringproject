package com.seobpyo.webspringproject.web;

import com.seobpyo.webspringproject.web.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ControllerErrorAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleAllExceptions(){
        return new ErrorResponse("Error");
    }
}
