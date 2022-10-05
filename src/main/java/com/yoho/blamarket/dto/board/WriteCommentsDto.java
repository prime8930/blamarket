package com.yoho.blamarket.dto.board;

import com.yoho.blamarket.entity.CommentsEntity;

public class WriteCommentsDto {

    private long itemId;
    private long topCmntId;
    private String contents;
    private String email;

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public long getTopCmntId() {
        return topCmntId;
    }

    public void setTopCmntId(long topCmntId) {
        this.topCmntId = topCmntId;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
