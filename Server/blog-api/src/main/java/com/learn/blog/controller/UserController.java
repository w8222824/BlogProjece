package com.learn.blog.controller;


import com.learn.blog.service.LoginService;
import com.learn.blog.service.SysUserService;
import com.learn.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private SysUserService sysUserService;






    @GetMapping("currentUser")
    public Result currentUser(@RequestHeader("Authorization")  String token )
    {




        return  sysUserService.findUserByToken(token);
    }




}

