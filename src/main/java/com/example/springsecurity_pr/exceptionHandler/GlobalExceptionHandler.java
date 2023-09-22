package com.example.springsecurity_pr.exceptionHandler;

import com.example.springsecurity_pr.errorDto.ErrorDto;
import com.example.springsecurity_pr.exception.AlreadyExistIdException;
import com.example.springsecurity_pr.util.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        List<ErrorDto> errorDtoList = new ArrayList<>();
        for(FieldError fieldError : bindingResult.getFieldErrors()){
            ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST, "VALIDATION_CHECK",fieldError.getDefaultMessage());
            errorDtoList.add(errorDto);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDtoList);
    }

    @ExceptionHandler(AlreadyExistIdException.class)
    public ResponseEntity<?> alreadyExistsId(AlreadyExistIdException e){
        log.error("AlreadyExistIdException :: " + e);
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity.status(errorCode.getHttpStatus()).body(ErrorDto.toErrorDto(errorCode));
    }
}
