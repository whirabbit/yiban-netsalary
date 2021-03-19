package com.wrobby.sust.clock_in.handler;

import com.wrobby.sust.clock_in.controllor.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Component
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public CommonResult error(){
        return new CommonResult<>(100,"服务器出现未知异常");
    }
    @ExceptionHandler(ArithmeticException.class)
    public  CommonResult ss(){
        return new CommonResult<>(101,"算数异常");
    }
//    @ExceptionHandler(RuntimeException.class)
//    public  CommonResult login(){
//        return new CommonResult<>(102,"登录失效");
//    }
}
