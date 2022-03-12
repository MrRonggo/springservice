package com.tutorial.springservice.person.service.presenter.implementation;

import com.tutorial.springservice.person.service.presenter.PeopleListPresenter;
import com.tutorial.springservice.person.service.response.PeopleListResponse;
import lombok.Getter;

@Getter
public class PeopleListBasicPresenter implements PeopleListPresenter {
    private PeopleListResponse response;

    @Override
    public void present(PeopleListResponse response) {
        this.response = response;
    }
}
