package com.yoho.blamarket.service;

import com.yoho.blamarket.common.ApiResponse;
import com.yoho.blamarket.dto.chat.RequestCreateChatDto;
import com.yoho.blamarket.entity.TradeEntity;
import com.yoho.blamarket.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    ChatRepository chatRepository;

    public ApiResponse createChat(RequestCreateChatDto request){
        ApiResponse apiResponse = null;

        TradeEntity tradeEntity = TradeEntity.builder()

                        .sellEmail(request.seller)
                        .buyEmail(request.buyer)
                        .subject("")
                        .status(request.buyer)
                        .build();


        log.info("1 : {}", tradeEntity.toString());
        tradeEntity = chatRepository.save(tradeEntity);
        log.info("2 : {}", tradeEntity.toString());

        apiResponse = new ApiResponse(200, "채팅 생성이 완료되었습니다.");

        return apiResponse;
    }
}
