package com.example.springsecurity_pr.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoSomethingController {

    @GetMapping("/doSomething")
    public String doSomething(@AuthenticationPrincipal String userId){

        return "인증된 ID : " + userId;
    }


}
