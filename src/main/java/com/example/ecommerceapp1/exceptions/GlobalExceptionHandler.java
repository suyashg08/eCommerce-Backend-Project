package com.example.ecommerceapp1.exceptions;

import com.example.ecommerceapp1.dtos.ErrorDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ErrorDto handleNullPointerExceptions() {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus("Failure");
        errorDto.setMessage("Null pointer exeption occured");

        return errorDto;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorDto handleProductNotFoundException(
            ProductNotFoundException productNotFoundException){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus("Failure");
        errorDto.setMessage(productNotFoundException.getMessage());

        return errorDto;
    }
}
