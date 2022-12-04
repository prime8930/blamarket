package com.yoho.blamarket.controller;

import com.yoho.blamarket.common.ApiResponse;
import com.yoho.blamarket.dto.chat.RequestCreateChatDto;
import com.yoho.blamarket.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/createChat")
    public ResponseEntity<ApiResponse> createChat(@RequestBody RequestCreateChatDto requestCreateChatDto){

        log.info("abcd {}", requestCreateChatDto.toString());
//        return null;
        return new ResponseEntity(chatService.createChat(requestCreateChatDto), HttpStatus.OK);
    }
}

