package com.wcq.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationConfig;
import org.apache.poi.ss.formula.functions.T;

import java.util.HashMap;
import java.util.Map;

public class JSONUtils {

    private static ThreadLocal<ObjectMapper> mapper = new ThreadLocal<>();

    static {
        mapper.set(new ObjectMapper());
    }

    public static String toJSONString(Object obj){
        String value = "{}";
        try {
            value = mapper.get().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static <K, V> Map<K, V> parseMap(String value){
        Map<K, V> map = null;
        try {
            map = mapper.get().readValue(value, HashMap.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static <T> T parse(String value, Class<T> clazz){
        T t = null;
        try {
            t = mapper.get().readValue(value, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return t;
    }

}
