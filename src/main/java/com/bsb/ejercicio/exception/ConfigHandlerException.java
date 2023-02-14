package com.bsb.ejercicio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ConfigHandlerException {

    @ExceptionHandler(ElementNotFound.class)
    public ResponseEntity<?> handleElementNotFound(HttpServletRequest request,
                                                   ElementNotFound e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(buildResponse(e, HttpStatus.NOT_FOUND));
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleEnteredDataNotFound(HttpServletRequest request,
                                                       NotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(buildResponse(e, HttpStatus.NOT_FOUND));
    }
    @ExceptionHandler(ErrorProcessException.class)
    public ResponseEntity<?> handleEnteredDataConflict(HttpServletRequest request,
                                                       ErrorProcessException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildResponse(e, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequest(HttpServletRequest request, BadRequestException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildResponse(e, HttpStatus.BAD_REQUEST));
    }
    //Resonse Error
    private ErrorResponse buildResponse(Exception e, HttpStatus httpStatus) {
        return new ErrorResponse(e, httpStatus.value());
    }

    private ErrorResponse buildResponse(String message, HttpStatus httpStatus) {
        return new ErrorResponse(message, httpStatus.value());
    }
}
