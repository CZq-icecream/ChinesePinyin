package com.czq.chinesepinyin.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.czq.chinesepinyin.dao.DailyGoalDao;
import com.czq.chinesepinyin.database.DailyGoalRoomDatabase;
import com.czq.chinesepinyin.entity.DailyGoal;

/**
 * 调用dao层获取数据
 * @date 2020.2.21
 * @author czq
 */
public class DailyGoalRepository {

    private DailyGoalRoomDatabase dailyGoalRoomDatabase;
    private DailyGoalDao dailyGoalDao;

    public DailyGoalRepository(Application application) {
        dailyGoalRoomDatabase = DailyGoalRoomDatabase.getDatabase(application);
        dailyGoalDao = dailyGoalRoomDatabase.dailyGoalDao();
    }

    /**
     * 查询当前的DailyGoal
     * @param id
     * @param dailyGoalMutableLiveData
     */
    public void selectDailyGoal(Integer id, MutableLiveData<DailyGoal> dailyGoalMutableLiveData){
        dailyGoalRoomDatabase.getDatabaseWriteExecutor().execute(new Runnable() {
            @Override
            public void run() {
                DailyGoal dailyGoal = dailyGoalDao.getDailyGoal(id);
                dailyGoalMutableLiveData.setValue(dailyGoal);
            }
        });
    }

    /**
     * 更新通过新知识获得的xp
     * @param id
     * @param newXp
     */
    public void updateDailyGoalNewXp(Integer id, Integer newXp) {
        dailyGoalRoomDatabase.getDatabaseWriteExecutor().execute(new Runnable() {
            @Override
            public void run() {
                dailyGoalDao.setNewXp(id, newXp);
            }
        });
    }

    /**
     * 更新通过复习获得的xp
     * @param id
     * @param reviewXp
     */
    public void updateDailyGoalReviewXp(Integer id, Integer reviewXp) {
        dailyGoalRoomDatabase.getDatabaseWriteExecutor().execute(new Runnable() {
            @Override
            public void run() {
                dailyGoalDao.setReviewXp(id, reviewXp);
            }
        });
    }

    /**
     * 插入每日目标
     * @param dailyGoal
     */
    public void insertDailyGoal(DailyGoal dailyGoal) {
        dailyGoalRoomDatabase.getDatabaseWriteExecutor().execute(new Runnable() {
            @Override
            public void run() {
                dailyGoalDao.insertDailyGoal(dailyGoal);
            }
        });
    }
}
