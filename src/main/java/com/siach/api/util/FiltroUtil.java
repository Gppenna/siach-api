package com.siach.api.util;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.siach.api.exception.BusinessException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public final class FiltroUtil {
    private FiltroUtil() {}
    public static <T> T buildFilter(String query, Class<T> dtoObject){

        if(!query.isEmpty()){
            String[] kvPairs = query.split(";");
            Map<String, String> map = new HashMap<>(kvPairs.length);

            for(String kvPair: kvPairs) {
                String[] kv = kvPair.split("=");
                String key = kv[0];
                String value = kv[1];
                map.put(key,value);
            }

            ObjectMapper mapper = JsonMapper.builder()
                    .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
                    .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                    .addModule(new JavaTimeModule())
                    .build();

            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            return mapper.convertValue(map, dtoObject);
        }
        return (T) dtoObject;
    }

    public static <T> T getFilter(String query, Class<T> dtoObject) {
        try{
            T object = dtoObject.getDeclaredConstructor().newInstance();
            if(!query.isEmpty()){
                return buildFilter(query, dtoObject);
            }
            return object;
        }catch(InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex){
            throw new BusinessException("Erro na criação do filtro", ex);
        }
    }

}
