package com.learn.blog.service;
import com.learn.blog.vo.Result;
import com.mszlu.blog.vo.TagVo;
import java.util.List;

public interface TagService {


    /**
     * 根据文章id查询标签列表
     * */
    List<TagVo> findTagsByArticleId(Long articleId);


    /**
     * 最热标签
     * @param:limit 根据参数拉取多少个当前最热
     * */
     Result hots(int limit);
}
