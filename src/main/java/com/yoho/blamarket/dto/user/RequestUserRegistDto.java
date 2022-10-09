package com.yoho.blamarket.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestUserRegistDto {

    private String email;
    private String password;
    private String name;
}
