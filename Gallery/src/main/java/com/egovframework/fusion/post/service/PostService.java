package com.egovframework.fusion.post.service;

import com.egovframework.fusion.post.dto.PostRequestDto;
import com.egovframework.fusion.post.vo.ImageVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface PostService {
    void insPost(PostRequestDto requestDto, List<MultipartFile> images);
    ImageVO getImage(ImageVO imageVo);

    ImageVO downloadImage(ImageVO imageVO);
}
