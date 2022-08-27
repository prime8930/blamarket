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
@Entity(name="trade")
public class TradeEntity {

    @Id
    long id;

    @Column(nullable = false)
    String sellEmail;

    @Column(nullable = false)
    String buyEmail;

    String toId;

    String subject;

    String status;

    String time;
}
