package com.example.springsecurity_pr.controller;

import com.example.springsecurity_pr.dto.LoginDto;
import com.example.springsecurity_pr.dto.LoginSuccessDto;
import com.example.springsecurity_pr.dto.UserCreateDto;
import com.example.springsecurity_pr.jwt.TokenProvider;
import com.example.springsecurity_pr.userService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody UserCreateDto userCreateDto){
        String createdUserId = userService.create(userCreateDto);
        return ResponseEntity.ok().body("생성된 유저 ID : " + createdUserId);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginDto loginDto){
        LoginSuccessDto loginSuccessDto = userService
                .getByCredentials(loginDto.getUsername(), loginDto.getPassword());
        return ResponseEntity.ok().body(loginSuccessDto);
    }


}
