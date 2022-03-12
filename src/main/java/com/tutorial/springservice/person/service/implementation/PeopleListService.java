package com.tutorial.springservice.person.service.implementation;

import com.tutorial.springservice.person.entity.Person;
import com.tutorial.springservice.person.service.PeopleList;
import com.tutorial.springservice.person.service.presenter.PeopleListPresenter;
import com.tutorial.springservice.person.service.request.PeopleListRequest;
import com.tutorial.springservice.person.service.response.PeopleListResponse;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class PeopleListService implements PeopleList {
    @Override
    public void serve(PeopleListRequest request, PeopleListPresenter presenter) {
        PeopleListResponse response = PeopleListResponse.builder()
                .people(getPeople())
                .build();

        presenter.present(response);
    }

    private List<Person> getPeople() {
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
