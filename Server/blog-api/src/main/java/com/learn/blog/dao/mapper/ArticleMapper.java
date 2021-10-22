package com.learn.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.blog.dao.dos.Archives;
import com.learn.blog.dao.pojo.Article;


import java.util.List;

public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 归档列表 因为ms_article 的year 和month是Bigint(13)创建的 所以需要处理一下Sql语句
     *
     * */
    List<Archives> listArchives();


//    List<Archives> listArchives();

//
//    IPage<Article> listArticle(Page<Article> page,
//                               Long categoryId,
//                               Long tagId,
//                               String year,
//                               String month);
}
