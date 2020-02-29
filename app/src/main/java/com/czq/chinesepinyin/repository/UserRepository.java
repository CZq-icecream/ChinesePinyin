package com.czq.chinesepinyin.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.czq.chinesepinyin.dao.UserDao;
import com.czq.chinesepinyin.database.UserDatabase;
import com.czq.chinesepinyin.entity.User;

/**
 * 提供访问API的repository
 * @date 2020.2.23
 * @author czq
 */
public class UserRepository {

    private static final String TAG = "UserRepository";

    private UserDao userDao;

    public UserRepository(Application application) {
        UserDatabase userDatabase = UserDatabase.getUserDatabase(application);
        userDao = userDatabase.userDao();
    }

    public LiveData<Integer> selectLearningDay(){
        return userDao.selectLearningDay();
    }

    public LiveData<Integer> selectNewXP(){
        return userDao.selectNewXP();
    }

    public LiveData<Integer> selectReviewXP(){
        return userDao.selectReviewXP();
    }

    public LiveData<Integer> selectGainToday(){
        return userDao.selectGainToday();
    }

    public User selectUser(){
        return userDao.selectUser();
    }
}
