package com.tutorial.springservice.core.service;

import com.tutorial.springservice.core.presenter.ObjectPresenter;
import com.tutorial.springservice.core.request.PersonCreateRequest;
import com.tutorial.springservice.persistence.entity.Person;

public interface PersonCreate {
    void serve(PersonCreateRequest request, ObjectPresenter<Person> presenter);
}
