package com.busybox.topicmonkey.domain.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;

public class JsonConverter
        implements AttributeConverter<Object, String> {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Object meta) {
        try {
            return objectMapper.writeValueAsString(meta);
        } catch (JsonProcessingException ex) {
            throw new ConversionFailed(ex);
        }
    }

    @Override
    public Object convertToEntityAttribute(String data) {
        try {
            return objectMapper.readValue(data, Object.class);
        } catch (IOException ex) {
            return new ConversionFailed(ex);
        }
    }

    public static class ConversionFailed
            extends RuntimeException {

        public ConversionFailed(Throwable cause) {
            super(cause);
        }

    }

}
