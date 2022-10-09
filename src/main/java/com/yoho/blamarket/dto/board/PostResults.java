package com.yoho.blamarket.dto.board;

public class PostResults {
    private int status;
    private String message;
    private RequestPostDto result;

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

    public RequestPostDto getResult() {
        return result;
    }

    public void setResult(RequestPostDto result) {
        this.result = result;
    }
}
