package com.yoho.blamarket.service;

import com.yoho.blamarket.dto.board.*;
import com.yoho.blamarket.entity.ImageEntity;
import com.yoho.blamarket.entity.ItemEntity;
import com.yoho.blamarket.entity.WishEntity;
import com.yoho.blamarket.repository.ItemRepository;
import com.yoho.blamarket.repository.ImageRepository;
import com.yoho.blamarket.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BoardService {

    private ItemRepository itemRepository;
    private ImageRepository imageRepository;
    private WishRepository wishRepository;

    @Autowired
    public BoardService(ItemRepository boardRepository, ImageRepository imageRepository, WishRepository wishRepository) {
        this.itemRepository = boardRepository;
        this.imageRepository = imageRepository;
        this.wishRepository = wishRepository;
    }

    /** 전체 게시글 조회 */
    public BoardResults getAllPosts() {
        List<RequestAllPostsDto> requestAllPostsDtoList = new ArrayList<>();
        List<ItemEntity> itemInfoList = itemRepository.findAll();       // 게시글

        for (ItemEntity itemEntity : itemInfoList) {
            long itemId = itemEntity.getId();
            List<ImageEntity> imageInfo = imageRepository.findByItemId(itemId);

            RequestAllPostsDto boardInfo = new RequestAllPostsDto(itemEntity, imageInfo.get(0));
            requestAllPostsDtoList.add(boardInfo);
        }

        BoardResults boardResults = new BoardResults();

        if(requestAllPostsDtoList.size() == 0) {
            boardResults.setStatus(400);
            boardResults.setMessage("fail");
        } else {
            boardResults.setStatus(200);
            boardResults.setMessage("success");
        }

        boardResults.setResult(requestAllPostsDtoList);
        
        return boardResults;
    }

    /** 게시글 조회 */
    public PostResults getPostByItemId(long itemId) {
        ItemEntity itemInfo = itemRepository.findById(itemId);
        Map<String, String> imageInfoMap = getImageInfo(itemId);
        Map<String, Object> wishInfoMap = getWishInfo(itemId);

        RequestPostDto requestPostDto = new RequestPostDto(itemInfo, imageInfoMap, wishInfoMap);

        PostResults postResults = new PostResults();

        if(itemId < 1) {    // 조건절 생각 중..
            postResults.setStatus(400);
            postResults.setMessage("fail");
        } else {
            postResults.setStatus(200);
            postResults.setMessage("success");
        }

        postResults.setResult(requestPostDto);

        return postResults;
    }

    private Map<String, Object> getWishInfo(long itemId) {
        WishEntity wishInfo = wishRepository.findByItem(itemId);
        Map<String, Object> wishInfoMap = new HashMap<>();

        wishInfoMap.put("item", wishInfo.getItem());
        wishInfoMap.put("email", wishInfo.getEmail());

        return wishInfoMap;
    }

    private Map<String, String> getImageInfo(long itemId) {
        List<ImageEntity> imageInfo = imageRepository.findByItemId(itemId);
        Map<String, String> imageInfoMap = new HashMap<>();
        for (int i = 1; i<=imageInfo.size(); i++) {
            String path = imageInfo.get(i-1).getPath();
            imageInfoMap.put("image" + i, path);
        }

        return imageInfoMap;
    }


}
