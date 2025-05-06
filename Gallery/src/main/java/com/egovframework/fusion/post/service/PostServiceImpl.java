package com.egovframework.fusion.post.service;

import com.egovframework.fusion.mapper.ImageMapper;
import com.egovframework.fusion.mapper.PostMapper;
import com.egovframework.fusion.post.dto.PostRequestDto;
import com.egovframework.fusion.post.vo.ImageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private ImageMapper imageMapper;

    private static final String BASE_DIR = "C:/Users/HOME/Desktop/2025/갤러리 게시판/imgs";

    @Override
    public void insPost(PostRequestDto requestDto, List<MultipartFile> images) {
        // 1. 포스트 정보 insert
        postMapper.insPostData(requestDto);
        Integer postId = requestDto.getPostId(); // 저장된 포스트 아이디
        
        if (images.size() > 0) {
            List<ImageVO> imageList= new ArrayList<>();
            // 2. 이미지 저장
            try {
                // 2-1. 포스트 아이디 폴더 생성
                Path uploadDir = Paths.get(BASE_DIR, String.valueOf(postId));
                Files.createDirectories(uploadDir);

                for(MultipartFile file : images){
                    if(file.isEmpty()) continue;
                    // 2-2. UUID생성 및 저장
                    String originName = file.getOriginalFilename();
                    String fileUUID = UUID.randomUUID().toString();
                    Path filePath = uploadDir.resolve(fileUUID);
                    System.out.println("이미지 저장 경로: " + filePath.toString());

                    file.transferTo(filePath);
                    // 2-3. 이미지 정보 기록
                    ImageVO imageVO = new ImageVO();
                    imageVO.setImgOriginName(originName);
                    imageVO.setImgSavedName(fileUUID);
                    imageVO.setImgPath(filePath.toString());
                    imageVO.setPostId(postId);

                    imageList.add(imageVO);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            // 3. 기록 insert
            imageMapper.insImg(imageList);
        }
    }
}
