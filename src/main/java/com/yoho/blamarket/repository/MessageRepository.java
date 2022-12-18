package com.yoho.blamarket.repository;

import com.yoho.blamarket.dto.chat.RequestGetMessageDto;
import com.yoho.blamarket.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, String> {
    List<MessageEntity> findTop20ByTradeIdAndMessageIdLessThen(RequestGetMessageDto requestGetMessageDto);
}
