package com.learn.blog.utils;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
/**
 *登录校验jwt
 *jwt可以生成一个加密的token，作为用户登录的令牌,当用户登录成功之后,发放给客户端
 * 请求需要登录的资源或者接口的时候,将token携带,后端校验token是否合法。
 *
 * jwt有3部分组成 a b c
 * a: Header,{“type”:"JWT",“alg”:"HS256"}   固定写法 可以被解密
 *
 * b: playload(负载),存放信息,比如,用户id，过期时间等等，可以被解密,所以不能存放敏感信息
 *
 * c: 签证,a和b加上秘钥 加密而成,只要秘钥不丢失,可认为是安全的
 *
 *jwt验证,主要就是验证c部分是否合法
 * */

public class JWTUtils {
    /**秘钥  不能丢 也不能给别人知道*/
    private static final String jwtToken = "123456Mszlu!@#$$";

    public static String createToken(Long userId){
        Map<String,Object> claims = new HashMap<>();        //b部分
        claims.put("userId",userId);                        //b部分
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtToken) // 签发算法，秘钥为jwtToken  这个是a部分
                .setClaims(claims) // body数据，要唯一，自行设置 b部分
                .setIssuedAt(new Date()) // 设置签发时间
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 60 * 1000));// 一天的有效时间
        String token = jwtBuilder.compact();   //规则定义好后生成token
        return token;
    }

    /**
     * 校验token是否合法
     *
     *
     *
     * */
    public static Map<String, Object> checkToken(String token){
        try {
            Jwt parse = Jwts.parser().setSigningKey(jwtToken).parse(token);     //根据秘钥解析token是否正确
            return (Map<String, Object>) parse.getBody();                       //如果顺利解析出来 走到这里拿到的是b部分
        }catch (Exception e){
            e.printStackTrace();           //解析失败
        }
        return null;                        //失败 返回null

    }



    /**
     * 开发的时候快速测试逻辑
     *
     * */
    public static void main(String[] args) {
        String token = JWTUtils.createToken(112345L);
        System.out.println(token);
        Map<String, Object> map = JWTUtils.checkToken(token);
        System.out.println(map.get("userId"));
    }

}
