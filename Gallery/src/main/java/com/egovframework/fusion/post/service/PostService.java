package com.egovframework.fusion.post.service;

import com.egovframework.fusion.post.dto.PostRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    void insPost(PostRequestDto requestDto, List<MultipartFile> images);
}
