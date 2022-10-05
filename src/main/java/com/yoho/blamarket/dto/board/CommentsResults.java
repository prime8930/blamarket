package com.yoho.blamarket.dto.board;

import com.yoho.blamarket.entity.CommentsEntity;

import java.util.List;

public class CommentsResults {

    private int status;
    private String message;
    private List<CommentsEntity> result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CommentsEntity> getResult() {
        return result;
    }

    public void setResult(List<CommentsEntity> result) {
        this.result = result;
    }
}
