package com.tutorial.springservice.person.controller;

import com.tutorial.springservice.person.entity.Person;
import com.tutorial.springservice.person.constant.PersonRouteConstant;
import com.tutorial.springservice.person.service.PeopleList;
import com.tutorial.springservice.person.service.implementation.PeopleListService;
import com.tutorial.springservice.person.service.presenter.PeopleListPresenter;
import com.tutorial.springservice.person.service.presenter.implementation.PeopleListBasicPresenter;
import com.tutorial.springservice.person.service.request.PeopleListRequest;
import com.tutorial.springservice.person.service.response.PeopleListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = PersonRouteConstant.PATH)
public class PersonController {

    private static final PeopleList peopleList = new PeopleListService();

    @GetMapping
    public String home() {
        return "Welcome to people page!";
    }

    @GetMapping(value = PersonRouteConstant.URL_LIST)
    public PeopleListResponse people() {
        PeopleListBasicPresenter presenter = new PeopleListBasicPresenter();
        peopleList.serve(new PeopleListRequest(), presenter);
        return presenter.getResponse();
    }
}
