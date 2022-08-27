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
@Entity(name="image")
public class ImageEntity {

    @Id
    long id;

    @Column(nullable = false)
    long itemId;

    @Column(nullable = false)
    String path;

    String deleteFlag;
}
