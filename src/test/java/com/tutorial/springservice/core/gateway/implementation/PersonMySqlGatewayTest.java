package com.tutorial.springservice.core.gateway.implementation;

import com.tutorial.springservice.app.constant.PageConstant;
import com.tutorial.springservice.core.exception.notFound.NoDataFoundException;
import com.tutorial.springservice.persistence.entity.Person;
import com.tutorial.springservice.persistence.mapper.PersonMapper;
import com.tutorial.springservice.persistence.repository.PersonRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PersonMySqlGatewayTest {
    private static final Long ID = 1L;
    private static final String FIRSTNAME = "firstname";
    private static final int DATE_OF_BIRTH = 19960907;
    private static final int INVOCATION = 1;
    private static final int TOTAL_DATA = 1;

    @Mock
    PersonRepo repo;

    PersonMySqlGateway gateway;

    @BeforeEach
    void setUp() {
        gateway = new PersonMySqlGateway(repo);
    }

    private PersonMapper constructPersonMapper() {
        return PersonMapper.builder()
                .id(ID)
                .firstName(FIRSTNAME)
                .dateOfBirth(DATE_OF_BIRTH)
                .build();
    }

    private Person constructPerson() {
        return Person.builder()
                .id(ID)
                .firstName(FIRSTNAME)
                .dateOfBirth(DATE_OF_BIRTH)
                .build();
    }

    @Test
    void findById() {
        PersonMapper person = constructPersonMapper();
        when(repo.findById(anyLong())).thenReturn(Optional.of(person));

        Person result = gateway.findById(ID);

        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        verify(repo, times(INVOCATION)).findById(captor.capture());
        Long argument = captor.getValue();

        assertEquals(ID, argument);
        assertEquals(ID, result.getId());
        assertEquals(FIRSTNAME, result.getFirstName());
        assertEquals(DATE_OF_BIRTH, result.getDateOfBirth());
    }

    @Test
    void find() {
        PersonMapper person = constructPersonMapper();
        when(repo.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(person)));

        Page<Person> result = gateway.find(PageRequest.of(PageConstant.DEFAULT_PAGE_NO, PageConstant.DEFAULT_PAGE_SIZE));
        Person resultContent = result.getContent().get(0);

        ArgumentCaptor<Pageable> captor = ArgumentCaptor.forClass(Pageable.class);
        verify(repo, times(INVOCATION)).findAll(captor.capture());
        Pageable argument = captor.getValue();

        assertEquals(PageConstant.DEFAULT_PAGE_NO, argument.getPageNumber());
        assertEquals(PageConstant.DEFAULT_PAGE_SIZE, argument.getPageSize());
        assertNotNull(result.getContent());
        assertEquals(TOTAL_DATA, result.getContent().size());
        assertEquals(ID, resultContent.getId());
        assertEquals(FIRSTNAME, resultContent.getFirstName());
        assertEquals(DATE_OF_BIRTH, resultContent.getDateOfBirth());
    }

    @Test
    void save() {
        PersonMapper person = constructPersonMapper();
        Person request = constructPerson();
        request.setId(null);
        when(repo.save(any(PersonMapper.class))).thenReturn(person);

        Person result = gateway.save(request);

        ArgumentCaptor<PersonMapper> captor = ArgumentCaptor.forClass(PersonMapper.class);
        verify(repo, times(INVOCATION)).save(captor.capture());
        PersonMapper argument = captor.getValue();

        assertNull(argument.getId());
        assertEquals(ID, result.getId());
        assertEquals(FIRSTNAME, result.getFirstName());
        assertEquals(DATE_OF_BIRTH, result.getDateOfBirth());
    }

    @Test
    void update() throws NoDataFoundException {
        PersonMapper person = constructPersonMapper();
        Person request = constructPerson();
        when(repo.findById(anyLong())).thenReturn(Optional.of(person));
        when(repo.save(any(PersonMapper.class))).thenReturn(person);

        Person result = gateway.update(request);

        ArgumentCaptor<Long> findCaptor = ArgumentCaptor.forClass(Long.class);
        verify(repo, times(INVOCATION)).findById(findCaptor.capture());
        Long findArgument = findCaptor.getValue();

        ArgumentCaptor<PersonMapper> captor = ArgumentCaptor.forClass(PersonMapper.class);
        verify(repo, times(INVOCATION)).save(captor.capture());
        PersonMapper argument = captor.getValue();

        assertEquals(ID, findArgument);
        assertNotNull(argument.getId());
        assertEquals(ID, result.getId());
        assertEquals(FIRSTNAME, result.getFirstName());
        assertEquals(DATE_OF_BIRTH, result.getDateOfBirth());
    }
}
