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
@Entity(name="comments")
public class CommentsEntity {

    @Id
    long id;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    long itemId;

    @Column(nullable = false, columnDefinition = "TEXT")
    String contents;

    long parentsComment;

    String regDate;

    String deleteFlag;

}
