package com.czq.chinesepinyin.ui.main.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.czq.chinesepinyin.R;
import com.czq.chinesepinyin.repository.UserRepository;
import com.czq.chinesepinyin.ui.login_register.LoginRegisterActivity;
import com.czq.chinesepinyin.ui.main.user.user.UserViewModel;
import com.czq.chinesepinyin.util.AuthenticationState;
import com.czq.chinesepinyin.util.Cache;
import com.czq.chinesepinyin.util.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";

    private Cache cache;
    private NavController controller;
    private UserViewModel userViewModel;
    private Button loginButton;
    private Button signInButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        cache = Cache.getInstance(getContext());
        controller = NavHostFragment.findNavController(this);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        initView(view);
        subscribe();
        return view;
    }

    private void initView(View view){
        loginButton = view.findViewById(R.id.login);
        signInButton = view.findViewById(R.id.sign_in);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = LoginRegisterActivity.newLoginIntent(getActivity());
                startActivity(intent);
            }
        });
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = LoginRegisterActivity.newSignInIntent(getActivity());
                startActivity(intent);
            }
        });
    }

    private void subscribe(){
        userViewModel.getAuthenticationStateLiveData().observe(this, new Observer<AuthenticationState>() {
            @Override
            public void onChanged(AuthenticationState authenticationState) {
                //如果用户已登录，则跳转到用户界面
                if (authenticationState == AuthenticationState.AUTHENTICATED) {
                    controller.navigate(R.id.action_profileFragment_to_userFragment);
                }
            }
        });
    }
}
