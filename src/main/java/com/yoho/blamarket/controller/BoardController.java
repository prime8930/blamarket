package com.yoho.blamarket.controller;

import com.yoho.blamarket.dto.board.*;
import com.yoho.blamarket.entity.ImageEntity;
import com.yoho.blamarket.entity.ItemEntity;
import com.yoho.blamarket.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/post")
public class BoardController {

    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/view")
    public BoardResults getAllPosts() {
        return boardService.getAllPosts();
    }

    @GetMapping("/viewDetail")
    public PostResults getPostByItemId(long itemId) {
        return boardService.getPostByItemId(itemId);
    }


    @RequestMapping("/write")
    public RequestResults writePost(WritePostDto writeInfo) {
        return boardService.writePost(writeInfo);
    }

    @RequestMapping("/delete")
    public RequestResults deletePost(long itemId) {
        return boardService.deletePost(itemId);
    }

    @GetMapping("/comments")
    public CommentsResults getAllComments(long itemId) {
        return boardService.getAllComments(itemId);
    }

    @RequestMapping("/comments/write")
    public RequestResults writeComments(WriteCommentsDto writeCommentsDto) {
        return boardService.writeComments(writeCommentsDto);
    }

    @RequestMapping("/comments/delete")
    public RequestResults deleteComments(long commentId) {
        return boardService.deleteComments(commentId);
    }
}
