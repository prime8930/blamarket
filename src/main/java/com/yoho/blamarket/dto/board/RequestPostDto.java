package com.yoho.blamarket.dto.board;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.yoho.blamarket.entity.ItemEntity;

import java.util.Map;


public class RequestPostDto {

    private long id;
    private String email;
    private String title;
    private String content;
    private long price;
    private String used_date;
    private String date;
    private String status;
    private String view_count;
    private String category;
    private Map<String, String> images;
    private Map<String, Object> wish;

    public RequestPostDto(ItemEntity itemEntity, Map<String, String> imageEntity, Map<String, Object> wishEntity) {
        this.id = itemEntity.getId();
        this.email = itemEntity.getEmail();
        this.title = itemEntity.getTitle();
        this.content = itemEntity.getContents();
        this.price = itemEntity.getPrice();
        this.used_date = itemEntity.getUsedDate();
        this.date = itemEntity.getRegistDate();
        this.status = itemEntity.getStatus();
        this.view_count = itemEntity.getViewCount();
        this.category = itemEntity.getCategory();
        this.images = imageEntity;
        this.wish = wishEntity;
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

    public String getUsed_date() {
        return used_date;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getView_count() {
        return view_count;
    }

    public String getCategory() {
        return category;
    }

    public Map<String, String> getImages() {
        return images;
    }

    public Map<String, Object> getWish() {
        return wish;
    }
}
