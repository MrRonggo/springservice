package com.tutorial.springservice.controller;

import com.tutorial.springservice.entity.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static com.tutorial.springservice.constant.PersonRouteConstant.*;

@RestController
public class PersonController {

    @GetMapping(value = PEOPLE)
    public List<Person> people() {
        return List.of(
                Person.builder()
                        .id(1L)
                        .name("Wresni Ronggowerdhi")
                        .dayOfBirth(LocalDate.of(1996, Month.SEPTEMBER, 7))
                        .email("wresni.ronggowerdhi@gmail.com")
                        .build()
        );
    }
}
