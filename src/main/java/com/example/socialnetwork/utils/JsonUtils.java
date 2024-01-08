package com.example.socialnetwork.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

public class JsonUtils {

    private static JsonUtils singleton;
    private ObjectMapper om;

    private JsonUtils() {
        om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public static JsonUtils getInstance() {
        if (singleton == null) {
            singleton = new JsonUtils();
        }
        return singleton;
    }

    public static ObjectMapper getOm() {
        return getInstance().getObjectMapper();
    }

    public static String writeToStringWithoutException(Object obj) {
        try {
            return getInstance().getObjectMapper().writeValueAsString(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public static byte[] writeToByteWithoutException(Object obj) {
        try {
            return getInstance().getObjectMapper().writeValueAsBytes(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ObjectMapper getObjectMapper() {
        return om;
    }

}