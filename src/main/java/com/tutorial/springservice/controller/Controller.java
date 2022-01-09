package com.tutorial.springservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.tutorial.springservice.constant.RouteConstant.*;

@RestController
public class Controller {

    @GetMapping(value = HELLO)
    public String hello() {
        return "Hello World!";
    }

    @GetMapping(value = HELLO_LIST)
    public List<String> helloList() {
        return List.of("Hello", "World!");
    }
}
