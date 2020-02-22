package com.czq.chinesepinyin.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.czq.chinesepinyin.entity.DailyGoal;

/**
 * 获取每日目标
 * @date 2020.2.21
 * @author czq
 */
@Dao
public interface DailyGoalDao {

    @Query(value = "SELECT * FROM daily_goal WHERE id = :id")
    DailyGoal getDailyGoal(Integer id);

    @Query("UPDATE daily_goal SET newXP = :newXp AND totalXP = :newXp + reviewXP WHERE id = :id")
    void setNewXp(Integer id, Integer newXp);

    @Query("UPDATE daily_goal SET reviewXP = :reviewXp AND totalXP = newXP + :reviewXp WHERE id = :id")
    void setReviewXp(Integer id, Integer reviewXp);

    @Insert(entity = DailyGoal.class)
    void insertDailyGoal(DailyGoal dailyGoal);
}
