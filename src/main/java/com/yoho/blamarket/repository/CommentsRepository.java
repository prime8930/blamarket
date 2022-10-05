package com.yoho.blamarket.repository;

import com.yoho.blamarket.entity.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentsRepository extends JpaRepository<CommentsEntity, Long> {

    List<CommentsEntity> findAllByItemId(long itemId);

    @Transactional
    void deleteById(long commentId);

}
