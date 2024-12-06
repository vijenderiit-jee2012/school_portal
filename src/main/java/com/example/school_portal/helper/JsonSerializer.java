package com.example.school_portal.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.serializer.Serializer;

import java.io.IOException;
import java.io.OutputStream;

public class JsonSerializer implements Serializer<Object> {

    private final ObjectMapper objectMapper;

    public JsonSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void serialize(Object object, OutputStream outputStream) throws IOException {
        System.out.println("FUCK : " +object);
        this.objectMapper.writeValue(outputStream, object);
    }
}