package com.example.scengine.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.*;

public class JsonUtil {

    @SuppressWarnings({ "unchecked", "static-access" })
    public static Map<String, Object> jsonToMap(String str) {
        final JSONObject jsonObj = new JSONObject();
        return (Map<String, Object>) jsonObj.parseObject(str, Map.class);
    }

    @SuppressWarnings({ "static-access" })
    public static <T> T mapToEntity(Map<String, ?> map, Class<T> clazz) {
        final String json = mapToJson(map);
        final JSONObject jsonObj = new JSONObject();
        return jsonObj.parseObject(json, clazz);
    }


    public static String entityToJson(Object obj) {
        return JSON.toJSONString(obj);
    }


    public static Map<String, Object> entityToMap(Object obj) {
        final String str = JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
        return jsonToMap(str);
    }

    /**
     *@name   实体转MAP（保留值为null的数据）
     */
    @SuppressWarnings("rawtypes")
    public static Map entityToMapNotT(Object obj) {
        final String str = JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
        return jsonToMap(str);
    }

    /**
     *@name  实体转Map去掉value是null的数据
     */
    @SuppressWarnings("rawtypes")
    public static Map entityToMapNotTDelNullValue(Object obj) {
        final String str = JSON.toJSONString(obj);
        return jsonToMap(str);
    }



    @SuppressWarnings({ "static-access" })
    public static <T> T jsonToEntity(String str, Class<T> clazz) {
        final JSONObject jsonObj = new JSONObject();
        return jsonObj.parseObject(str, clazz);
    }

    /**
     * @name Json字符串转为VO对象列表
     */
    public static <T> List<T> jsonToEntityList(String str, Class<T> clazz) {
        final List<T> resList = new ArrayList<T>();
        if (!Objects.isNull(str)){
            final JSONArray jsonArray = JSONArray.parseArray(str);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                resList.add(jsonObject.toJavaObject(clazz));
            }
        }
        return resList;
    }

    /**
     * @title mapList转换为beanList
     */
    public static <T> List<T> listToEntityList(List<Map<String, Object>> list, Class<T> clazz) {
        final List<T> tList = new ArrayList<T>();
        if (!Objects.isNull(list)){
            final Iterator<Map<String, Object>> ite = list.iterator();
            while (ite.hasNext()) {
                final Map<String, Object> item = ite.next();
                tList.add((T) mapToEntity(item, clazz));
            }
        }
        return tList;
    }


    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> jsonToList(String str) {
        final List<Map<String, Object>> paramList = new ArrayList<>();
        if (!Objects.isNull(str)){
            final JSONArray jsonArray = JSONArray.parseArray(str);
            JSONObject jsonObject;
            for (int i = 0; i < jsonArray.size(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                paramList.add(jsonObject.toJavaObject(Map.class));
            }
        }
        return paramList;
    }


    public static LinkedList<String> jsonToListStr(String str) {
        final LinkedList<String> paramList = new LinkedList<>();
        if (!Objects.isNull(str)){
            final JSONArray jsonArray = JSONArray.parseArray(str);
            for (int i = 0; i < jsonArray.size(); i++) {
                final String json = jsonArray.getString(i);
                paramList.add(json);
            }
        }
        return paramList;
    }

    /**
     * map转JSON对象
     */
    public static String mapToJson(Map<String, ?> map) {
        return JSON.toJSONString(map, SerializerFeature.WriteNullStringAsEmpty);
    }


    public static String mapToJsonString(Map<String, String> map) {
        return JSON.toJSON(map).toString();
    }

    /**
     * LIST转JSON对象
     */
    public static String listToJson(List<?> list) {
        return JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteMapNullValue);
    }

    /**
     * @name 实体List转MapList
     */
    public static List<Map<String, Object>> entityListToMapList(List<?> list) {
        final List<Map<String, Object>> resList = new ArrayList<>();
        for (Object obj : list) {
            resList.add(entityToMap(obj));
        }
        return resList;
    }

    /**
     * @name 实体对象拷贝
     */
    public static <T> T entityCopy(Object obj, Class<T> clazz) {
        return jsonToEntity(entityToJson(obj), clazz);
    }

    /**
     * @name 实体列表拷贝
     */
    public static <T> List<T> entityListCopy(List<?> list, Class<T> clazz) {
        return listToEntityList(entityListToMapList(list), clazz);
    }
}
