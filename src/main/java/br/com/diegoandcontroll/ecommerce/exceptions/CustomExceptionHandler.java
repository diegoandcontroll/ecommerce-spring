package br.com.diegoandcontroll.ecommerce.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException ex, WebRequest request) {
        String errorMessage = ex.getErrorMessage();
        HttpStatus status = ex.getStatus();
        String path = ex.getPath();
        
        CustomErrorResponse errorResponse = new CustomErrorResponse(errorMessage, path);
        
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
    }
}