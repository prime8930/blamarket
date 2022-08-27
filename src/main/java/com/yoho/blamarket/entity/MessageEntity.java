package com.yoho.blamarket.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="message")
public class MessageEntity {

    @Id
    long id;

    @Column(nullable = false)
    long tradeId;

    String isFromSender;

    @Column(columnDefinition = "TEXT")
    String content;

    String sender;

    boolean read_flag;

    String sendTime;
}
