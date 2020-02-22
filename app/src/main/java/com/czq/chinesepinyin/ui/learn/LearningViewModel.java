package com.czq.chinesepinyin.ui.learn;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.czq.chinesepinyin.ConstantForTest;
import com.czq.chinesepinyin.entity.DailyGoal;
import com.czq.chinesepinyin.repository.DailyGoalRepository;

/**
 * @date 2020.2.21
 * @author czq
 */
public class LearningViewModel extends AndroidViewModel {

    private MutableLiveData<DailyGoal> dailyGoalMutableLiveData = new MutableLiveData<>();
    private DailyGoalRepository dailyGoalRepository;

    /**
     * @param application
     * @param id
     */
    public LearningViewModel(Application application, Integer id){
        super(application);
        dailyGoalRepository = new DailyGoalRepository(application);
        dailyGoalRepository.selectDailyGoal(id, dailyGoalMutableLiveData);
    }

    public MutableLiveData<DailyGoal> getDailyGoalMutableLiveData(){
        return dailyGoalMutableLiveData;
    }
}
