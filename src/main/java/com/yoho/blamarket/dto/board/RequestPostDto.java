package com.yoho.blamarket.dto.board;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.yoho.blamarket.entity.ItemEntity;

import java.util.List;
import java.util.Map;


public class RequestPostDto {

    private long id;
    private String email;
    private String title;
    private String content;
    private long price;
    private String date;
    private int status;
    private long view_count;
    private String category;
    private List<String> images;
    private int wish;

    public RequestPostDto(ItemEntity itemEntity, List<String> imageEntity, int wish) {
        this.id = itemEntity.getId();
        this.email = itemEntity.getEmail();
        this.title = itemEntity.getTitle();
        this.content = itemEntity.getContents();
        this.price = itemEntity.getPrice();
        this.date = itemEntity.getRegistDate();
        this.status = itemEntity.getStatus();
        this.view_count = itemEntity.getViewCount();
        this.category = itemEntity.getCategory();
        this.images = imageEntity;
        this.wish = wish;
    }

    public RequestPostDto(ItemEntity itemEntity, List<String> imageEntity) {
        this.id = itemEntity.getId();
        this.email = itemEntity.getEmail();
        this.title = itemEntity.getTitle();
        this.content = itemEntity.getContents();
        this.price = itemEntity.getPrice();
        this.date = itemEntity.getRegistDate();
        this.category = itemEntity.getCategory();
        this.images = imageEntity;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public long getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public int getStatus() {
        return status;
    }

    public long getView_count() {
        return view_count;
    }

    public String getCategory() {
        return category;
    }

    public List<String> getImages() {
        return images;
    }

    public int getWish() {
        return wish;
    }
}
