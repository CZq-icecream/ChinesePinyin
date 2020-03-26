package com.czq.chinesepinyin.ui.main.user.user;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.czq.chinesepinyin.util.AuthenticationState;
import com.czq.chinesepinyin.util.Cache;

/**
 * @date 2020.3.21
 * @author czq
 */
public class UserViewModel extends AndroidViewModel {

    private Cache cache;
    private MutableLiveData<AuthenticationState> authenticationStateMutableLiveData;

    public UserViewModel(@NonNull Application application) {
        super(application);

        //1.从缓存中获取用户状态
        //2.设置到LiveData中
    }

    public LiveData<AuthenticationState> getAuthenticationStateLiveData(){
        return authenticationStateMutableLiveData;
    }
}