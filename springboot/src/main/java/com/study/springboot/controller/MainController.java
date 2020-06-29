package com.study.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Cdu
 * @discription:
 * @create 2020-06-23 23:04
 */
@Controller
public class MainController {

    @RequestMapping("/hello")
    @ResponseBody
    public String toMain(){
        return "Hello";
    }
}