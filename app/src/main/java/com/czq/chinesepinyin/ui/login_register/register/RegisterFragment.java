package com.czq.chinesepinyin.ui.login_register.register;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.czq.chinesepinyin.R;
import com.google.android.material.textfield.TextInputEditText;

/**
 * @date 2020.3.21
 * @author czq
 */
public class RegisterFragment extends Fragment {

    private NavController controller;

    private TextInputEditText usernameEditText;
    private TextInputEditText passwordEditText;
    private TextInputEditText passwordConfirmEditText;
    private Button registerButton;
    private Button loginButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        controller = NavHostFragment.findNavController(this);

        usernameEditText = view.findViewById(R.id.username);
        passwordEditText = view.findViewById(R.id.password);
        passwordConfirmEditText = view.findViewById(R.id.password_confirm);

        registerButton = view.findViewById(R.id.register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //用户名不为空
                if (usernameEditText.getText() == null || usernameEditText.getText().toString().trim().equals("")) {
                    usernameEditText.setError(getResources().getString(R.string.register_fragment_username_not_null));
                    usernameEditText.requestFocus();
                    return;
                }
                //密码不为空
                if (passwordEditText.getText() == null) {
                    passwordEditText.setError(getResources().getString(R.string.register_fragment_password_not_null));
                    passwordEditText.requestFocus();
                    return;
                }
                //密码不包含空格
                if (passwordEditText.getText().toString().contains(" ")) {
                    passwordEditText.setError(getResources().getString(R.string.register_fragment_password_no_space));
                    passwordEditText.requestFocus();
                    return;
                }
                //密码长度([6,12]）
                if (passwordEditText.getText().toString().length() < 6 || passwordEditText.getText().toString().length() > 12) {
                    passwordEditText.setError(getResources().getString(R.string.register_fragment_password_length));
                    passwordEditText.requestFocus();
                    return;
                }
                //第二次密码不为空
                if (passwordConfirmEditText.getText() == null) {
                    passwordConfirmEditText.setError(getResources().getString(R.string.register_fragment_password_is_incorrect));
                    passwordConfirmEditText.requestFocus();
                    return;
                }
                //两次输入的密码要相等
                if (!passwordEditText.getText().toString().equals(passwordConfirmEditText.getText().toString())) {
                    passwordConfirmEditText.setError(getResources().getString(R.string.register_fragment_password_is_incorrect));
                    passwordConfirmEditText.requestFocus();
                    return;
                }
                //提交表单请求
            }
        });

        loginButton = view.findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.navigation_login_fragment);
            }
        });
    }
}