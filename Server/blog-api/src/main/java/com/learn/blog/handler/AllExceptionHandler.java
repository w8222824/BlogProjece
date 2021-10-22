package com.learn.blog.handler;


import com.learn.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
//对加了@COntroller注解的方法进行拦截处理  AOP的实现
@ControllerAdvice
public class AllExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody    //返回json数据
    public Result error(Exception e) {
        e.printStackTrace();
        log.error("全局异常捕获1：" + e);

        return Result.fail(-999,"系统异常");    // 通用异常结果
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    //空指针处理方法
    public Result error(NullPointerException e) {
        e.printStackTrace();
        log.error("全局异常捕获2：" + e);
        return Result.fail(-9999,"空指针异常");    // 通用异常结果
    }

    //下标越界处理方法
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseBody
    public Result error(IndexOutOfBoundsException e) {
        e.printStackTrace();
        log.error("全局异常捕获3：" + e);
        return Result.fail(-99999,"下标越界异常");    // 通用异常结果
    }


}
