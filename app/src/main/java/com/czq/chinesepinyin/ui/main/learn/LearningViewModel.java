package com.czq.chinesepinyin.ui.main.learn;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.czq.chinesepinyin.repository.UserRepository;

/**
 * @date 2020.2.21
 * @author czq
 */
public class LearningViewModel extends AndroidViewModel {

    private static final String TAG = "LearningViewModel";

    private LiveData<Integer> learningDay;
    private LiveData<Integer> newXP;
    private LiveData<Integer> reviewXP;
    private LiveData<Integer> gainToday;

    public LearningViewModel(@NonNull Application application) {
        super(application);
        UserRepository userRepository = new UserRepository(application);
        learningDay = userRepository.selectLearningDay();
        newXP = userRepository.selectNewXP();
        Log.d(TAG, userRepository.selectNewXP().getValue() + "");
        reviewXP = userRepository.selectReviewXP();
        gainToday = userRepository.selectGainToday();
    }

    public LiveData<Integer> getLearningDay(){
        return learningDay;
    }
    public LiveData<Integer> getNewXP(){
        return newXP;
    }
    public LiveData<Integer> getReviewXP(){
        return reviewXP;
    }
    public LiveData<Integer> getGainToday(){
        return gainToday;
    }
}
