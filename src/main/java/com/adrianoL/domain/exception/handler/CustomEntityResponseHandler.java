package com.adrianoL.domain.exception.handler;

import com.adrianoL.domain.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class CustomEntityResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> resourceNotFoundException(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public final ResponseEntity<ExceptionResponse> userAlreadyExistsException(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(StorageException.class)
    public final ResponseEntity<ExceptionResponse> storageException(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> "'" + error.getField() + "' " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                errorMessage,
                request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
