package com.yoho.blamarket.jwt;

import com.yoho.blamarket.dto.user.JwtUserDto;
import com.yoho.blamarket.entity.UserEntity;
import com.yoho.blamarket.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT를 이용한 인증
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final UserRepository userRepository;

    public JwtAuthorizationFilter(
            AuthenticationManager authenticationManager,
            UserRepository userRepository
    ) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        String token = null;
        try {
            // header 에서 JWT token을 가져옵니다.
            String header = request.getHeader(JwtProperties.HEADER_STRING);
            if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
                chain.doFilter(request, response);
                return;
            }
            token = request.getHeader(JwtProperties.HEADER_STRING)
                    .replace(JwtProperties.TOKEN_PREFIX, "");
        } catch (Exception ignored) {
        }

        if (token != null) {
            Authentication authentication = getUsernamePasswordAuthenticationToken(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    /**
     * JWT 토큰으로 User를 찾아서 UsernamePasswordAuthenticationToken를 만들어서 반환한다.
     * User가 없다면 null
     */
    private Authentication getUsernamePasswordAuthenticationToken(String token) {
        String userEmail = JwtUtils.getUserEmail(token);
        if (userEmail != null) {
            UserEntity userEntity = userRepository.findByEmail(userEmail); // 유저를 유저명으로 찾습니다.
            JwtUserDto jwtUserDto = JwtUserDto.builder()
                    .email(userEntity.getEmail())
                    .password(userEntity.getPassword())
                    .authority(userEntity.getRoles())
                    .build()
                    ;
            return new UsernamePasswordAuthenticationToken(
                    jwtUserDto, // principal
                    null,
                    jwtUserDto.getAuthorities()
            );
        }
        return null; // 유저가 없으면 NULL
    }
}