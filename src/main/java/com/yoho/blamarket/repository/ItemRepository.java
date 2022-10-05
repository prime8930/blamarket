package com.yoho.blamarket.repository;

import com.yoho.blamarket.entity.ImageEntity;
import com.yoho.blamarket.entity.ItemEntity;
import com.yoho.blamarket.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, String> {

    List<ItemEntity> findAll();

    ItemEntity findById(long itemId);

    @Transactional
    int deleteById(long itemId);
}
