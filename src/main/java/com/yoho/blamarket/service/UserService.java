package com.yoho.blamarket.service;

import com.yoho.blamarket.repository.UserRepository;
import com.yoho.blamarket.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;



    public List<UserVo> getUserList(){
        List<UserVo> users = userRepository.findAll();
        return users;
    }

    public UserVo getUser(String email){
        System.out.println(email);
        return userRepository.findByEmail(email);
    }



}
