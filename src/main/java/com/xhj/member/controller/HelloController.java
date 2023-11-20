package com.xhj.member.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xhj
 * @Date: 2023/11/20/12:05
 * @Description:
 */
@RestController
@RequestMapping("hello")
public class HelloController {

    @RequestMapping()
    public String hello(){
        return "hello";
    }

}
