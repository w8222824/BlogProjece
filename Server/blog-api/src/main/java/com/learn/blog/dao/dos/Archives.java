package com.learn.blog.dao.dos;


import lombok.Data;

/**
 * dos 是do层（因为do是关键字,所以加个s） 也是数据库查询出来的对象,但是不需要持久化的
 *
 *  档案类 归档类
 * */
@Data
public class Archives {
    private Integer year;

    private Integer month;

    private Long count;


}
