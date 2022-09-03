package com.yoho.blamarket.dto.board;

import com.yoho.blamarket.entity.ImageEntity;
import com.yoho.blamarket.entity.ItemEntity;
import com.yoho.blamarket.entity.WishEntity;

import java.util.Map;

public class RequestAllPostsDto {
    private long id;
    private String title;
    private String content;
    private String thumbnail;
    private long price;
    private String date;
    private String used_date;
    private String view_count;

    public RequestAllPostsDto(ItemEntity item, ImageEntity image) {
        this.id = item.getId();
        this.title = item.getTitle();
        this.content = item.getContents();
        this.thumbnail = image.getPath();
        this.price = item.getPrice();
        this.date = item.getRegistDate();
        this.used_date = item.getUsedDate();
        this.view_count = item.getViewCount();
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public long getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public String getUsed_date() {
        return used_date;
    }

    public String getView_count() {
        return view_count;
    }
}
