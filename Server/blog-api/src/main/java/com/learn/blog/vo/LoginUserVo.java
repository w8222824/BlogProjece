package com.learn.blog.vo;

import lombok.Data;

@Data
public class LoginUserVo {

    private String id;

    private String account;

    private String nickname;
    /**头像*/
    private String avatar;
}
