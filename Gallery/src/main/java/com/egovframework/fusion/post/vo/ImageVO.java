package com.egovframework.fusion.post.vo;

import java.util.Date;

public class ImageVO {
    private Integer imgId;
    private String imgOriginName;
    private String imgSavedName;
    private String imgPath;
    private String imgDelYn;
    private Date imgSaveDate;
    private Integer postId;

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public String getImgOriginName() {
        return imgOriginName;
    }

    public void setImgOriginName(String imgOriginName) {
        this.imgOriginName = imgOriginName;
    }

    public String getImgSavedName() {
        return imgSavedName;
    }

    public void setImgSavedName(String imgSavedName) {
        this.imgSavedName = imgSavedName;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getImgDelYn() {
        return imgDelYn;
    }

    public void setImgDelYn(String imgDelYn) {
        this.imgDelYn = imgDelYn;
    }

    public Date getImgSaveDate() {
        return imgSaveDate;
    }

    public void setImgSaveDate(Date imgSaveDate) {
        this.imgSaveDate = imgSaveDate;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }
}
