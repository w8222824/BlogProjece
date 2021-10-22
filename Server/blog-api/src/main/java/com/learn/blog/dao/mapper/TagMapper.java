package com.learn.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.learn.blog.dao.pojo.Tag;
import com.learn.blog.dao.pojo.Tag;

import java.util.List;

public interface TagMapper extends BaseMapper<Tag> {
    /**
     * 根据文章id查询标签列表
     * @param articleId
     * @return
     */
    List<Tag> findTagsByArticleId(Long articleId);

    /**
     * 查询最热的标签 前n条
     * @param limit
     * @return
     */
    List<Long> findHotsTagIds(int limit);


    /**
     * 根据目标标签查询查询
     *
     * */
    List<Tag> findTagsByTagIds(List<Long> tagIds);
}
