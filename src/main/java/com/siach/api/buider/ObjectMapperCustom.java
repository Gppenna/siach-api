package com.siach.api.buider;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.text.SimpleDateFormat;
import java.util.Locale;

public final class ObjectMapperCustom {
    private ObjectMapperCustom(){}
    public static ObjectMapper builder(){
        ObjectMapper objectMapper = JsonMapper.builder()
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .build();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ROOT));
        return objectMapper;
    }
}
