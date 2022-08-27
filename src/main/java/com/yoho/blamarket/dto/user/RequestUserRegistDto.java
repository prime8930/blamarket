package com.yoho.blamarket.dto.user;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
public class RequestUserRegistDto {

    private String email;
    private String password;
    private String name;
}
