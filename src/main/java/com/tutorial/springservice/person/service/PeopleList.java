package com.tutorial.springservice.person.service;

import com.tutorial.springservice.person.service.presenter.PeopleListPresenter;
import com.tutorial.springservice.person.service.request.PeopleListRequest;
import org.springframework.stereotype.Service;

@Service
public interface PeopleList {
    void serve(PeopleListRequest request, PeopleListPresenter presenter);
}
