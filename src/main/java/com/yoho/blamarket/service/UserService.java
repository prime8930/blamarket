package com.yoho.blamarket.service;

import com.yoho.blamarket.common.ApiResponse;
import com.yoho.blamarket.dto.user.RequestUserRegistDto;
import com.yoho.blamarket.dto.user.ResponseUserRegistDto;
import com.yoho.blamarket.repository.UserRepository;
import com.yoho.blamarket.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {


    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }



    public ApiResponse regist(RequestUserRegistDto userRegistDto){

        ApiResponse apiResponse = null;

        try{
            //dto로 받은 값을 Entity에 전달.
            UserEntity userEntity = UserEntity.builder()
                    .email(userRegistDto.getEmail())
                    .password(userRegistDto.getPassword())
                    .name(userRegistDto.getName())
                    .build()
                    ;
            userEntity.encryptPassword(passwordEncoder);

            // insert
            userEntity = userRepository.save(userEntity);

            // entity그대로 리턴하면 비밀번호도 들어가니까 응답용 dto에 옮김
            ResponseUserRegistDto responseUserRegistDto = ResponseUserRegistDto.builder()
                    .email(userEntity.getEmail())
                    .name(userEntity.getName())
                    .company(userEntity.getCompany())
                    .build()
                    ;

            // api리스폰스를 만들어 리스폰스 규칙을 설정
            apiResponse = new ApiResponse(HttpStatus.OK, "회원 가입 성공");
            // api리스폰스에 리스폰스 데이터를 설정
            apiResponse.putData("user", responseUserRegistDto);


        } catch (Exception e){
            apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST, "회원 가입 실패");
            log.info(e.toString());

        }
        return apiResponse;
    }



    public List<UserEntity> getUserList(){
        List<UserEntity> users = userRepository.findAll();
        return users;
    }

    public UserEntity getUser(String email){
        System.out.println(email);
        return userRepository.findByEmail(email);
    }


}
