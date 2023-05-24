package com.siach.api.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.siach.api.buider.ObjectMapperCustom;
import com.siach.api.exception.BusinessException;
import com.siach.api.model.entity.AtividadeBarema;
import com.siach.api.model.entity.GrupoBarema;
import net.minidev.json.JSONObject;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static org.hibernate.internal.CoreLogging.logger;

public final class MapperUtils {
    private static final List<Class<?>> LEAF = Arrays.asList(
            String.class,
            LocalDateTime.class,
            LocalDate.class,
            BigDecimal.class,
            List.class,
            ArrayList.class,
            Boolean.class,
            byte[].class,
            MultipartFile.class
    );
    private MapperUtils() {}
    public static <T> T getGenericDTO(Map<String, Object> map, TypeReference<T> toValueTypeRef){
        ObjectMapper objectMapper = ObjectMapperCustom.builder()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module());
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));
        objectMapper.registerModule(javaTimeModule);

        return objectMapper.convertValue(map, toValueTypeRef);

    }

    public static <T> List<T> getGenericListDTO(List<Map<String, Object>> mapList, TypeReference<T> toValueTypeRef){
        ObjectMapper objectMapper = ObjectMapperCustom.builder()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module());
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));
        objectMapper.registerModule(javaTimeModule);
        return mapList.stream()
                .map( t-> {
                    return objectMapper.convertValue(t, toValueTypeRef);
                })
                .collect(Collectors.toList());
    }
    private static boolean isLeafNode(Class<?> cls) {
        for (Class<?> itClass : LEAF) {
            if(cls.equals(itClass)) {
                return true;
            }
        }
        return (ClassUtils.isPrimitiveOrWrapper(cls));
    }

    public static Object instantiateObject(Object object) {

        try {
            Field[] fields = object.getClass().getDeclaredFields();
            Object objectTmp = object.getClass().getConstructor().newInstance();
            for (Field field : fields) {
                Method fieldMethod = getGetter(field, objectTmp);
                if(fieldMethod == null) {
                    return null;
                }
                Class<?> value = fieldMethod.getReturnType();
                if (isLeafNode(value)) {
                    getSetter(field, objectTmp).invoke(objectTmp, (Object) null);
                } else {
                    getSetter(field, objectTmp).invoke(objectTmp, instantiateObject(value.getConstructor().newInstance()));
                }
            }
            return objectTmp;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
            throw new BusinessException("Error in instanciation", ex);
        }
    }
    @lombok.Generated
    public static Object mergeObjects(List<Object> objects) {

        Object destinationObject = objects.get(0);

        for (int i = 1; i < objects.size(); i++) {
            mergeObjects(destinationObject, objects.get(i));
        }
        return destinationObject;
    }
    @lombok.Generated
    private static void mergeObjects(Object destinationObject, Object sourceObject) {
        try {
            Field[] destinationFields = destinationObject.getClass().getDeclaredFields();

            Field[] sourceFields = sourceObject.getClass().getDeclaredFields();

            for (Field destinationField : destinationFields) {
                Method destinationMethod = getGetter(destinationField, destinationObject);
                if(destinationMethod == null) {
                    return;
                }

                Object destinationValue = destinationMethod.invoke(destinationObject);

                if (destinationValue == null || isLeafNode(destinationValue.getClass())) {

                    mergeLeafNodes(destinationField, destinationValue, destinationObject, sourceFields, sourceObject);
                } else {

                    mergeNonLeafNodes(destinationValue, sourceObject);
                }


            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new BusinessException("Merge objects erro", e);
        }

    }
    @lombok.Generated
    public static Method getGetter(Field field, Object object) {
        try {
            if(field.isSynthetic()) {
                return null;
            }
            String fieldName = field.getName();

            Locale locale = Locale.ENGLISH;
            String getterName = "get" + fieldName.substring(0, 1).toUpperCase(locale) + fieldName.substring(1);

            return object.getClass().getMethod(getterName);
        } catch (NoSuchMethodException ex) {
            throw new BusinessException(field.getName(), ex);
        }

    }
    @lombok.Generated
    public static Method getSetter(Field field, Object object) {
        try {
            String fieldName = field.getName();
            Locale locale = Locale.ENGLISH;
            String setterName = "set" + fieldName.substring(0, 1).toUpperCase(locale) + fieldName.substring(1);
            return object.getClass().getMethod(setterName, field.getType());
        } catch (NoSuchMethodException ex) {
            throw new BusinessException("Error in setter", ex);
        }

    }

    @lombok.Generated
    private static void mergeLeafNodes(Field destinationField, Object destinationValue, Object destinationObject, Field[] sourceFields, Object sourceObject) {

        try {
            for (Field sourceField : sourceFields) {
                Method sourceMethod = getGetter(sourceField, sourceObject);
                if(sourceMethod == null) {
                    return;
                }
                Object sourceValue = sourceMethod.invoke(sourceObject);

                if ((sourceValue == null || isLeafNode(sourceValue.getClass())) && sourceField.getName().equals(destinationField.getName())) {
                    getSetter(destinationField, destinationObject).invoke(destinationObject, chooseSourceOrDestinationValue(destinationValue, sourceValue));
                    break;
                } else if ((sourceValue == null || isLeafNode(sourceValue.getClass()))) {
                    logger(" mergeLeafNodes else");
                }
                else {
                    mergeNonLeafNodes(destinationObject, sourceValue);
                }


            }
        } catch ( IllegalAccessException | InvocationTargetException ex) {
            throw new BusinessException("Error in merge", ex);
        }

    }
    @lombok.Generated
    private static void mergeNonLeafNodes(Object destinationValue, Object sourceObject) {
        List<Object> objects = new ArrayList<>();
        objects.add(destinationValue);
        objects.add(sourceObject);
        mergeObjects(objects);
    }
    @lombok.Generated
    private static Object chooseSourceOrDestinationValue(Object destinationValue, Object sourceValue) {
        return sourceValue == null ? destinationValue : sourceValue;
    }

}
