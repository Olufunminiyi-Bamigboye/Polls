package com.wayup.polls.advice;

import com.wayup.polls.dto.response.ErrorDetail;
import com.wayup.polls.exception.AppException;
import com.wayup.polls.exception.BadRequestException;
import com.wayup.polls.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorDetail> handleUserNotFoundException(AppException ex, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail(ex.getMessage(), LocalDateTime.now(), request.getDescription(false));

        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDetail> handleInvalidUserException(BadRequestException ex, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail(ex.getMessage(), LocalDateTime.now(), request.getDescription(false));

        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleInsufficientAmountException(ResourceNotFoundException ex, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail(ex.getMessage(), LocalDateTime.now(), request.getDescription(false));

        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }
}
