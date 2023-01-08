package com.yoho.blamarket.controller;

import com.yoho.blamarket.dto.board.*;
import com.yoho.blamarket.jwt.JwtProperties;
import com.yoho.blamarket.jwt.JwtUtils;
import com.yoho.blamarket.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;


@RestController
@RequestMapping("/post")
public class BoardController {

    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/view")
    public BoardResults getAllPosts(@RequestParam(defaultValue = "1", value="page") int page,
                                    @RequestParam(value="companyId") long companyId) {
        return boardService.getAllPosts(page, companyId);
    }

    @GetMapping("/viewDetail")
    public PostResults getPostByItemId(HttpServletRequest request, long itemId) {
        return boardService.getPostByItemId(request, itemId);
    }

    @GetMapping("/edit")
    public PostResults callEditPost(HttpServletRequest request, long itemId) {
        return boardService.callEditPost(request, itemId);
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

    @RequestMapping("/like")
    public RequestResults likePost(HttpServletRequest request, long itemId) {
        return boardService.likePost(request, itemId);
    }

}
