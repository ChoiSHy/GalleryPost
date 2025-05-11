package com.egovframework.fusion.post.controller;

import com.egovframework.fusion.post.dto.PostRequestDto;
import com.egovframework.fusion.post.service.PostService;
import com.egovframework.fusion.post.vo.ImageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;
    private static final String BASE_DIR = "C:/Users/HOME/Desktop/2025/갤러리 게시판/imgs";

    @RequestMapping(value = "/post/postList.do", method = RequestMethod.GET)
    public String getPostList(Model model, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "views/post/postList";
    }

    /*
     * 포스트 업로드 페이지
     *
     * */
    @RequestMapping(value = "/post/insPost/page.do")
    public String getInsPostPage(Model model) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "views/post/postRegister";
    }

    /*
     * 포스트 업로드
     *
     * */
    @RequestMapping(value = "/post/insPost.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> insPost(
            @RequestPart("postData") PostRequestDto postRequestDto,
            @RequestPart("images") List<MultipartFile> images,
            HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("userId") == null) {
                // TODO:로그인 안된 경우
                return ResponseEntity.badRequest().body("로그인 필요");
            }
            postRequestDto.setUserId(Integer.parseInt(String.valueOf(session.getAttribute("userId"))));
            postService.insPost(postRequestDto, images);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok("업로드 완료");
    }

    @RequestMapping(value = "/attach/download.do")
    @ResponseBody
    public ResponseEntity<?> downloadImg(ImageVO imageVO, HttpServletResponse response) {
        try {
            ImageVO resultVo = postService.downloadImage(imageVO);

            //Path filePath = Paths.get(String.format("%s/%s/%s", BASE_DIR, resultVo.getImgPath(),resultVo.getImgSavedName()));
            Path filePath = Paths.get(resultVo.getImgPath());
            Resource resource = new UrlResource(filePath.toUri());

            if(!resource.exists()){
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return ResponseEntity.notFound().build();
            }

            // 응답 헤더
            response.setContentType(Files.probeContentType(filePath));
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+resultVo.getImgOriginName()+"\"");
            response.setContentLengthLong(Files.size(filePath));

            // 데이터 전송
            Files.copy(filePath, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("파일 다운로드 중 오류 발생");
        }
        return ResponseEntity.ok("다운로드 완료");
    }
}
