package com.example.demo.controller;

import com.example.demo.domain.user.UserEntity;
import com.example.demo.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping({"/","/index"})
    public String index(){
        return "index";
    }

    @GetMapping("/sign")
    public String sign(){
        return "sign";
    }

    @PostMapping("/sign/result")
    public String sign_result(@RequestParam(value="id") String id, @RequestParam(value="email") String email, @RequestParam(value="pw") String pw){
        userService.addUser(UserEntity.builder().id(id).email(email).password(pw).authority("MEMBER").build());
        return "index";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }
}
