package com.czq.chinesepinyin.util;

import com.czq.chinesepinyin.entity.User;

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

    /**
     * baseUrl
     */
    private static final String BASE_URL = "http://192.168.43.42:8080";

    /**
     * 缓存的key
     * USER                             : MutableLiveData<User>
     * AUTHENTICATION_STATE             : AuthenticationState
     * AUTHENTICATION_STATE_LIVE_DATA   : MutableLiveData<AuthenticationState>
     * CURRENT_LESSON_ID                : Integer
     * CURRENT_LESSON_PROGRESS          : Integer
     * GAIN_TODAY                       : Integer
     * DAILY_GOAL                       : Integer
     */
    private static final String USER = "USER";
    private static final String AUTHENTICATION_STATE = "AUTHENTICATION_STATE";
    private static final String AUTHENTICATION_STATE_LIVE_DATA = "AUTHENTICATION_STATE_LIVE_DATA";
    private static final String CURRENT_LESSON_ID = "CURRENT_LESSON_ID";
    private static final String CURRENT_LESSON_PROGRESS = "CURRENT_LESSON_PROGRESS";
    private static final String GAIN_TODAY = "GAIN_TODAY";
    private static final String DAILY_GOAL = "DAILY_GOAL";

    /**
     * 发送请求@Body的key
     */
    private static final String UUID = "uuid";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    /**
     * 未登录用户的临时账号
     */
    private static final User user = new User("", "", "", 0,
            0, 0, 0, 1, 1,"");

    public static String getBaseUrl(){
        return BASE_URL;
    }

    public static String getUSER(){
        return USER;
    }

    public static String getAuthenticationState(){
        return AUTHENTICATION_STATE;
    }

    public static String getAuthenticationStateLiveData() {
        return AUTHENTICATION_STATE_LIVE_DATA;
    }

    public static String getUUID() {
        return UUID;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static User getUser(){
        return user;
    }

    public static String getCurrentLessonId() {
        return CURRENT_LESSON_ID;
    }

    public static String getCurrentLessonProgress() {
        return CURRENT_LESSON_PROGRESS;
    }

    public static String getGainToday() {
        return GAIN_TODAY;
    }

    public static String getDailyGoal() {
        return DAILY_GOAL;
    }
}
