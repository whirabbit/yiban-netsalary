package com.wrobby.sust.clock_in.controllor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

@GetMapping("/hello")
    String hello(){
    //sendEmailUtil.send("邮件系统测试");
        return "系统正在运行";
    }
    @GetMapping("/")
    String index(){
    return "<h1>这里是欢迎页<h1>";
    }
    @GetMapping("/error")
    String error(){
    throw new  ArithmeticException();
   // return "看到这个说明有bug了";
    }
}
