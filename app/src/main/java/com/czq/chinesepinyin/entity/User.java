package com.czq.chinesepinyin.entity;

import androidx.annotation.NonNull;
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
    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    private String uuid;
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
    /**
     * 用户当前学习的课程进度
     */
    @ColumnInfo(name = "current_lesson_progress", defaultValue = "1")
    private Integer currentLessonProgress;
    /**
     * 用户头像路径
     */
    @ColumnInfo(name = "profile_path", defaultValue = "")
    private String profilePath;

    @Override
    public String toString() {
        return "User{" +
                "uuid='" + uuid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", learningDays=" + learningDays +
                ", dailyGoal=" + dailyGoal +
                ", gainToday=" + gainToday +
                ", totalXP=" + totalXP +
                ", currentLessonId=" + currentLessonId +
                ", currentLessonProgress=" + currentLessonProgress +
                ", profilePath='" + profilePath + '\'' +
                '}';
    }

    public User(@NonNull String uuid, String username, String password, Integer learningDays,
                Integer dailyGoal, Integer gainToday, Integer totalXP, Integer currentLessonId,
                Integer currentLessonProgress, String profilePath) {
        this.uuid = uuid;
        this.username = username;
        this.password = password;
        this.learningDays = learningDays;
        this.dailyGoal = dailyGoal;
        this.gainToday = gainToday;
        this.totalXP = totalXP;
        this.currentLessonId = currentLessonId;
        this.currentLessonProgress = currentLessonProgress;
        this.profilePath = profilePath;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public Integer getCurrentLessonProgress() {
        return currentLessonProgress;
    }

    public void setCurrentLessonProgress(Integer currentLessonProgress) {
        this.currentLessonProgress = currentLessonProgress;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
}
