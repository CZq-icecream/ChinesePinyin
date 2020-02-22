package com.czq.chinesepinyin.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

/**
 * 代表用户信息
 * @date 2020.2.21
 * @author czq
 */
@Entity
public class User {

    /**
     * 主键
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;
    /**
     * 用户名
     */
    @ColumnInfo(name = "username")
    private String username;
    /**
     * 密码
     */
    @ColumnInfo(name = "password")
    private String password;
    /**
     * 已学习的天数
     */
    @ColumnInfo(name = "learning_days")
    private Integer learningDays;
    /**
     * 每日目标xp
     */
    @ColumnInfo(name = "daily_goal")
    private Integer dailyGoal;
    /**
     * 每日新学习获得xp
     */
    @ColumnInfo(name = "daily_new")
    private Integer daily_new;
    /**
     * 每日复习获得xp
     */
    @ColumnInfo(name = "daily_review")
    private Integer daily_review;
    /**
     * 用户当前的XP
     */
    @ColumnInfo(name = "total_xp")
    private Integer totalXP;
    /**
     * 用户学习的课程id
     */
    @ColumnInfo(name = "history_lesson")
    private List<Integer> historyLesson;
    /**
     * 用户当前学习的课程id
     */
    @ColumnInfo(name = "current_lesson")
    private Integer currentLesson;


}
