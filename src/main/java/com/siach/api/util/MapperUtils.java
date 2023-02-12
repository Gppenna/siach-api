package com.siach.api.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.siach.api.buider.ObjectMapperCustom;
import com.siach.api.exception.BusinessException;
import com.siach.api.model.entity.AtividadeBarema;
import com.siach.api.model.entity.GrupoBarema;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.hibernate.internal.CoreLogging.logger;

public final class MapperUtils {
    private static final List<Class<?>> LEAF = Arrays.asList(
            String.class,
            LocalDateTime.class,
            LocalDate.class,
            AtividadeBarema.class,
            GrupoBarema.class,
            byte[].class
            );
    private MapperUtils() {}
    public static <T> T getGenericDTO(Map<String, Object> map, TypeReference<T> toValueTypeRef){
        ObjectMapper objectMapper = ObjectMapperCustom.builder()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());

        return objectMapper.convertValue(map, toValueTypeRef);

    }

    public static <T> List<T> getGenericListDTO(List<Map<String, Object>> mapList, TypeReference<T> toValueTypeRef){
        ObjectMapper objectMapper = ObjectMapperCustom.builder()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
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

    public static Object instanciateObject(Object object) {
        try {
            Field[] fields = object.getClass().getDeclaredFields();
            Object objectTmp = object.getClass().getConstructor().newInstance();
            for (Field field : fields) {
                Class<?> value = getGetter(field, objectTmp).getReturnType();
                if (isLeafNode(value)) {
                    getSetter(field, objectTmp).invoke(objectTmp, (Object) null);
                } else {
                    getSetter(field, objectTmp).invoke(objectTmp, instanciateObject(value.getConstructor().newInstance()));
                }
            }
            return objectTmp;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
            throw new BusinessException("Error in instanciation", ex);
        }
    }

    public static Object mergeObjects(List<Object> objects) {
        Object destinationObject = objects.get(0);
        for (int i = 1; i < objects.size(); i++) {
            mergeObjects(destinationObject, objects.get(i));
        }
        return destinationObject;
    }

    private static void mergeObjects(Object destinationObject, Object sourceObject) {
        try {
            Field[] destinationFields = destinationObject.getClass().getDeclaredFields();
            Field[] sourceFields = sourceObject.getClass().getDeclaredFields();
            for (Field destinationField : destinationFields) {
                Object destinationValue = getGetter(destinationField, destinationObject).invoke(destinationObject);

                if (destinationValue == null || isLeafNode(destinationValue.getClass())) {
                    mergeLeafNodes(destinationField, destinationValue, destinationObject, sourceFields, sourceObject);
                } else {
                    mergeNonLeafNodes(destinationValue, sourceObject);
                }
            }
        } catch ( IllegalAccessException | InvocationTargetException ex) {
            throw new BusinessException("Error in merge", ex);
        }

    }
    public static Method getGetter(Field field, Object object) {
        try {
            String fieldName = field.getName();
            Locale locale = Locale.ENGLISH;
            String getterName = "get" + fieldName.substring(0, 1).toUpperCase(locale) + fieldName.substring(1);
            return object.getClass().getMethod(getterName);
        } catch (NoSuchMethodException ex) {
            throw new BusinessException("Error in getter", ex);
        }

    }

    public static Method getSetter(Field field, Object object) {
        try {
            String fieldName = field.getName();
            Locale locale = Locale.ENGLISH;
            String setterName = "set" + fieldName.substring(0, 1).toUpperCase(locale) + fieldName.substring(1);
            return object.getClass().getMethod(setterName, field.getType());
        } catch (NoSuchMethodException ex) {
            throw new BusinessException("Error in getter", ex);
        }

    }


    private static void mergeLeafNodes(Field destinationField, Object destinationValue, Object destinationObject, Field[] sourceFields, Object sourceObject) {
        try {
            for (Field sourceField : sourceFields) {
                Object sourceValue = getGetter(sourceField, sourceObject).invoke(sourceObject);

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

    private static void mergeNonLeafNodes(Object destinationValue, Object sourceObject) {
        List<Object> objects = new ArrayList<>();
        objects.add(destinationValue);
        objects.add(sourceObject);
        mergeObjects(objects);
    }

    private static Object chooseSourceOrDestinationValue(Object destinationValue, Object sourceValue) {
        return sourceValue == null ? destinationValue : sourceValue;
    }
}
