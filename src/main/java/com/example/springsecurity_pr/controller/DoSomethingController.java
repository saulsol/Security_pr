package com.example.springsecurity_pr.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoSomethingController {

    @GetMapping("/doSomething")
    public String doSomething(@AuthenticationPrincipal String userId){
        if(userId == null){
            System.out.println("로그인을 먼저 해야합니다");
        }

        return "인증된 ID : " + userId;
    }

    // 레디스를 사용한다면 => jwt 토큰 블랙리스트 추가해야 함

}
