package com.egovframework.fusion.post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class PostController {

    @RequestMapping(value = "/post/postList.do", method = RequestMethod.GET)
    public String getPostList(Model model, HttpSession session){
        return "views/post/postList";
    }
}
