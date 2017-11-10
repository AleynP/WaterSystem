package com.souhou.watersystem.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.TypeUtils;

import java.util.List;


/**
 * Created by Administrator on 2017/7/14.
 */

public class JsonMananger {
    static {
        TypeUtils.compatibleWithJavaBean = true;
    }

    /**
     * 将json字符串转换成java对象
     *
     * @param json
     * @param cls
     * @return
     * @throws Exception
     */
    public static <T> T jsonToBean(String json, Class<T> cls) {
        return JSON.parseObject(json, cls);
    }

    /**
     * 将json字符串转换成java List对象
     *
     * @param json
     * @param cls
     * @return
     * @throws Exception
     */
    public static <T> List<T> jsonToList(String json, Class<T> cls) {
        return JSON.parseArray(json, cls);
    }

    /**
     * 将bean对象转化成json字符串
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static String beanToJson(Object obj) {
        String result = JSON.toJSONString(obj);
        return result;
    }


}