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
@Entity(name="item")
public class ItemEntity {

    @Id
    long id;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    String contents;

    long price;

    String usedDate;

    String registDate;

    String status;

    String viewCount;

    String Category;

    long wish;

    String deleteFlag;

}
