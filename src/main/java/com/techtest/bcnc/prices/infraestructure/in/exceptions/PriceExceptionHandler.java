package com.techtest.bcnc.prices.infraestructure.in.exceptions;

import com.techtest.bcnc.prices.domain.exceptons.PriceNotFoundException;
import com.techtest.bcnc.prices.infraestructure.in.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@Hidden //Fue necesario colocar esta anotacion para que se pueda cargar de forma correcta el entorno grafico de Swagger
@RestControllerAdvice(basePackages = "com.techtest.bcnc.prices.infraestructure.in.controllers")
public class PriceExceptionHandler {
    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePriceNotFoundException(PriceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMismatch(MethodArgumentTypeMismatchException ex) {
        String message = String.format("El parámetro '%s' tiene un valor o formato inválido: '%s'", ex.getName(), ex.getValue());
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                message,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
