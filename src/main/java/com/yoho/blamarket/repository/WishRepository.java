package com.yoho.blamarket.repository;

import com.yoho.blamarket.entity.WishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<WishEntity, Long> {

    WishEntity findByItem(long itemId);
}
