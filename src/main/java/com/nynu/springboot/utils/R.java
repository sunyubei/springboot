package com.nynu.springboot.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 王纪勇
 * @Date: 2022/4/7 9:22
 * @Description: 返回数据接受体
 */
public class R extends HashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = 1L;

    public R setDate(Object date) {
        put("date", date);
        return this;
    }

    public <T> T getData(TypeReference<T> typeReference) {
        Object date = get("date");
        String s = JSON.toJSONString(date);
        T t = JSON.parseObject(s, typeReference);
        return t;
    }

    public <T> T getData(String key, TypeReference<T> typeReference) {
        Object data = get(key);
        String s = JSON.toJSONString(data);
        T t = JSON.parseObject(s, typeReference);
        return t;
    }

    public R() {
        put("code", 0);
        put("msg", "success");
    }

    public static R error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public Integer getCode() {
        return (Integer) this.get("code");

    }

}
