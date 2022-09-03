package com.yoho.blamarket.controller;

import com.yoho.blamarket.dto.board.BoardResults;
import com.yoho.blamarket.dto.board.PostResults;
import com.yoho.blamarket.dto.board.RequestResults;
import com.yoho.blamarket.dto.board.WritePostDto;
import com.yoho.blamarket.entity.ImageEntity;
import com.yoho.blamarket.entity.ItemEntity;
import com.yoho.blamarket.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/view")
    @ResponseBody
    public BoardResults getAllPosts() {
        return boardService.getAllPosts();
    }

    @GetMapping("/viewDetail")
    @ResponseBody
    public PostResults getPostByItemId(long itemId) {
        return boardService.getPostByItemId(itemId);
    }

}
