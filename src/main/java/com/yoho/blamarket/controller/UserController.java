package com.yoho.blamarket.controller;


import com.yoho.blamarket.repository.UserRepository;
import com.yoho.blamarket.service.UserService;
import com.yoho.blamarket.vo.UserVo;
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
    public List<UserVo> login(){

        List<UserVo> userList = userService.getUserList();

        return userList;
    }

    @GetMapping("/user")
    public UserVo getUser(String email){
        System.out.println("controller : " + email);
        UserVo userVo = userService.getUser(email);
        System.out.println(userVo.toString());
        return userVo;
    }


    @PostMapping("/login")
    public String login(@ModelAttribute UserVo user){

        return "abc";
    }
}
