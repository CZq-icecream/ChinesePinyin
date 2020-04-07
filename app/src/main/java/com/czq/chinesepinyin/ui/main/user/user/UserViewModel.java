package com.czq.chinesepinyin.ui.main.user.user;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.czq.chinesepinyin.entity.User;
import com.czq.chinesepinyin.util.AuthenticationState;
import com.czq.chinesepinyin.util.Cache;
import com.czq.chinesepinyin.util.Constant;

/**
 * @date 2020.3.21
 * @author czq
 */
public class UserViewModel extends AndroidViewModel {

    private Cache cache;
    private MutableLiveData<AuthenticationState> authenticationStateMutableLiveData;
    private MutableLiveData<User> userMutableLiveData;

    public UserViewModel(@NonNull Application application) {
        super(application);

        //初始化缓存
        cache = Cache.getInstance(application.getApplicationContext());
        authenticationStateMutableLiveData = (MutableLiveData<AuthenticationState>) cache.get(Constant.getAuthenticationStateLiveData());
        userMutableLiveData = (MutableLiveData<User>) cache.get(Constant.getUSER());
    }

    public LiveData<AuthenticationState> getAuthenticationStateLiveData(){
        return authenticationStateMutableLiveData;
    }

    public LiveData<User> getUserLiveData(){
        return userMutableLiveData;
    }
}