package com.egovframework.fusion.mapper;

import com.egovframework.fusion.post.vo.ImageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImageMapper {
    void insImg(List<ImageVO> imageList);
}
