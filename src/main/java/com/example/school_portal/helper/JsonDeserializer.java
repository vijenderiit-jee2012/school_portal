package com.example.school_portal.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.serializer.Deserializer;

import java.io.IOException;
import java.io.InputStream;

public class JsonDeserializer implements Deserializer<Object> {

    private final ObjectMapper objectMapper;

    public JsonDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Object deserialize(InputStream inputStream) throws IOException {
        System.out.println("input : " + inputStream.toString());

        return this.objectMapper.readValue(inputStream, Object.class);
    }
}