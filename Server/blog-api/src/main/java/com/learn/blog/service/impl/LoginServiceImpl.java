package com.learn.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.learn.blog.dao.pojo.SysUser;
import com.learn.blog.service.LoginService;
import com.learn.blog.service.SysUserService;
import com.learn.blog.vo.ErrorCode;
import com.learn.blog.vo.Result;
import com.learn.blog.vo.params.LoginParam;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.learn.blog.utils.JWTUtils;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {


    private static final String slat="mszlu!@#";            //加密言

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisTemplate<String,String>  redisTemplate;




    @Override
    public Result login(LoginParam loginParam) {
        //检查参数是否合法
        //根据账号密码去user表中查询是否存在
        //如果不存在就失败  存在就使用Jwt生成token返回给前端
        //把toekn放入redis中, redis token:user信息 设置过期时间(登录认证的时候 先认证token字符串是否合法, redis认证是否存在).
        String account =loginParam.getAccount();
        String  password= loginParam.getPassword();

        if (StringUtils.isBlank(account)||StringUtils.isBlank(password)){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }

        password= DigestUtils.md5Hex(password+slat);            //md5加密
        SysUser sysUser= sysUserService.findUser(account,password);
        if (sysUser==null){
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        String token=JWTUtils.createToken(sysUser.getId());
        //将token和sysUser 存到redis中 过期时间为1天
        redisTemplate.opsForValue().set("Token_"+token, JSON.toJSONString(sysUser),1, TimeUnit.DAYS);

        return Result.success(token);
    }

    @Override
    public Result logout(String token) {

        redisTemplate.delete("Token_"+token);
        return Result.success(null);
    }

    @Override
    public SysUser checkToken(String token) {
        if (StringUtils.isBlank(token))
        {
            return  null;
        }
        Map<String,Object> stringObjectMap=JWTUtils.checkToken(token);
        if (stringObjectMap==null)
        {
            return  null;
        }
       String userJson= redisTemplate.opsForValue().get("Token_"+token);
        if (StringUtils.isBlank(userJson)){
            return  null;  //过期了也返回null
        }

        SysUser sysUser=JSON.parseObject(userJson,SysUser.class);  //json转成json对象

        return sysUser;
    }





}
