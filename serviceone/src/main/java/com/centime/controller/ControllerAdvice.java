package com.centime.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.centime.exception.CentimeException;
import com.centime.modal.ErrorResponse;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerAdvice {
	
	
	private static final String VALIDATION = "VALIDATION_ERROR";
	private static final String INTERNAL_ERROR = "INTERNAL_SERVER_ERROR";
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> errors = result.getFieldErrors();

        return new ResponseEntity<>(new ErrorResponse(VALIDATION, processFieldError(errors), BAD_REQUEST), BAD_REQUEST);
    }
	
    private String processFieldError(List<FieldError> errors) {
        StringBuilder message = new StringBuilder();
        if (!CollectionUtils.isEmpty(errors)) {
            for (FieldError error : errors) {
                message.append(error.getDefaultMessage()).append(" | ");
            }
            message.setLength(message.length() - 1);
        }
        return message.toString();
    }
    
    @ExceptionHandler(CentimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleException(CentimeException ex) {
        return new ResponseEntity<>(new ErrorResponse(INTERNAL_ERROR, ex.getErrorMessage(), INTERNAL_SERVER_ERROR), INTERNAL_SERVER_ERROR);
    }

}
