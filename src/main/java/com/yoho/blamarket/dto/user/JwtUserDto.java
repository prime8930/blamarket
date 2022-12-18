package com.yoho.blamarket.dto.user;

import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JwtUserDto implements UserDetails {


    private String email;
    private String password;
    private String authority;
    private String state;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton((GrantedAuthority) () -> authority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        if(StringUtils.equalsIgnoreCase(state, "A")){
            return false;
        }
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if(StringUtils.equalsIgnoreCase(state, "L")){
            return false;
        }
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        if(StringUtils.equalsIgnoreCase(state, "P")){
            return false;
        }
        return true;
    }

    @Override
    public boolean isEnabled() {
        if(isCredentialsNonExpired() && isAccountNonExpired() && isAccountNonLocked()){
            return true;
        }
        return false;
    }


}
