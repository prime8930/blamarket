package com.yoho.blamarket.dto.board;

import org.springframework.web.multipart.MultipartFile;

public class WritePostDto {

    private String email;
    private String title;
    private String contents;
    private MultipartFile[] imageList;
    private String category;
    private long price;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public MultipartFile[] getImageList() {
        return imageList;
    }

    public void setImageList(MultipartFile[] imageList) {
        this.imageList = imageList;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
