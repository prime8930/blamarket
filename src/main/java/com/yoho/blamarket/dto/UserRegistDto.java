package com.yoho.blamarket.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserRegistDto {

    private String email;
    private String password;
    private String name;
}
