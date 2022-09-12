package com.yoho.blamarket.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestEditUserDto {

    private String email;
    private String password;
    private String name;
    private String company;
}
