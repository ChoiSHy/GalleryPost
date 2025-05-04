package com.egovframework.fusion.sign.service;

import com.egovframework.fusion.sign.dto.SignResultDto;
import com.egovframework.fusion.sign.vo.SignVO;

public interface SignService {
    // 아이디 중복 체크
    SignResultDto confirmUsername(String user_username);

    // 회원가입
    SignResultDto signUp(SignVO signVo);
    SignResultDto signIn(SignVO signVo);
}
