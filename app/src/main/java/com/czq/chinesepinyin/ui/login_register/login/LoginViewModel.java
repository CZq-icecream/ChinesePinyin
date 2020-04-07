package com.czq.chinesepinyin.ui.login_register.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.czq.chinesepinyin.repository.UserRepository;
import com.czq.chinesepinyin.util.AuthenticationState;
import com.czq.chinesepinyin.util.Cache;
import com.czq.chinesepinyin.util.Constant;

/**
 * @date 2020.3.21
 * @author czq
 */
public class LoginViewModel extends AndroidViewModel {

    private static final String TAG = "LoginViewModel";

    private UserRepository userRepository;
    private Cache cache;
    private LiveData<AuthenticationState> authenticationStateLiveData;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        cache = Cache.getInstance(application.getApplicationContext());
        authenticationStateLiveData = (LiveData<AuthenticationState>) cache.get(Constant.getAuthenticationStateLiveData());
    }

    public void login(String username, String password) {
        userRepository.login(username, password);
    }

    public LiveData<AuthenticationState> getAuthenticationStateLiveData(){
        return authenticationStateLiveData;
    }
}
