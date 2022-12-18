package com.yoho.blamarket.dto.chat;


import lombok.Data;

@Data
public class RequestMessageDto {
    String sender;
    Long treadId;
    String content;
}
