package com.tutorial.springservice.core.service.implementation;

import com.tutorial.springservice.app.presenterImpl.ObjectPresenterImpl;
import com.tutorial.springservice.core.gateway.PersonGateway;
import com.tutorial.springservice.core.request.PersonCreateRequest;
import com.tutorial.springservice.persistence.entity.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PersonCreateServiceTest {
    private static final Long ID = 1L;
    private static final String FIRSTNAME = "firstname";
    private static final int DATE_OF_BIRTH = 19960907;
    private static final int INVOCATION = 1;

    @Mock
    PersonGateway gateway;

    PersonCreateService service;
    ObjectPresenterImpl<Person> presenter;
    PersonCreateRequest request;

    @BeforeEach
    void setUp() {
        service = new PersonCreateService(gateway);
        presenter = new ObjectPresenterImpl<>();
        request = PersonCreateRequest.builder()
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
    void serve() {
        Person expected = constructPerson();
        when(gateway.save(any(Person.class))).thenReturn(expected);

        service.serve(request, presenter);
        Person result = presenter.getResponse();

        ArgumentCaptor<Person> captor = ArgumentCaptor.forClass(Person.class);
        verify(gateway, times(INVOCATION)).save(captor.capture());

        Person argument = captor.getValue();

        assertNull(argument.getId());
        assertEquals(expected, result);
    }
}
