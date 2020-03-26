package com.czq.chinesepinyin.util;

/**
 * 常量类
 * @date 2020.3.18
 * @author czq
 */
public class Constant {

    /**
     * 禁止被实例化
     */
    private Constant(){}

    private static final String BASE_URL = "http://192.168.43.42:8080";

    /**
     * 缓存的key
     * USER: MutableLiveData
     * AUTHENTICATION_STATE: MutableLiveData
     */
    private static final String USER = "USER";
    private static final String AUTHENTICATION_STATE = "AUTHENTICATION_STATE";


    public static String getBaseUrl(){
        return BASE_URL;
    }


}
