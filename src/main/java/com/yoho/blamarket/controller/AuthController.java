package com.yoho.blamarket.controller;

import com.yoho.blamarket.common.ApiResponse;
import com.yoho.blamarket.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @GetMapping("/{email}")
    public ResponseEntity<ApiResponse> makeCode(@PathVariable String email) throws Exception {


        System.out.println("email " + email);
        ApiResponse apiResponse = authService.makeCode(email);

        // ResponseEntity로 하면 header정보도 전부 넘어가는것으로 알고 있음 .. 정확하진 않음 테스트 해봐야한다.
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{email}/{code}")
    public ResponseEntity<ApiResponse> checkCode(@PathVariable String email, @PathVariable String code){

        ApiResponse apiResponse = authService.checkCode(email, code);
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }
}
