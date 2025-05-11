package com.egovframework.fusion.post.dto;

import com.egovframework.fusion.post.vo.ImageVO;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class PostDto implements Serializable {
    private static final long serialVersionUID = 362498820763181265L;

    private Integer postId;
    private String postTitle;
    private String postContent;
    private Date postRegistDate;
    private Date postUpdateDate;
    private String postDelYn;
    private Integer postLikes;
    private Integer userId;

    private String userUsername;
    private List<ImageVO> imgs;
}
