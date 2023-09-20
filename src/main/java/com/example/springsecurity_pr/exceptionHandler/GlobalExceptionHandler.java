package com.example.springsecurity_pr.exceptionHandler;

import com.example.springsecurity_pr.errorDto.ErrorDto;
import com.example.springsecurity_pr.exception.AlreadyExistIdException;
import com.example.springsecurity_pr.util.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> handleValidationException(MethodArgumentNotValidException ex){
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(
                error -> {
                    errors.add(error.getDefaultMessage());
                }
        );
        return errors;
    }

    @ExceptionHandler(AlreadyExistIdException.class)
    public ResponseEntity<?> alreadyExistsId(AlreadyExistIdException e){
        log.error("TicketException :: " + e);
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity.status(errorCode.getHttpStatus()).body(ErrorDto.toErrorDto(errorCode));
    }
}
