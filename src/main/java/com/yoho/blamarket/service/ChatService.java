package com.yoho.blamarket.service;

import com.yoho.blamarket.common.ApiResponse;
import com.yoho.blamarket.common.Utils;
import com.yoho.blamarket.dto.chat.RequestCreateChatDto;
import com.yoho.blamarket.dto.chat.RequestGetMessageDto;
import com.yoho.blamarket.dto.chat.RequestMessageDto;
import com.yoho.blamarket.entity.MessageEntity;
import com.yoho.blamarket.entity.TradeEntity;
import com.yoho.blamarket.repository.ChatRepository;
import com.yoho.blamarket.repository.MessageRepository;
import jdk.jshell.spi.ExecutionControlProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {



    ChatRepository chatRepository;
    MessageRepository messageRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository, MessageRepository messageRepository){
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
    }

    public ApiResponse createChat(RequestCreateChatDto request){
        ApiResponse apiResponse = new ApiResponse();

        try{

            TradeEntity tradeEntity = TradeEntity.builder()
                            .sellEmail(request.seller)
                            .buyEmail(request.buyer)
                            .subject("")
                            .status(request.buyer)
                            .build();

            tradeEntity = chatRepository.save(tradeEntity);
            apiResponse = new ApiResponse(200, "채팅 생성이 완료되었습니다.");
        } catch (Exception e){
            log.error("채팅 생성 중 오류 : {}", e.getMessage());
            log.error("{}", e.getStackTrace());
            apiResponse.setStatus(400);
            apiResponse.setMessage("채팅을 생성하는중 에러 발생");
        }

        return apiResponse;
    }

    public ApiResponse getChat(String email){
        ApiResponse apiResponse = new ApiResponse();

        try{
            List<TradeEntity> buyTrade = chatRepository.findAllByBuyEmail(email);
            List<TradeEntity> sellTrade = chatRepository.findAllBySellEmail(email);
            List<TradeEntity> tradeList = Stream.concat(buyTrade.stream(), sellTrade.stream())
                    .collect(Collectors.toList());
            apiResponse.putData("data", tradeList);
        } catch (Exception e) {
            log.error("채팅을 가져오는중 에러 발생 : {}", e.getMessage());
            log.error("{}", e.getStackTrace());
            apiResponse.setStatus(400);
            apiResponse.setMessage("채팅을 가져오는중 에러 발생");
        }
        return apiResponse;
    }

    public ApiResponse getMessage(RequestGetMessageDto requestGetMessageDto){
        ApiResponse apiResponse = new ApiResponse();

        try{
            List<MessageEntity> responseMessage =
                    messageRepository.findTop20ByTradeIdAndMessageIdLessThen(requestGetMessageDto);
            apiResponse.putData("date", responseMessage);
        } catch (Exception e){
            log.error("메세지 가져오는 중 에러 : {}", e.getMessage());
            log.error("{}", e.getStackTrace());
            apiResponse.setStatus(400);
            apiResponse.setMessage("메세지 가져오는 중 에러");
        }
        return apiResponse;
    }

    public ApiResponse sendMessage(RequestMessageDto requestMessageDto){
        ApiResponse apiResponse = new ApiResponse();

        try{
            MessageEntity message = MessageEntity.builder()
                    .tradeId(requestMessageDto.getTreadId())
                    .content(requestMessageDto.getContent())
                    .sender(requestMessageDto.getSender())
                    .read_flag(false)
                    .sendTime(Utils.retDate(Utils.SEND_TIME))
                    .build();

            chatRepository.


        } catch (Exception e) {
            log.error("메세지 가져오는 중 에러 : {}", e.getMessage());
            log.error("{}", e.getStackTrace());
            apiResponse.setStatus(400);
            apiResponse.setMessage("메세지 가져오는 중 에러");
        }

        return apiResponse;
    }
}
