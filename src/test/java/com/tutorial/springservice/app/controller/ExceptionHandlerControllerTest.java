package com.tutorial.springservice.app.controller;

import com.tutorial.springservice.core.exception.notFound.NoPersonFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ExceptionHandlerControllerTest {

    ExceptionHandlerController controller;
    MockHttpServletRequest request;

    @BeforeEach
    void setUp() {
        controller = new ExceptionHandlerController();
        request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    void internalServerError() {
        ResponseEntity<Object> result = controller.internalServerError(new Exception(), request);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), result.getStatusCodeValue());
    }

    @Test
    void notFound() {
        ResponseEntity<Object> result = controller.notFound(new NoPersonFoundException(), request);
        assertEquals(HttpStatus.NOT_FOUND.value(), result.getStatusCodeValue());
    }
}
