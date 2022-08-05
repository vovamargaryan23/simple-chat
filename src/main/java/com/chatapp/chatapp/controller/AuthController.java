package com.chatapp.chatapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("")
    public String main(){
        return "forward:/auth";
    }

    @GetMapping("/auth")
    public String auth(){

        return "auth";
    }
}
