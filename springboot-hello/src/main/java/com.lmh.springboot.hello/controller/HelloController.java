package com.lmh.springboot.hello.controller;

import com.lmh.springboot.hello.limit.TPSLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (C), 2019, 巴图鲁信息科技有限公司
 * FileName: HelloController
 * Author:   liaominghuikero@gmail.com
 * Date:     2019/6/11 21:43
 * Description: TODO
 * Version:1.0.0
 */
@RestController
@RequestMapping("springboot")
public class HelloController {


    @Autowired
    private TPSLimiter limiter;

    @RequestMapping("/hello")
    public String hello() {
        System.out.println(limiter.isAllowable("hello", 10, 6000l));
        return "Hello World";
    }
}
