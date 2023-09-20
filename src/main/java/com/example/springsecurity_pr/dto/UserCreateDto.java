package com.example.springsecurity_pr.dto;

import com.example.springsecurity_pr.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDto {

    @NotEmpty(message = "ID는 필수 입력값입니다.")
    private String username;
    @NotEmpty(message = "PASSWORD는 필수 입력값입니다.")
    private String password;

    @NotNull(message = "역할은 필수 입력값입니다.")
    private String role;

    @NotNull(message = "토큰값은 필수입니다.")
    private String token;
    public UserEntity toEntity(){

        return UserEntity.builder()
                .username(username)
                .password(password)
                .role(role)
                .build();

    }
}
