package org.evrim.spring.exam.mvc.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    @ResponseBody
    public String hello(String name) {
        return "hello " + name;
    }

}
