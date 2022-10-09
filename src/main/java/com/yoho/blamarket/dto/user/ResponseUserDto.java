package com.yoho.blamarket.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseUserDto {

    private String email;
    private String name;
    private String company;
}
