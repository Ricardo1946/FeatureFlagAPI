package FeatureFlagAPI.ApiFlag.controller.advice;


import FeatureFlagAPI.ApiFlag.common.ErrorResponse;
import FeatureFlagAPI.ApiFlag.common.exception.MethodArgumentNotValidException;
import FeatureFlagAPI.ApiFlag.common.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(org.springframework.web.bind.MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> listErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();


        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                "Error en validación",
                HttpStatus.BAD_REQUEST.value(),
                "BAD_REQUEST",
                request.getRequestURI(),
                listErrors
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                "Recurso no encontrado",
                HttpStatus.NOT_FOUND.value(),
                "NOT_FOUND",
                request.getRequestURI(),
                null

        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


}
