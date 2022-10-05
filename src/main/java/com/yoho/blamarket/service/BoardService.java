package com.yoho.blamarket.service;

import com.yoho.blamarket.dto.board.*;
import com.yoho.blamarket.entity.CommentsEntity;
import com.yoho.blamarket.entity.ImageEntity;
import com.yoho.blamarket.entity.ItemEntity;
import com.yoho.blamarket.entity.WishEntity;
import com.yoho.blamarket.repository.CommentsRepository;
import com.yoho.blamarket.repository.ItemRepository;
import com.yoho.blamarket.repository.ImageRepository;
import com.yoho.blamarket.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@Service
public class BoardService {

    private ItemRepository itemRepository;
    private ImageRepository imageRepository;
    private WishRepository wishRepository;
    private CommentsRepository commentsRepository;

    @Autowired
    public BoardService(ItemRepository itemRepository, ImageRepository imageRepository, WishRepository wishRepository, CommentsRepository commentsRepository) {
        this.itemRepository = itemRepository;
        this.imageRepository = imageRepository;
        this.wishRepository = wishRepository;
        this.commentsRepository = commentsRepository;
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
    // 추가 필요 1. 게시글 조회 시 조회수 1 증가
    public PostResults getPostByItemId(long itemId) {
        ItemEntity itemInfo = itemRepository.findById(itemId);
        Map<String, String> imageInfoMap = getImageInfo(itemId);
        Map<String, Object> wishInfoMap = getWishInfo(itemId);

        RequestPostDto requestPostDto = new RequestPostDto(itemInfo, imageInfoMap, wishInfoMap);

        PostResults postResults = new PostResults();

        // 예외처리 수정 필요
        if(itemId < 1) {
            postResults.setStatus(400);
            postResults.setMessage("fail");
        } else {
            postResults.setStatus(200);
            postResults.setMessage("success");
        }

        postResults.setResult(requestPostDto);

        return postResults;
    }

    /** 좋아요 정보 가져오기 */
    private Map<String, Object> getWishInfo(long itemId) {
        WishEntity wishInfo = wishRepository.findByItem(itemId);
        Map<String, Object> wishInfoMap = new HashMap<>();

        if(wishInfo != null ) {
            wishInfoMap.put("item", wishInfo.getItem());
            wishInfoMap.put("email", wishInfo.getEmail());
        }

        return wishInfoMap;
    }

    /** 이미지 정보 가져오기 */
    private Map<String, String> getImageInfo(long itemId) {
        List<ImageEntity> imageInfo = imageRepository.findByItemId(itemId);
        Map<String, String> imageInfoMap = new HashMap<>();

        if( imageInfo != null ) {
            for (int i = 1; i<=imageInfo.size(); i++) {
                String path = imageInfo.get(i-1).getPath();
                imageInfoMap.put("image" + i, path);
            }
        }

        return imageInfoMap;
    }

    /** 게시글 등록 */
    public RequestResults writePost(WritePostDto writeInfo) {
        RequestResults requestResults = new RequestResults();

        try {
            ItemEntity itemEntity = ItemEntity.builder()
                    .email(writeInfo.getEmail())
                    .title(writeInfo.getTitle())
                    .contents(writeInfo.getContents())
                    .price(writeInfo.getPrice())
                    .Category(writeInfo.getCategory())
                    .build();

            ItemEntity itemSave = itemRepository.save(itemEntity);

            for (MultipartFile multipartFile : writeInfo.getImageList()) {

                UUID uuid = UUID.randomUUID();
                String folderPath = ResourceUtils.getURL("src/main/resources/img").getPath();

                String savedPath = folderPath + "\\" + uuid + multipartFile.getName();

                ImageEntity imageEntity = ImageEntity.builder()
                        .itemId(itemSave.getId())
                        .path(savedPath)
                        .build();

                File saveFile = new File(savedPath);
                multipartFile.transferTo(saveFile);

                imageRepository.save(imageEntity);
            }

            requestResults.setStatus(200);
            requestResults.setMessage("success");

        } catch(Exception e) {
            requestResults.setStatus(400);
            requestResults.setMessage("fail");
        }

        return requestResults;
    }

    /** 게시글 삭제 */
    public RequestResults deletePost(long itemId) {
        RequestResults requestResults = new RequestResults();

        int result = imageRepository.deleteByItemId(itemId);

        if(result > 0) {
            itemRepository.deleteById(itemId);
            requestResults.setStatus(200);
            requestResults.setMessage("success");
        } else {
            requestResults.setStatus(400);
            requestResults.setMessage("fail");
        }

        return requestResults;
    }

    /** 게시글 수정 */

    /** 댓글 조회 */
    public CommentsResults getAllComments(long itemId) {
        CommentsResults commentsResults = new CommentsResults();
        List<CommentsEntity> commentsInfoList = new ArrayList<>();
        try {
            commentsInfoList = commentsRepository.findAllByItemId(itemId);
            commentsResults.setStatus(200);
            commentsResults.setMessage("success");

        } catch (Exception e) {
            commentsResults.setStatus(400);
            commentsResults.setMessage("fail");
        }

        commentsResults.setResult(commentsInfoList);

        return commentsResults;
    }

    /** 댓글 작성 */
    public RequestResults writeComments(WriteCommentsDto writeCommentsDto) {
        RequestResults requestResults = new RequestResults();
        CommentsEntity commentsEntity = CommentsEntity.builder()
                .itemId(writeCommentsDto.getItemId())
                .contents(writeCommentsDto.getContents())
                .email(writeCommentsDto.getEmail())
                .parentsComment(writeCommentsDto.getTopCmntId())
                .build();
        try {
            commentsRepository.save(commentsEntity);
            requestResults.setStatus(200);
            requestResults.setMessage("success");
        } catch (Exception e) {
            requestResults.setStatus(400);
            requestResults.setMessage("fail");
        }

        return requestResults;
    }

    /** 댓글 삭제 */
    public RequestResults deleteComments(long commentId) {
        RequestResults requestResults = new RequestResults();

        try {
            commentsRepository.deleteById(commentId);
            requestResults.setStatus(200);
            requestResults.setMessage("success");
        } catch(Exception e) {
            requestResults.setStatus(400);
            requestResults.setMessage("fail");
        }

        return requestResults;
    }



}
