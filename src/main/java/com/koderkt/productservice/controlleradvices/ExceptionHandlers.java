package com.koderkt.productservice.controlleradvices;

import com.koderkt.productservice.dtos.ExceptionDto;
import com.koderkt.productservice.exceptions.ProductNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(ProductNotExistException.class)
    public ResponseEntity<ExceptionDto> handleProductNotExistException(ProductNotExistException productNotExistException) {
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage(productNotExistException.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
