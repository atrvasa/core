package com.atrvasa.core.request;

import com.atrvasa.exception.Aenah;
import com.atrvasa.json.JsonConverter;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RequestTest {
    RequestInspector inspector;

    @BeforeEach
    void setUp() {
        inspector = new RequestInspector();
    }

    @Test
    @DisplayName("Requested data should not be null")
    void nullRequestTest() throws Exception {
        Aenah exception = null;

        try {
            inspector.initialize(null);
        } catch (RequestIsNullException ex) {
            exception = ex;
        }

        assertNotNull(exception);
    }

    @Test
    @DisplayName("Load request to json object")
    void loadRequestTest() throws Exception {

        String request = "{ session: {username: \"guest\"}, plugin: \"parameter\", model: \"item\", " +
                "method: \"getEditable\", delegate: \"inspect\", data: { key: \"gender\" } }";
        RequestModel requestModel = inspector.initialize(request);
        assertNotNull(requestModel);
        assertEquals("guest", requestModel.getSession().getUsername());
        assertEquals("parameter", requestModel.getPlugin());
        assertEquals("item", requestModel.getModel());
        assertEquals("getEditable", requestModel.getMethod());
        assertEquals("inspect", requestModel.getDelegate());
        JsonObject data = requestModel.getData();
        String field = new JsonConverter(data).getFieldAsString("key", null);
        assertEquals("gender", field);


    }

    @Test
    void pluginIsNullTest() throws Exception {
        PluginIsNullException exception = null;
        try {
            String request = "{}";
            RequestModel requestModel = inspector.initialize(request);
            assertNotNull(requestModel);
        } catch (PluginIsNullException e) {
            exception = e;
        }

        assertNotNull(exception);
    }
}