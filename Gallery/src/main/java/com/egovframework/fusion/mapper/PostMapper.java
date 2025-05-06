package com.egovframework.fusion.mapper;

import com.egovframework.fusion.post.dto.PostRequestDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {
    int insPostData(PostRequestDto postRequestDto);
}
