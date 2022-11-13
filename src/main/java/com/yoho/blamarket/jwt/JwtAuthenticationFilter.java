package com.yoho.blamarket.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoho.blamarket.common.ApiResponse;
import com.yoho.blamarket.dto.user.JwtUserDto;
import com.yoho.blamarket.dto.user.ResponseUserDto;
import com.yoho.blamarket.entity.UserEntity;
import com.yoho.blamarket.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * JWT를 이용한 로그인 인증
 */
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    /**
     * 로그인 인증 시도
     */
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException {

        // 로그인할 때 입력한 username과 password를 가지고 authenticationToken를 생성한다.
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                request.getParameter("email"),
                request.getParameter("password"),
                new ArrayList<>()
        );

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException {
        JwtUserDto jwtUserDto = (JwtUserDto) authResult.getPrincipal();
        String token = JwtUtils.createToken(jwtUserDto);
        // 헤더에 토큰 추가
        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+token);

        // 바디에 추가 리스폰스 내용 추가
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        ApiResponse apiResponse = new ApiResponse(200, "로그인 성공");

        // 유저 정보를 다시 긁어옴
        UserEntity userEntity = userRepository.findByEmail(jwtUserDto.getEmail());
        ResponseUserDto responseUserDto = ResponseUserDto.builder()
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .company(userEntity.getCompany())
                .build()
                ;

        // 데이터 넣기
        apiResponse.putData("data", responseUserDto);
        apiResponse.putData(JwtProperties.HEADER_STRING,
                 JwtProperties.TOKEN_PREFIX+token);

        String resString = mapper.writeValueAsString(apiResponse);
        response.getWriter().write(resString);
    }

    @Override
    protected void unsuccessfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException failed
    ) throws IOException {

        // 로그인 실패시 반환값
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        ApiResponse apiResponse = new ApiResponse(400, "아이디 또는 계정이 일치하지 않습니다.");

        String resString = mapper.writeValueAsString(apiResponse);
        response.getWriter().write(resString);
    }

}