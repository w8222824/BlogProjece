package com.learn.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.learn.blog.dao.mapper.ArticleMapper;

import com.learn.blog.dao.pojo.Article;

import com.learn.blog.dao.pojo.SysUser;
import com.learn.blog.service.*;

import com.learn.blog.vo.ArticleVo;
import com.learn.blog.vo.Result;

import com.mszlu.blog.vo.params.PageParams;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TagService tagService;
    @Autowired
    private SysUserService sysUserService;


    /**
     * 分页查询 article数据库表
     *
     * */
    @Override
    public Result listArticle(PageParams pageParams) {
        log.info("查询实现");
        //是否置顶进行排序
        //order by create_data desc
        Page<Article> page = new Page<>(pageParams.getPage(),pageParams.getPageSize());
        LambdaQueryWrapper<Article> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getWeight,Article::getCreateDate);
        Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
        List<Article> records=articlePage.getRecords();

        List<ArticleVo> articleVoList =copyList(records,true,true);

        return Result.success(articleVoList);
    }

    private List<ArticleVo> copyList(List<Article> records,boolean isTag,boolean isAuthor) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article record : records) {

            articleVoList.add(copy(record,isTag,isAuthor));
        }
        return articleVoList;
    }

    public ArticleVo copy(Article article,boolean isTag,boolean isAuthor){
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article, articleVo);   //相同属性copy

        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));

        //并不是所有的文章都需要标签和作者
        if (isTag){
            Long articleId=article.getId();//获取当前文章的id
            articleVo.setTags(tagService.findTagsByArticleId(articleId)); //给对应的文章id的文章 定义标签
        }
        if (isAuthor)
        {
            Long authorId=article.getAuthorId(); //获取文章id
            articleVo.setAuthor(sysUserService.findUserById(authorId).getNickname());  //给文章定义作者
        }
        return articleVo;
    }


}