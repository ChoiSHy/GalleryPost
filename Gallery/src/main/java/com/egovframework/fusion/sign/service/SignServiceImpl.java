package com.egovframework.fusion.sign.service;

import com.egovframework.fusion.mapper.SignMapper;
import com.egovframework.fusion.sign.dto.SignResultDto;
import com.egovframework.fusion.sign.vo.SignVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignServiceImpl implements SignService {
    @Autowired
    private SignMapper signMapper;

    /*
     * 아이디 중복 확인
     *
     * */
    @Override
    public SignResultDto confirmUsername(String user_username) {
        SignResultDto signResultDto = null;

        if (user_username.trim().isEmpty()) { // 아이디 공백 확인
            signResultDto = fail("아이디 비었음");
        } else {
            if (signMapper.confirmUsername(user_username) > 0) {  //중복 아이디 확인
                signResultDto = fail("이미 존재하는 아이디");
            } else {
                signResultDto = success("사용가능한 아이디");
            }
        }
        return signResultDto;
    }

    /*
     * 회원가입
     *
     * */
    @Override
    public SignResultDto signUp(SignVO signVo) {
        SignResultDto signResultDto = null;
        String username = signVo.getUserUsername(); // 아이디

        if (username.trim().isEmpty()) { // 아이디 공백 확인
            signResultDto = fail("아이디 비었음");
        } else {
            if (signMapper.confirmUsername(username) == 0) {    //중복 아이디 확인
                signMapper.signUp(signVo);
                signResultDto = success("회원가입 완료되었습니다.");
            } else {
                signResultDto = fail("중복된 아이디가 입력되었습니다.");
            }
        }
        return signResultDto;
    }

    /*
     * 로그인
     *
     * */
    @Override
    public SignResultDto signIn(SignVO signVo) {
        SignResultDto signResultDto = null;

        String username = signVo.getUserUsername();
        if (username.trim().isEmpty()) { // 아이디 공백 확인
            signResultDto = fail("아이디 비었음");
        } else {
            SignVO loadedSignVo = signMapper.signIn(signVo);
            if (loadedSignVo != null) { // username match
                //pw match
                if (loadedSignVo.getUserPassword().equals(signVo.getUserPassword())) {
                    signResultDto = success("로그인 성공했습니다.");
                    signResultDto.setId(loadedSignVo.getUserId());
                    signResultDto.setUsername(loadedSignVo.getUserUsername());
                } else {
                    signResultDto = fail("아이디 혹은 비밀번호가 맞지 않습니다.");
                }
            } else {
                signResultDto = fail("아이디 혹은 비밀번호가 맞지 않습니다.");
            }
        }
        return signResultDto;
    }

    /*
     * 전달 객체 (성공)
     *
     * */
    private SignResultDto success(String message) {
        SignResultDto signResultDto = new SignResultDto();
        signResultDto.setMsg(message);
        signResultDto.setCheck(true);

        return signResultDto;
    }

    /*
     * 전달 객체 (실패)
     *
     * */
    private SignResultDto fail(String message) {
        SignResultDto signResultDto = new SignResultDto();
        signResultDto.setMsg(message);
        signResultDto.setCheck(false);

        return signResultDto;
    }
}
