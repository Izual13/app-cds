package com.template.hello.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;
import java.util.Map;

public class JsonConverter implements AttributeConverter<Map<String, String>, String> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(Map<String, String> attribute) {
        return OBJECT_MAPPER.writeValueAsString(attribute);
    }

    @SneakyThrows
    @Override
    public Map<String, String> convertToEntityAttribute(String dbData) {
        if (dbData.equals("{}")) {
            throw new RuntimeException();
        }
        return OBJECT_MAPPER.readValue(dbData, new TypeReference<Map<String, String>>() {
        });
    }
}