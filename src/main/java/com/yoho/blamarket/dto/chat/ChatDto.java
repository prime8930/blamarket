package com.yoho.blamarket.dto.chat;

import lombok.Data;

@Data
public class ChatDto {

    private int id;
    private String seller;
    private String buyer;
    private String imgUrl;
    private String messageSender;
    private String time;
    private String message;
    private String messageCnt;

}
