package com.learn.blog.service;

import com.learn.blog.dao.pojo.SysUser;
import com.learn.blog.vo.Result;
import com.learn.blog.vo.params.LoginParam;

public interface LoginService {





    /**
     * 检测token
     *
     */
     SysUser checkToken(String token);

    /**
     * 登录功能
     * @Param loginParam
     *
     */
    Result login(LoginParam loginParam);

    /**
     * 退出登录
     * @Param token
     * @return
     *
     * */
    Result logout(String token);

}
