package com.learn.blog.controller;


import com.learn.blog.service.TagService;
import com.learn.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("tags")
public class TagsController {


@Autowired
private TagService tagService;

        /**
         * 拉取最热标签
         *
         * */
        @GetMapping("hot")
        public Result hot(){
                int limit=6;
            return     tagService.hots(limit);
        }



}
