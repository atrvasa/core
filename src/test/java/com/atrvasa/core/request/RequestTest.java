package com.atrvasa.core.request;

import com.atrvasa.exception.Aenah;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RequestTest {
    RequestInspector inspector;

    @BeforeEach
    void setUp() {
        inspector = new RequestInspector();
    }

    @Test
    @DisplayName("Requested data should not be null")
    void nullRequestTest() {
        Aenah exception = null;

        try {
            inspector.initialize(null);
        } catch (RequestIsNullException ex) {
            exception = ex;
        }

        assertNotNull(exception);
    }
}