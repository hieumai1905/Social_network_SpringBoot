package com.example.socialnetwork.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class ConvertUtils {

    public static <T> T convert(Object source, Class<T> dstClass) {
        if (source == null)
            return null;
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(source, dstClass);
    }

//    public static <T> T jsonToObject(String jsonValue, Class<T> dstClass) {
//        if (jsonValue == null || jsonValue.trim().isEmpty())
//            return null;
//        try {
//            return gson.fromJson(jsonValue, dstClass);
//        } catch (Exception e) {
//            throw new JsonParseException(ErrorMessage.INVALID_JSON_PARSE, null, e);
//        }
//    }

    public static <T> List<T> convertList(List<?> sourceList, Class<T> dstClass) {
        if (sourceList == null) {
            return null;
        }

        List<T> outList = new ArrayList<>();
        for (Object object : sourceList) {
            outList.add(convert(object, dstClass));
        }

        return outList;
    }

//    public static <T> List<T> convertList(String jsonString, Class<T> dstClass) {
//        List<T> sourceList = new Gson().fromJson(jsonString, new TypeToken<T>() {
//        }.getType());
//
//        List<T> outList = new ArrayList<>();
//        for (Object object : sourceList) {
//            outList.add(convert(object, dstClass));
//        }
//
//        return outList;
//    }
}
