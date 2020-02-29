package com.czq.chinesepinyin.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

/**
 * 代表用户信息
 * @date 2020.2.21
 * @author czq
 */
@Entity(tableName = "user")
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
    @ColumnInfo(name = "learning_days", defaultValue = "0")
    private Integer learningDays;
    /**
     * 每日目标xp
     */
    @ColumnInfo(name = "daily_goal", defaultValue = "15")
    private Integer dailyGoal;
    /**
     * 每日新学习获得xp
     */
    @ColumnInfo(name = "daily_new", defaultValue = "5")
    private Integer dailyNew;
    /**
     * 每日复习获得xp
     */
    @ColumnInfo(name = "daily_review", defaultValue = "10")
    private Integer dailyReview;
    /**
     * 今日获得xp
     */
    @ColumnInfo(name = "gain_today", defaultValue = "15")
    private Integer gainToday;
    /**
     * 用户当前的XP
     */
    @ColumnInfo(name = "total_xp", defaultValue = "0")
    private Integer totalXP;
    /**
     * 用户当前学习的课程id
     */
    @ColumnInfo(name = "current_lesson_id", defaultValue = "1")
    private Integer currentLessonId;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", learningDays=" + learningDays +
                ", dailyGoal=" + dailyGoal +
                ", dailyNew=" + dailyNew +
                ", dailyReview=" + dailyReview +
                ", gainToday=" + gainToday +
                ", totalXP=" + totalXP +
                ", currentLessonId=" + currentLessonId +
                '}';
    }

    public User(String username, String password, Integer learningDays, Integer dailyGoal, Integer dailyNew, Integer dailyReview, Integer gainToday, Integer totalXP, Integer currentLessonId) {
        this.username = username;
        this.password = password;
        this.learningDays = learningDays;
        this.dailyGoal = dailyGoal;
        this.dailyNew = dailyNew;
        this.dailyReview = dailyReview;
        this.gainToday = gainToday;
        this.totalXP = totalXP;
        this.currentLessonId = currentLessonId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getLearningDays() {
        return learningDays;
    }

    public void setLearningDays(Integer learningDays) {
        this.learningDays = learningDays;
    }

    public Integer getDailyGoal() {
        return dailyGoal;
    }

    public void setDailyGoal(Integer dailyGoal) {
        this.dailyGoal = dailyGoal;
    }

    public Integer getDailyNew() {
        return dailyNew;
    }

    public void setDailyNew(Integer dailyNew) {
        this.dailyNew = dailyNew;
    }

    public Integer getDailyReview() {
        return dailyReview;
    }

    public void setDailyReview(Integer dailyReview) {
        this.dailyReview = dailyReview;
    }

    public Integer getGainToday() {
        return gainToday;
    }

    public void setGainToday(Integer gainToday) {
        this.gainToday = gainToday;
    }

    public Integer getTotalXP() {
        return totalXP;
    }

    public void setTotalXP(Integer totalXP) {
        this.totalXP = totalXP;
    }

    public Integer getCurrentLessonId() {
        return currentLessonId;
    }

    public void setCurrentLessonId(Integer currentLessonId) {
        this.currentLessonId = currentLessonId;
    }
}
