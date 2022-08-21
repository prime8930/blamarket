package com.yoho.blamarket.controller;


import com.yoho.blamarket.repository.UserRepository;
import com.yoho.blamarket.service.UserService;
import com.yoho.blamarket.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public List<UserEntity> login(){

        List<UserEntity> userList = userService.getUserList();

        return userList;
    }

    @GetMapping("/user")
    public UserEntity getUser(String email){
        System.out.println("controller : " + email);
        UserEntity userVo = userService.getUser(email);
        System.out.println(userVo.toString());
        return userVo;
    }


    @PostMapping("/login")
    public String login(@ModelAttribute UserEntity user){

        return "abc";
    }
}
