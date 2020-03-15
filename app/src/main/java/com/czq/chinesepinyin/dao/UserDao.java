package com.czq.chinesepinyin.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.czq.chinesepinyin.entity.User;

/**
 * 查询用户的dao
 * @date 2020.2.22
 * @author czq
 */
@Dao
public interface UserDao {

    /**
     * 查找已学习的天数
     * @return
     */
    @Query("SELECT learning_days from user LIMIT 1")
    LiveData<Integer> selectLearningDay();

    /**
     * 查找今日获得的xp
     * @return
     */
    @Query("SELECT gain_today FROM user LIMIT 1")
    LiveData<Integer> selectGainToday();

    /**
     * 插入用户信息
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertUser(User user);

    /**
     * 删除所有用户信息
     */
    @Query("DELETE FROM user")
    void deleteAll();

    /**
     * 获得用户信息
     * @return
     */
    @Query("SELECT * FROM user LIMIT 1")
    LiveData<User> selectUser();
}
