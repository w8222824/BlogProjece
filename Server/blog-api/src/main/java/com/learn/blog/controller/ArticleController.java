package com.learn.blog.controller;

import com.learn.blog.service.ArticleService;
import com.learn.blog.vo.ArticleVo;
import com.learn.blog.vo.Result;

import com.mszlu.blog.vo.params.PageParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//json数据进行交互
@Slf4j
@RestController
@RequestMapping("articles")
public class ArticleController {

 @Autowired
 private ArticleService articleService;

    /**
     * 首页文章列表
     *
     * */
    @PostMapping
    public Result listArticle(@RequestBody PageParams pageParams){

        //ArticleVo 页面接收的数据
//        List<ArticleVo> articles = articleService.listArticlesPage(pageParams);
        log.info("数据校验2：{}",pageParams);
        log.info("数据校验3:{}",articleService.listArticle(pageParams));
        return articleService.listArticle(pageParams);
    }


//    @Autowired
//    private ArticleService articleService;
//    /**
//     * 首页 文章列表
//     * @param pageParams
//     * @return
//     */
//    @PostMapping
//    //加上此注解 代表要对此接口记录日志
//    @LogAnnotation(module="文章",operator="获取文章列表")
//    @Cache(expire = 5 * 60 * 1000,name = "listArticle")
//    public Result listArticle(@RequestBody PageParams pageParams){
////        int i = 10/0;
//        return articleService.listArticle(pageParams);
//    }
//
//    /**
//     * 首页 最热文章
//     * @return
//     */
//    @PostMapping("hot")
//    @Cache(expire = 5 * 60 * 1000,name = "hot_article")
//    public Result hotArticle(){
//        int limit = 5;
//        return articleService.hotArticle(limit);
//    }
//
//    /**
//     * 首页 最新文章
//     * @return
//     */
//    @PostMapping("new")
//    @Cache(expire = 5 * 60 * 1000,name = "news_article")
//    public Result newArticles(){
//        int limit = 5;
//        return articleService.newArticles(limit);
//    }
//
//    /**
//     * 首页 最新文章
//     * @return
//     */
//    @PostMapping("listArchives")
//    public Result listArchives(){
//        return articleService.listArchives();
//    }
//
//
//    @PostMapping("view/{id}")
//    public Result findArticleById(@PathVariable("id") Long articleId){
//        return articleService.findArticleById(articleId);
//    }
//    //接口url：/articles/publish
//    //
//    //请求方式：POST
//    @PostMapping("publish")
//    public Result publish(@RequestBody ArticleParam articleParam){
//
//        return articleService.publish(articleParam);
//    }
}
