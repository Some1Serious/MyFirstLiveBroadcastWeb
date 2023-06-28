package com.example.demo.pojo.result;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public class StatusSerializer extends JsonSerializer<HttpStatus> {

    @Override
    public void serialize(HttpStatus status, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {
        jsonGenerator.writeNumber(status.value());
    }
}