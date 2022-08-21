package com.yoho.blamarket.service;

import com.yoho.blamarket.repository.UserRepository;
import com.yoho.blamarket.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;



    public List<UserEntity> getUserList(){
        List<UserEntity> users = userRepository.findAll();
        return users;
    }

    public UserEntity getUser(String email){
        System.out.println(email);
        return userRepository.findByEmail(email);
    }



}
