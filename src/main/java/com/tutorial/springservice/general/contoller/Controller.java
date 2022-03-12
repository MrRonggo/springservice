package com.tutorial.springservice.general.contoller;

import com.tutorial.springservice.general.route.RouteConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @GetMapping
    public String welcome() {
        return "Welcome!";
    }

    @GetMapping(value = RouteConstant.HELLO)
    public String hello() {
        return "Hello World!";
    }

    @GetMapping(value = RouteConstant.HELLO_LIST)
    public List<String> helloList() {
        return List.of("Hello", "World!");
    }
}
