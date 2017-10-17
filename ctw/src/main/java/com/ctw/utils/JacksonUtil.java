package com.ctw.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * ctw
 *
 * @Author: HaiAng
 * @Time： 2016/5/28.1:01
 * @Vistion：1.0
 * @Remark： Json转换
 */
public class JacksonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();
    static {
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 将json字符串转换为指定类型的java对象
     *
     * @param jsonString
     * @param clazz
     * @return
     * @throws IOException
     */
    public static <T> T fromJson(String jsonString, Class<T> clazz) throws IOException {
        return mapper.readValue(jsonString, clazz);
    }

    /**
     * 将json字符串转换为HashMap(json里的子对象也将转换为Map)
     *
     * @param jsonString
     * @return
     * @throws IOException
     */
    public static Map<String, Object> fromJson(String jsonString) throws IOException {
        return mapper.readValue(jsonString, new TypeReference<HashMap<String, Object>>() {
        });
    }

    /**
     * 将java对象转换为json字符串
     *
     * @param pojo
     * @return
     * @throws JsonProcessingException
     */
    public static String toJson(Object pojo) throws JsonProcessingException {
        return mapper.writeValueAsString(pojo);
    }

    /**
     * 将java对象转换为map对象
     * @param pojo
     * @return
     * @throws IOException
     */
    public static Map<String, Object> pojo2map(Object pojo) throws IOException {
        return fromJson(toJson(pojo));
    }
    /**
     * map对象转成json字符串
     *
     * @param object map对象
     * @return map的json字符串
     */
    public static String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
