package com.api.socialMaker.dto;

import lombok.Getter;
import lombok.Setter;

public class PostRequest {
    private String content;

    // Constructors, getters ve setters
    public PostRequest() {}

    public PostRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
