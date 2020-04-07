package com.czq.chinesepinyin.ui.login_register.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.czq.chinesepinyin.R;
import com.czq.chinesepinyin.util.AuthenticationState;


/**
 * @date 2020.3.21
 * @author czq
 */
public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment";

    private NavController controller;
    private LoginViewModel loginViewModel;

    private EditText usernameEditText;
    private EditText passwordEditText;
    private TextView tapHereTextView;
    private Button loginButton;
    private Button signInButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        initView(view);
        subscribe();
        return view;
    }

    private void initView(View view) {
        controller = NavHostFragment.findNavController(this);

        usernameEditText = view.findViewById(R.id.login_username);
        passwordEditText = view.findViewById(R.id.login_password);
        tapHereTextView = view.findViewById(R.id.tap_here);

        loginButton = view.findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                Log.d(TAG, username + " " + password);
                //登录
                loginViewModel.login(username, password);
            }
        });

        signInButton = view.findViewById(R.id.sign_in);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.navigation_register_fragment);
            }
        });
    }

    private void subscribe(){
        loginViewModel.getAuthenticationStateLiveData().observe(this, new Observer<AuthenticationState>() {
            @Override
            public void onChanged(AuthenticationState authenticationState) {
                if (authenticationState == AuthenticationState.AUTHENTICATED) {
                    //登录状态时结束页面
                    getActivity().finish();
                }
            }
        });
    }
}
