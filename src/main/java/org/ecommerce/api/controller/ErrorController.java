package org.ecommerce.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.ecommerce.api.model.DataResponse;
import org.ecommerce.api.model.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;

@RestControllerAdvice
public class ErrorController {

    @Autowired
    private HttpServletRequest request;

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<DataResponse<ErrorResponse>> apiException(ResponseStatusException exception) {
        return ResponseEntity.status(exception.getStatusCode())
                .body(DataResponse.<ErrorResponse>builder()
                        .api_version("v0.1")
                        .status_code(exception.getStatusCode().value())
                        .message("Failed, something wrong")
                        .data(new ErrorResponse(exception.getReason()))
                        .timestamp(String.valueOf(new Timestamp(System.currentTimeMillis())))
                        .path(request.getServletPath())
                        .build());
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<DataResponse<ErrorResponse>> constrintViolation(ConstraintViolationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(DataResponse.<ErrorResponse>builder()
                        .api_version("v0.1")
                        .status_code(HttpStatus.BAD_REQUEST.value())
                        .message("Failed, something wrong")
                        .data(new ErrorResponse(exception.getMessage()))
                        .timestamp(String.valueOf(new Timestamp(System.currentTimeMillis())))
                        .path(request.getServletPath())
                        .build());
    }
}
