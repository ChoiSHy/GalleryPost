package com.egovframework.fusion.post.dto;

import jakarta.validation.constraints.Size;

public class PostRequestDto {
    @Size(max = 30, min = 1)
    private String title;
    @Size(max = 300, min = 1)
    private String content;

    private Integer userId;
    private Integer postId;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
