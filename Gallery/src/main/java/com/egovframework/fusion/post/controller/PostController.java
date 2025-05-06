package com.egovframework.fusion.post.controller;

import com.egovframework.fusion.post.dto.PostRequestDto;
import com.egovframework.fusion.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/post/postList.do", method = RequestMethod.GET)
    public String getPostList(Model model, HttpServletRequest request) {
        try{
            HttpSession session = request.getSession(false);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "views/post/postList";
    }
    /*
    * 포스트 업로드 페이지
    *
    * */
    @RequestMapping(value="/post/insPost/page.do")
    public String getInsPostPage(Model model){
        try{

        }catch (Exception e){
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
            try{
                HttpSession session = request.getSession(false);
                if(session == null || session.getAttribute("userId") == null){
                    // TODO:로그인 안된 경우
                    return ResponseEntity.badRequest().body("로그인 필요");
                }
                postRequestDto.setUserId(Integer.parseInt(String.valueOf(session.getAttribute("userId"))));
                postService.insPost(postRequestDto, images);

            } catch (Exception e){
                e.printStackTrace();
            }

            return ResponseEntity.ok("업로드 완료");
    }
}
