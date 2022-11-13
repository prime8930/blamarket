package com.yoho.blamarket.controller;


import com.yoho.blamarket.common.ApiResponse;
import com.yoho.blamarket.dto.user.RequestEditUserDto;
import com.yoho.blamarket.dto.user.RequestUserRegistDto;
import com.yoho.blamarket.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "")
    public ResponseEntity<ApiResponse> regist(@RequestBody RequestUserRegistDto request){

        System.out.println(request.toString());
        ApiResponse apiResponse = userService.regist(request);

        // ResponseEntity로 하면 header정보도 전부 넘어가는것으로 알고 있음 .. 정확하진 않음 테스트 해봐야한다.
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse> getUser(String email){
        ApiResponse apiResponse = userService.getUser(email);

        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<ApiResponse> editUser(@RequestBody RequestEditUserDto requestEditUserDto){

        ApiResponse apiResponse = userService.editUser(requestEditUserDto);

        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<ApiResponse> deleteUter(@RequestBody RequestEditUserDto requestEditUserDto){

        ApiResponse apiResponse = userService.editUser(requestEditUserDto);

        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @GetMapping("jwt-user")
    public ResponseEntity<ApiResponse> getUserWithJwt(@RequestHeader("JWT-AUTHENTICATION") String jwtToken){

        log.info(jwtToken);
        ApiResponse apiResponse = userService.getUserWithJwtToken(jwtToken);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/token")
    public ResponseEntity<ApiResponse> refreshToken(HttpServletRequest request, HttpServletResponse response){

        ApiResponse apiResponse = userService.refreshJwtToken(request, response);

        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }



}
