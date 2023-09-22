package com.example.springsecurity_pr.dto;

import com.example.springsecurity_pr.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    @NotEmpty(message = "역할은 필수 입력값입니다.")
    private String role;


    public UserEntity toEntity(PasswordEncoder passwordEncoder){

        return UserEntity.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(role)
                .build();

    }
}
