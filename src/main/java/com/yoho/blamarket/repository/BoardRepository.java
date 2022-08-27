package com.yoho.blamarket.repository;

import com.yoho.blamarket.entity.ItemEntity;
import com.yoho.blamarket.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<ItemEntity, String> {

    List<ItemEntity> findAll();
}
