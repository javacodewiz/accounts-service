package com.app.exception;


import com.app.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {




    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException exception, WebRequest request)
    {
        ErrorDto dto = new ErrorDto(
                request.getDescription(false),
        HttpStatus.BAD_REQUEST,
        exception.getMessage(),
                LocalDateTime.now());

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> handleCustomerNotFoundException(ResourceNotFoundException exception, WebRequest request)
    {
        ErrorDto dto = new ErrorDto(
                request.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now());

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGlobalException(Exception exception, WebRequest request)
    {
        ErrorDto dto = new ErrorDto(
                request.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now());

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

}
