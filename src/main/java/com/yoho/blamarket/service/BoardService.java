package com.yoho.blamarket.service;

import com.yoho.blamarket.entity.ItemEntity;
import com.yoho.blamarket.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<ItemEntity> getAllPosts() {
        return boardRepository.findAll();
    }
}
