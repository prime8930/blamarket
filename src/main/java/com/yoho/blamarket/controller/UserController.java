package com.yoho.blamarket.controller;


import com.yoho.blamarket.dto.UserRegistDto;
import com.yoho.blamarket.repository.UserRepository;
import com.yoho.blamarket.service.UserService;
import com.yoho.blamarket.entity.UserEntity;
import com.yoho.blamarket.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("")
    public UserEntity getUser(String email){
        System.out.println("controller : " + email);
        UserEntity userVo = userService.getUser(email);
        System.out.println(userVo.toString());
        return userVo;
    }


    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse regist(@RequestBody UserRegistDto request){

        System.out.println(request.toString());
        userService.regist(request);

        return new ApiResponse();
    }
}
