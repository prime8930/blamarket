package com.yoho.blamarket.service;

import com.yoho.blamarket.common.ApiResponse;
import com.yoho.blamarket.common.Util;
import com.yoho.blamarket.dto.user.RequestEditUserDto;
import com.yoho.blamarket.dto.user.RequestUserRegistDto;
import com.yoho.blamarket.dto.user.ResponseUserDto;
import com.yoho.blamarket.repository.UserRepository;
import com.yoho.blamarket.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

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
            UserEntity userEntity = userRepository.findByEmail(userRegistDto.getEmail());
            if (userEntity != null){
                log.info("userEntity is null");
                apiResponse = new ApiResponse(400, "회원 가입 실패");
                apiResponse.putData("error", "이미 가입된 회원입니다.");
                return apiResponse;
            }
            //dto로 받은 값을 Entity에 전달.
            userEntity = UserEntity.builder()
                    .email(userRegistDto.getEmail())
                    .password(userRegistDto.getPassword())
                    .name(userRegistDto.getName())
                    .build()
                    ;
            userEntity.encryptPassword(passwordEncoder);

            // insert
            userEntity = userRepository.save(userEntity);

            // entity그대로 리턴하면 비밀번호도 들어가니까 응답용 dto에 옮김
            ResponseUserDto responseUserRegistDto = ResponseUserDto.builder()
                    .email(userEntity.getEmail())
                    .name(userEntity.getName())
                    .company(userEntity.getCompany())
                    .build()
                    ;

            // api리스폰스를 만들어 리스폰스 규칙을 설정
            apiResponse = new ApiResponse(200, "회원 가입 성공");
            // api리스폰스에 리스폰스 데이터를 설정
            apiResponse.putData("data", responseUserRegistDto);


        } catch (Exception e){
            apiResponse = new ApiResponse(400, "회원 가입 실패");
            log.info(e.toString());

        }
        return apiResponse;
    }



    public List<UserEntity> getUserList(){
        List<UserEntity> users = userRepository.findAll();
        return users;
    }

    public ApiResponse getUser(String email){
        ApiResponse apiResponse = null;
        UserEntity userEntity = userRepository.findByEmail(email);

        ResponseUserDto responseUserDto = ResponseUserDto.builder()
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .company(userEntity.getCompany())
                .build()
                ;

        apiResponse = new ApiResponse(200, "회원 조회 성공");
        apiResponse.putData("user", responseUserDto);

        return apiResponse;
    }


    public ApiResponse editUser(RequestEditUserDto requestEditUserDto) {
        ApiResponse apiResponse = null;

        UserEntity userEntity = UserEntity.builder()
                .email(requestEditUserDto.getEmail())
                .password(requestEditUserDto.getPassword())
                .name(requestEditUserDto.getName())
                .company(requestEditUserDto.getCompany())
                .build()
                ;

        try{
            userEntity = userRepository.save(userEntity);
        }catch (Exception e){
            log.error(e.toString());
        }

        return apiResponse;
    }


    public ApiResponse deleteUser(String email) {
        ApiResponse apiResponse = null;

        UserEntity userEntity = UserEntity.builder()
                .email(email)
                .build()
                ;

        try{
            userEntity = userRepository.save(userEntity);
        }catch (Exception e){
            log.error(e.toString());
        }

        return apiResponse;
    }

//    private Boolean validateDuplicateUser(RequestUserRegistDto userRegistDto) {
//        Optional<UserEntity> optionalUserEntity = Optional.ofNullable(userRepository.findByEmail(userRegistDto.getEmail()));
//        return optionalUserEntity.ifPresent();
//        userEntity.ifPresent(findUser -> {
//            throw new CUserDuplicatedException();
//        });
//    }
}
