package com.yoho.blamarket.controller;


import com.yoho.blamarket.dto.user.RequestUserRegistDto;
import com.yoho.blamarket.service.UserService;
import com.yoho.blamarket.entity.UserEntity;
import com.yoho.blamarket.common.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<ApiResponse> regist(@RequestBody RequestUserRegistDto request){

        System.out.println(request.toString());
        ApiResponse apiResponse = userService.regist(request);

        // ResponseEntity로 하면 header정보도 전부 넘어가는것으로 알고 있음 .. 정확하진 않음 테스트 해봐야한다.
        return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
