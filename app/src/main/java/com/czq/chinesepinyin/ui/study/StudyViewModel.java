package com.czq.chinesepinyin.ui.study;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.czq.chinesepinyin.entity.User;

/**
 * @date 2020.2.25
 * @author czq
 */
public class StudyViewModel extends AndroidViewModel {

    private LiveData<User> userLiveData;

    //TODO
    //1. 应该是一个StudyViewModel还是拆成两个：OptionViewModel和DetailViewModel
    //2. 每次答题完成后，如何将变化通知给LiveData
    //1. 拆成两个
    //2. 通过progress的变化再次访问数据库或者后台程序

    public StudyViewModel(@NonNull Application application) {
        super(application);
    }
}