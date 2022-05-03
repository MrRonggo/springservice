package com.tutorial.springservice.app.controller;

import com.tutorial.springservice.app.presenterImpl.ObjectPresenterImpl;
import com.tutorial.springservice.core.gateway.PersonGateway;
import com.tutorial.springservice.core.request.PersonCreateRequest;
import com.tutorial.springservice.core.service.PersonCreate;
import com.tutorial.springservice.persistence.entity.Person;
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
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PersonControllerTest {
    private static final int TOTAL_DATA = 3;
    private static final String FIRSTNAME = "person";
    private static final int INVOCATION = 1;
    private static final long ID = 1L;
    private static final int DATE_OF_BIRTH = 19960907;

    @Mock
    PersonGateway gateway;
    @Mock
    PersonCreate createService;

    PersonController controller;
    MockHttpServletRequest request;

    @BeforeEach
    void setUp() {
        controller = new PersonController(gateway, createService);
        request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    private Page<Person> constructPagePerson() {
        List<Person> personList = new ArrayList<>();
        for (long i = 1; i <= TOTAL_DATA; i++)
            personList.add(Person.builder()
                            .id(i)
                            .firstName(FIRSTNAME+i)
                    .build());

       return new PageImpl<>(personList);
    }

    private Person constructPerson() {
        return Person.builder()
                .id(ID)
                .firstName(FIRSTNAME)
                .dateOfBirth(DATE_OF_BIRTH)
                .build();
    }

    @Test
    public void home() throws Exception {
        final int pageNo = 0;
        final int pageSize = 1;
        final int invocation = 1;

        Page<Person> expected = constructPagePerson();
        when(gateway.find(any(Pageable.class))).thenReturn(expected);

        Page<Person> result = controller.home(request, pageNo, pageSize);

        ArgumentCaptor<Pageable> captor = ArgumentCaptor.forClass(Pageable.class);
        verify(gateway, times(invocation)).find(captor.capture());
        Pageable argument = captor.getValue();

        assertEquals(pageNo, argument.getPageNumber());
        assertEquals(pageSize, argument.getPageSize());
        assertEquals(expected, result);
    }

    @Test
    public void get() throws Exception {
        final long id = 1L;

        Person expected = constructPerson();
        when(gateway.findById(anyLong())).thenReturn(expected);

        Person result = controller.get(request, id);

        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        verify(gateway, times(INVOCATION)).findById(captor.capture());
        Long argument = captor.getValue();

        assertEquals(id, argument);
        assertEquals(expected, result);
    }

    @Test
    public void update() throws Exception {
        Person expected = constructPerson();
        when(gateway.update(any(Person.class))).thenReturn(expected);

        Person result = controller.update(request, expected);

        ArgumentCaptor<Person> captor = ArgumentCaptor.forClass(Person.class);
        verify(gateway, times(INVOCATION)).update(captor.capture());
        Person argument = captor.getValue();

        assertEquals(expected,argument);
        assertEquals(expected, result);
    }

    @Test
    public void create() throws Exception {
        PersonCreateRequest body = PersonCreateRequest.builder()
                .firstName(FIRSTNAME)
                .dateOfBirth(DATE_OF_BIRTH)
                .build();

        Person result = controller.create(request, body);

        ArgumentCaptor<PersonCreateRequest> bodyCaptor = ArgumentCaptor.forClass(PersonCreateRequest.class);
        verify(createService, times(INVOCATION)).serve(bodyCaptor.capture(), any(ObjectPresenterImpl.class));
        PersonCreateRequest argument = bodyCaptor.getValue();

        assertEquals(FIRSTNAME, argument.getFirstName());
        assertEquals(DATE_OF_BIRTH, argument.getDateOfBirth());
        assertNull(result);
    }
}

