package com.tutorial.springservice.core.gateway;

import com.tutorial.springservice.core.exception.notFound.NoDataFoundException;
import com.tutorial.springservice.persistence.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonGateway {
    Person findById(long id);
    Page<Person> find(Pageable page);
    Person save(Person person);
    Person update(Person person) throws NoDataFoundException;
}
