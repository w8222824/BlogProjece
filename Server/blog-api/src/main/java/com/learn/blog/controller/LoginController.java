package com.learn.blog.controller;


import com.learn.blog.service.LoginService;
import com.learn.blog.vo.Result;
import com.learn.blog.vo.params.LoginParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping
    public Result login(@RequestBody LoginParam loginParam){
        return   loginService.login(loginParam);

    }


}
