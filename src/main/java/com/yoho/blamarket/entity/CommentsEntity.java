package com.yoho.blamarket.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="comments")
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE comments SET delete_flag = true WHERE id = ?")
@Where(clause = "delete_flag = false")
public class CommentsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    long itemId;

    @Column(nullable = false, columnDefinition = "TEXT")
    String contents;

    long parentsComment;

    @CreatedDate
    String regDate;

    boolean deleteFlag = Boolean.FALSE; // 삭제 여부 기본값 false

    @PrePersist
    public void onPrePersist(){
        this.regDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
