package com.yoho.blamarket.dto.board;

import java.util.List;

public class BoardResults {
    private int status;
    private String message;
    private List<RequestAllPostsDto> result;

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

    public List<RequestAllPostsDto> getResult() {
        return result;
    }

    public void setResult(List<RequestAllPostsDto> result) {
        this.result = result;
    }

}
