package com.anli.expensemana.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;

@RestController
@RequestMapping
public class HelloRestController {

    @GetMapping("/hello")
    public String getHello() {
        return "hello!";
    }

    @GetMapping("/test2")
    public String getTest2() {
        return "test2";
    }
}
