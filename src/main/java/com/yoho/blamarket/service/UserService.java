package com.yoho.blamarket.service;

import com.yoho.blamarket.dto.UserRegistDto;
import com.yoho.blamarket.repository.UserRepository;
import com.yoho.blamarket.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    public List<UserEntity> getUserList(){
        List<UserEntity> users = userRepository.findAll();
        return users;
    }

    public UserEntity getUser(String email){
        System.out.println(email);
        return userRepository.findByEmail(email);
    }

    public String regist(UserRegistDto userRegistDto){

        UserEntity userEntity = UserEntity.builder()
                .email(userRegistDto.getEmail())
                .password(userRegistDto.getPassword())
                .name(userRegistDto.getName())
                .build()
                ;

        userEntity.encryptPassword(passwordEncoder);

        userRepository.save(userEntity);

        return userEntity.getEmail();
    }



}
