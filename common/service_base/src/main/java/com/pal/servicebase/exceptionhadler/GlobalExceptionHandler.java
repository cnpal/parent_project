package com.pal.servicebase.exceptionhadler;

import com.pal.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author pal
 * @Date Created in 2020/9/4 12:55
 * @Version: 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
//指定处理的异常
    @ExceptionHandler({Exception.class})
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理");
    }
//    自定义异常
    @ExceptionHandler({MyException.class})
    public R error(MyException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
