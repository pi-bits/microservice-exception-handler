package com.serviceinfotech.services.microserviceexceptionhandler.advice;

import com.serviceinfotech.services.microserviceexceptionhandler.exceptions.AccountNumberNotFound;
import com.serviceinfotech.services.microserviceexceptionhandler.exceptions.InvalidAccountNumber;
import com.serviceinfotech.services.microserviceexceptionhandler.model.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidAccountNumber.class)
    public ResponseEntity<Object> handle4XXException(InvalidAccountNumber ex, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(400,ex.getMessage());
        return handleExceptionInternal(ex, errorDetails,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(AccountNumberNotFound.class)
    public ResponseEntity<Object> handle5XXException(AccountNumberNotFound ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(500,ex.getMessage());
        return handleExceptionInternal(ex, errorDetails,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
