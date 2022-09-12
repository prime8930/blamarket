package com.yoho.blamarket.service;


import com.yoho.blamarket.common.ApiResponse;
import com.yoho.blamarket.common.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
public class AuthService {

    // 이부분 고민해봐야것다 빈으로 등록이 안돼서 생긴는 문제인데
    // 어떻게 처리해야할지 모르것다.
    @Autowired
    private JavaMailSenderImpl mailSender;

    public ApiResponse makeCode(String email) throws MessagingException, UnsupportedEncodingException {

        ApiResponse apiResponse = null;
//        SimpleMailMessage message = new SimpleMailMessage();

        MailUtils mailUtils = new MailUtils(mailSender);
        mailUtils.setFrom("fromtest@email.com", "테스트");
        mailUtils.setTo(email);
        mailUtils.setSubject("인증코드 발송");
        mailUtils.setText("인증코드 생성 후 발송");
        mailUtils.send();
//        message.setFrom("yoonsucker@gmail.com");
//        message.setTo(email);
//        message.setSubject("인증코드 발송");
//        message.setText("인증코드 생성 후 발송");
//        mailSender.send(message);

        apiResponse = new ApiResponse(HttpStatus.OK, "발송 성공");


        return apiResponse;
    }
}
