package com.learn.blog.service.impl;


import com.learn.blog.dao.mapper.TagMapper;
import com.learn.blog.dao.pojo.Tag;
import com.learn.blog.service.TagService;
import com.learn.blog.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  com.mszlu.blog.vo.TagVo;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<TagVo> findTagsByArticleId(Long articleId) {
        //mybatisplus 无法进行多表查询
        List<Tag> tags = tagMapper.findTagsByArticleId(articleId);
        return copyList(tags);
    }


    public TagVo copy(Tag tag){
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag,tagVo);
        tagVo.setId(String.valueOf(tag.getId()));
        return tagVo;
    }
    public List<TagVo> copyList(List<Tag> tagList){
        List<TagVo> tagVoList = new ArrayList<>();
        for (Tag tag : tagList) {
            tagVoList.add(copy(tag));
        }
        return tagVoList;
    }

    /**
     *  select count(*) as count，tag_id FROM `ms_article_tag` group by tag_id order by count desc limit 1
     * =>select tag_id FROM `ms_article_tag` group by tag_id order by count(*)  desc limit 1
     * */
    @Override
    public Result hots(int limit) {

      /**
       *     * 标签所有用的文章数最多,就是最热标签
       *      *2查询 根据tag_id 分组 技术,从大到小,排列,取前limit个
       * */
       List<Long> tagIds=  tagMapper.findHotsTagIds(limit);
        //不存在直接返回
       if (CollectionUtils.isEmpty(tagIds)){
           return Result.success(Collections.emptyList());
       }
       //需求是tagId 和tagName加 Tag对象
        //select * from tag where id in (1,2,3,4)
        //根据tagid查询tag对象
       List<Tag> TagList= tagMapper.findTagsByTagIds(tagIds);

        return Result.success(TagList);
    }

}
