package com.learn.blog.service;

import com.learn.blog.vo.Result;
import com.learn.blog.vo.params.LoginParam;

public interface LoginService {


    /**
     * 登录功能
     * @Param loginParam
     *
     * */
    Result login(LoginParam loginParam);
}
