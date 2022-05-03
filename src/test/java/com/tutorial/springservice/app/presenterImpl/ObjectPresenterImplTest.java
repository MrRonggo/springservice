package com.tutorial.springservice.app.presenterImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ObjectPresenterImplTest {
    ObjectPresenterImpl<String> presenter;

    @BeforeEach
    void setUp() {
        presenter = new ObjectPresenterImpl<>();
    }

    @Test
    void present() {
        String expected = "test";
        presenter.present(expected);
        String result = presenter.getResponse();
        assertEquals(expected, result);
    }
}
