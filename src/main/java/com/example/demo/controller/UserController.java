package com.example.demo.controller;

import com.example.demo.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
