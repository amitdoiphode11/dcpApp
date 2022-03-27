package com.eaglesoft.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class HelloController {

    @GetMapping("/hello")
    public String helloString(){
        return "Hi Aarya!!! ";
    }
}
