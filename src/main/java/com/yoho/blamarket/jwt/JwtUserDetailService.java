package com.yoho.blamarket.jwt;

import com.yoho.blamarket.dto.user.JwtUserDto;
import com.yoho.blamarket.entity.UserEntity;
import com.yoho.blamarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class JwtUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username);
        JwtUserDto jwtUserDto = JwtUserDto.builder()
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .authority(userEntity.getRoles())
                .build()
                ;
        return jwtUserDto;
    }
}
