package com.javaclimb.util;

import java.util.HashMap;

public class ReturnDataForLayui extends HashMap<String, Object> {
    public static final String KEY_CODE = "code";
    public static final String KEY_MSG = "msg";
    public static final String KEY_COUNT = "count";
    public static final String KEY_DATA = "data";

    /**
     * 失败
     */
    public static final Integer KEY_CODE_ERROR = -1;
    /**
     * 成功
     */
    public static final Integer KEY_CODE_SUCCESS = 0;
    /**
     * count 默认0
     */
    public static final Integer KEY_COUNT_SIZE = 0;


    public ReturnDataForLayui() {
        put(KEY_CODE, KEY_CODE_SUCCESS);
        put(KEY_COUNT, KEY_COUNT_SIZE);
        put(KEY_MSG, "请求成功");
    }

    public static ReturnDataForLayui success() {
        return new ReturnDataForLayui();
    }


    public static ReturnDataForLayui success(Object data, long count) {
        ReturnDataForLayui returnData = new ReturnDataForLayui();
        returnData.put(KEY_DATA, data);
        returnData.put(KEY_COUNT, count);
        return returnData;
    }

    public static ReturnDataForLayui fail() {
        ReturnDataForLayui returnData = new ReturnDataForLayui();
        returnData.put(KEY_CODE, KEY_CODE_ERROR);
        returnData.put(KEY_MSG, "请求失败");
        return returnData;
    }


    public static ReturnDataForLayui fail(String msg) {
        ReturnDataForLayui returnData = new ReturnDataForLayui();
        returnData.put(KEY_CODE, KEY_CODE_ERROR);
        returnData.put(KEY_MSG, msg);
        return returnData;
    }


    public int getCode() {
        return (int) get(KEY_CODE);
    }

    public Object getData() {
        return get(KEY_DATA);
    }

    public String getMsg() {
        return (String) get(KEY_MSG);
    }

    @Override
    public Object put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
