package com.careforeyou.inbody.bean.json;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by Administrator on 2016/8/3.
 */
public class JsonMapper {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    }

    private JsonMapper() {
    }

    public static ObjectMapper getInstance() {
        return mapper;
    }

    public static String toJson(Object data) {
        StringWriter writer = new StringWriter();
        try {
            mapper.writeValue(writer, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        LogUtil.i("JsonMapper", "writer:" + writer);
        return writer.toString();
    }

    public static <T> T fromJson(String json, Class<T> tClass) {
//        LogUtil.i("JsonMapper", "json:" + json);
        T t = null;
        try {
            t = mapper.readValue(json, tClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static <T> T fromJson(Object json, Class<T> tClass) {
//        LogUtil.i("JsonMapper", "Object:" + json);
        return fromJson(toJson(json), tClass);
    }

    public static <T> T fromJson(String json, TypeReference valueTypeRef) {
//        LogUtil.i("JsonMapper", "json:" + json);
        T t = null;
        try {
            t = mapper.readValue(json, valueTypeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static <T> T fromJson(Object json, TypeReference valueTypeRef) {
//        LogUtil.i("JsonMapper", "Object:" + json);
        T t = null;
        try {
            t = mapper.readValue(toJson(json), valueTypeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }




}
