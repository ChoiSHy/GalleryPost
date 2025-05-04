package com.egovframework.fusion.sign.controller;

import com.egovframework.fusion.sign.dto.SignResultDto;
import com.egovframework.fusion.sign.dto.SignUpIdCheckDto;
import com.egovframework.fusion.sign.service.SignService;
import com.egovframework.fusion.sign.vo.SignVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SignController {
    @Autowired
    private SignService signService;

    /*
     * 로그인 페이지
     *  */
    @RequestMapping("/sign-in/page.do")
    public String signInPage() {
        return "views/sign/sign-in";
    }

    /*
     * 회원가입 페이지
     *
     * */
    @RequestMapping("/sign-up/page.do")
    public String signUpPage() {
        return "views/sign/sign-up";
    }

    /*
     * id 중복 확인
     *
     * */
    @RequestMapping(value = "/sign/idCheck.do", method = RequestMethod.POST)
    @ResponseBody
    public SignResultDto confirmId(@Valid SignUpIdCheckDto reqDto) {
        SignResultDto signResultDto;
        try {
            signResultDto = signService.confirmUsername(reqDto.getUserUsername());
        } catch (Exception e) {
            e.printStackTrace();
            signResultDto = new SignResultDto();
            signResultDto.setMsg("알 수 없는 오류");
            signResultDto.setCheck(false);
            return signResultDto;
        }
        return signResultDto;
    }

    /*
     * 회원가입
     *
     * */
    @RequestMapping(value = "/sign/sign-up.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<SignResultDto> signUp(@Valid SignVO signVo) {
        SignResultDto signResultDto = null;
        try {
            signResultDto = signService.signUp(signVo);
        } catch (Exception e) {
            e.printStackTrace();
            signResultDto = new SignResultDto();
            signResultDto.setMsg("알 수 없는 오류");
            signResultDto.setCheck(false);
            return ResponseEntity.badRequest().body(signResultDto);
        }
        return ResponseEntity.ok(signResultDto);
    }

    /*
     * 로그인
     *
     * */
    @RequestMapping(value = "/sign/sign-in.do", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<SignResultDto> signIn(@Valid SignVO signVo, HttpServletRequest request) {
        SignResultDto signResultDto = null;
        try {
            signResultDto = signService.signIn(signVo);

            /* 세션에 로그인 정보 남기기 */
            if (signResultDto.getCheck()) {
                HttpSession session = request.getSession();
                session.setAttribute("userUsername", signResultDto.getUsername());
                session.setAttribute("userId", signResultDto.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            signResultDto = new SignResultDto();
            signResultDto.setCheck(false);
            signResultDto.setMsg("알 수 없는 오류");
            return ResponseEntity.badRequest().body(signResultDto);
        }
        return ResponseEntity.ok(signResultDto);
    }

    /*
     * 로그아웃
     *
     * */
    @RequestMapping(value = "/sign/sign-out.do", method = RequestMethod.GET)
    public String signOut(HttpSession session) {
        session.invalidate();
        return "redirect:/post/postList.do";
    }
}
