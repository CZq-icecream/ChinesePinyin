package com.czq.chinesepinyin.ui.setting.dailygoal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.czq.chinesepinyin.entity.User;
import com.czq.chinesepinyin.util.Cache;
import com.czq.chinesepinyin.util.Constant;

/**
 * @date 2020.4.9
 */
public class DailyGoalSettingViewModel extends AndroidViewModel {

    private static final String TAG = "DailyGoalSettingViewModel";

    private Cache cache;
    private LiveData<User> userLiveData;

    public DailyGoalSettingViewModel(@NonNull Application application) {
        super(application);

        cache = Cache.getInstance(application.getApplicationContext());
        userLiveData = (LiveData<User>) cache.get(Constant.getUSER());
    }

    public LiveData<User> getUserLiveData(){
        return userLiveData;
    }

}
