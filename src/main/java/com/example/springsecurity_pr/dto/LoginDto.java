package com.example.springsecurity_pr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {

    @NotEmpty(message = "ID는 필수 입력값입니다.")
    private String username;
    @NotEmpty(message = "PASSWORD는 필수 입력값입니다.")
    private String password;

}
