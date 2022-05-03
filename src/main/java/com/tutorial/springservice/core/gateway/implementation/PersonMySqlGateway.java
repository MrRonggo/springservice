package com.tutorial.springservice.core.gateway.implementation;

import com.tutorial.springservice.core.exception.notFound.NoDataFoundException;
import com.tutorial.springservice.core.exception.notFound.NoPersonFoundException;
import com.tutorial.springservice.core.gateway.PersonGateway;
import com.tutorial.springservice.persistence.entity.Person;
import com.tutorial.springservice.persistence.mapper.PersonMapper;
import com.tutorial.springservice.persistence.repository.PersonRepo;
import com.tutorial.springservice.shared.ObjectParser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonMySqlGateway implements PersonGateway {
    private final PersonRepo repo;

    private PersonMapper toMapper(Person entity) {
        return ObjectParser.map(entity, PersonMapper.class);
    }

    private Person toEntity(PersonMapper mapper) {
        return ObjectParser.map(mapper, Person.class);
    }

    @Override
    public Person findById(long id) {
        return toEntity(repo.findById(id).orElse(null));
    }

    @Override
    public Page<Person> find(Pageable page) {
        return repo.findAll(page).map(this::toEntity);
    }

    @Override
    public Person save(Person person) {
        return toEntity(repo.save(toMapper(person)));
    }

    @Override
    public Person update(Person person) throws NoDataFoundException {
        PersonMapper mapper = repo.findById(person.getId()).orElseThrow(NoPersonFoundException::new);
        mapper.setFirstName(person.getFirstName());
        mapper.setLastName(person.getLastName());
        mapper.setDateOfBirth(person.getDateOfBirth());
        return toEntity(repo.save(mapper));
    }
}
