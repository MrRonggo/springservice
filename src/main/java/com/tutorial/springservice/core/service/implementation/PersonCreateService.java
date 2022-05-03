package com.tutorial.springservice.core.service.implementation;

import com.tutorial.springservice.core.gateway.PersonGateway;
import com.tutorial.springservice.core.presenter.ObjectPresenter;
import com.tutorial.springservice.core.request.PersonCreateRequest;
import com.tutorial.springservice.core.service.PersonCreate;
import com.tutorial.springservice.persistence.entity.Person;
import com.tutorial.springservice.shared.ObjectParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PersonCreateService implements PersonCreate {
    private final PersonGateway gateway;

    @Override
    public void serve(PersonCreateRequest request, ObjectPresenter<Person> presenter) {
        Person newPerson = ObjectParser.map(request, Person.class);
        presenter.present(gateway.save(newPerson));
    }
}
