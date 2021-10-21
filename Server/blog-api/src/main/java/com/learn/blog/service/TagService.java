package com.learn.blog.service;
import com.mszlu.blog.vo.TagVo;
import java.util.List;

public interface TagService {


    /**
     * 根据文章id查询标签列表
     * */
    List<TagVo> findTagsByArticleId(Long articleId);
}
