package com.yoho.blamarket.repository;

import com.yoho.blamarket.entity.TradeEntity;
import com.yoho.blamarket.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.List;

public interface ChatRepository extends JpaRepository<TradeEntity, Long> {
    List<TradeEntity> findAll();
    TradeEntity findById(long id);

}
