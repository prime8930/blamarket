package com.yoho.blamarket.repository;

import com.yoho.blamarket.dto.board.RequestAllPostsDto;
import com.yoho.blamarket.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    /** JpaRepository<T, ID> 타입 명시할 때는 Entity 타입, PK 타입을 명시한다. */
    List<ImageEntity> findByItemId(long itemId);

}
