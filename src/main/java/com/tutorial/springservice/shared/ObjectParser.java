package com.tutorial.springservice.shared;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectParser {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String toJsonString(Object obj) throws JsonProcessingException {
        return MAPPER.writeValueAsString(obj);
    }

    public static <T, S> T map(S obj, Class<T> toClass) {
        if(obj == null || toClass == null) return null;

        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return MAPPER.convertValue(obj, toClass);
    }
}
