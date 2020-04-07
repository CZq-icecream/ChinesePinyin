package com.czq.chinesepinyin.util;

import android.content.Context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 暂时作为缓存类，可以存储一些答题信息，例如当前的课程号，课程进度数值，获得的XP数值
 * 应该作为一个单例类
 * TODO 后面应该在splash界面初始化Cache
 * @date 2020.3.18
 * @author czq
 */
public class Cache {

    private volatile static Cache instance = null;

    private static Map<String, Object> cache;

    private Cache(Context context){
        cache = new ConcurrentHashMap<>();
    }

    public static Cache getInstance(Context context){
        if (instance == null) {
            synchronized (Cache.class) {
                if (instance == null) {
                    instance = new Cache(context);
                }
            }
        }
        return instance;
    }

    public void put(String key, Object value) {
        cache.put(key, value);
    }

    public Object get(String key) {
        return cache.get(key);
    }
}
