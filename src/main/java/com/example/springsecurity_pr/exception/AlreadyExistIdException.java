package com.example.springsecurity_pr.exception;

import com.example.springsecurity_pr.util.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class AlreadyExistIdException extends RuntimeException {
    private ErrorCode errorCode;
}
