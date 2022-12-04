package com.yoho.blamarket.service;


import com.yoho.blamarket.common.ApiResponse;
import com.yoho.blamarket.common.MailUtils;
import com.yoho.blamarket.common.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Slf4j
@Service
public class AuthService {

    // 이부분 고민해봐야것다 빈으로 등록이 안돼서 생긴는 문제인데
    // 어떻게 처리해야할지 모르것다.
//    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private RedisUtil redisUtil;

    public ApiResponse makeCode(String email) throws MessagingException, UnsupportedEncodingException {

//        ApiResponse apiResponse = null;
//        SimpleMailMessage message = new SimpleMailMessage();
        Random rand = new Random();
        String code = String.valueOf(rand.nextInt(1000000));


        MailUtils mailUtils = new MailUtils(mailSender);
        mailUtils.setFrom("fromtest@email.com", "테스트");
        mailUtils.setTo(email);
        mailUtils.setSubject("인증코드 발송");
        mailUtils.setText(code);
        mailUtils.send();

        redisUtil.setDataExpire(email, code, 180000);

//        message.setFrom("yoonsucker@gmail.com");
//        message.setTo(email);
//        message.setSubject("인증코드 발송");
//        message.setText("인증코드 생성 후 발송");
//        mailSender.send(message);

        ApiResponse apiResponse = new ApiResponse(200, "발송 성공");

        return apiResponse;
    }

    public ApiResponse checkCode(String email, String code){

        ApiResponse apiResponse;
        String redisCode = redisUtil.getData(email);
        log.info("get redis code : {}", redisCode);

        if (redisCode == null) {
            apiResponse = new ApiResponse(400, "인증시간 초과");
        } else if(redisCode.equals(code)){
            apiResponse = new ApiResponse(200, "인증번호가 일치합니다.");
            redisUtil.deleteData(email);
        } else{
            apiResponse = new ApiResponse(400, "인증번호가 일치하지 않습니다.");
        }

        return apiResponse;
    }
}
