package com.egovframework.fusion.mapper;

import com.egovframework.fusion.sign.vo.SignVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignMapper {
    int confirmUsername(String user_username);

    void signUp(SignVO signVo);

    SignVO signIn(SignVO signVO);

}
