package com.czq.chinesepinyin.ui.main.learn;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.czq.chinesepinyin.entity.User;
import com.czq.chinesepinyin.repository.UserRepository;

/**
 * @date 2020.2.21
 * @author czq
 */
public class LearningViewModel extends AndroidViewModel {

    private static final String TAG = "LearningViewModel";

    private LiveData<User> userLiveData;

    public LiveData<Integer> liveData = new MutableLiveData<>(1);

    public LearningViewModel(@NonNull Application application) {
        super(application);
        UserRepository userRepository = new UserRepository(application);
        userLiveData = userRepository.selectUser();
    }

    public LiveData<User> getUserLiveData(){
        return userLiveData;
    }

}
