package com.example.springsecurity_pr.controller;

import com.example.springsecurity_pr.dto.LoginDto;
import com.example.springsecurity_pr.dto.LoginSuccessDto;
import com.example.springsecurity_pr.dto.UserCreateDto;
import com.example.springsecurity_pr.jwt.TokenProvider;
import com.example.springsecurity_pr.userService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    //로그아웃 추가
    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());

        return ResponseEntity.ok().body("로그아웃이 되었습니다.");
    }


}
