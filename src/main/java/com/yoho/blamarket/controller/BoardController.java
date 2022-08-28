package com.yoho.blamarket.controller;

import com.yoho.blamarket.entity.ItemEntity;
import com.yoho.blamarket.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/post")
public class BoardController {

    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("")
    @ResponseBody
    public List<ItemEntity> getAllPosts() {
        List<ItemEntity> allPosts = boardService.getAllPosts();
        return allPosts;
    }

}
