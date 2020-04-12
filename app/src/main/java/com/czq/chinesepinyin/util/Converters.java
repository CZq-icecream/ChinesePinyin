package com.czq.chinesepinyin.util;

import androidx.room.TypeConverter;

import com.czq.chinesepinyin.entity.DailyGoal;

/**
 * @date 2020.4.10
 * https://rangaofei.github.io/2018/05/11/%E5%AE%89%E5%8D%93%E6%8C%81%E4%B9%85%E5%8C%96%E6%95%B0%E6%8D%AE%E5%AD%98%E5%82%A8%E6%A1%86%E6%9E%B6Room%E7%9A%84%E4%BD%BF%E7%94%A8/
 */
public class Converters {

    @TypeConverter
    public static Integer DailyGoalToInteger(DailyGoal dailyGoal) {
        return dailyGoal.getXP();
    }

    @TypeConverter
    public static DailyGoal IntegerToDailyGoal(Integer integer) {
        return DailyGoal.getDailyGoal(integer);
    }
}
