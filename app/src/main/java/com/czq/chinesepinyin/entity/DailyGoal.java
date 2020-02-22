package com.czq.chinesepinyin.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 表示每日目标
 * @date 2020.2.21
 * @author czq
 */
@Entity(tableName = "daily_goal")
public class DailyGoal {

    /**
     * 主键
     */
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;
    /**
     * 今日目标：通过学习新知识获得的xp
     */
    @ColumnInfo(name = "new_xp", defaultValue = "0")
    private Integer newXP;
    /**
     * 今日目标：通过复习获得的xp
     */
    @ColumnInfo(name = "review_xp", defaultValue = "0")
    private Integer reviewXP;
    /**
     * 今日目标：总的xp。totalXP = newXP + reviewXP
     */
    @ColumnInfo(name = "total_xp")
    private Integer totalXP;

    public DailyGoal(Integer newXP, Integer reviewXP) {
        this.newXP = newXP;
        this.reviewXP = reviewXP;
        this.totalXP = newXP + reviewXP;
    }

    public Integer getNewXP() {
        return newXP;
    }

    public void setNewXP(Integer newXP) {
        this.newXP = newXP;
    }

    public Integer getReviewXP() {
        return reviewXP;
    }

    public void setReviewXP(Integer reviewXP) {
        this.reviewXP = reviewXP;
    }

    public Integer getTotalXP() {
        return totalXP;
    }

    public void setTotalXP(Integer totalXP) {
        this.totalXP = totalXP;
    }
}
