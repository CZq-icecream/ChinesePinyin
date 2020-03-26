package com.czq.chinesepinyin.ui.login_register.login;

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


/**
 * @date 2020.3.21
 * @author czq
 */
public class LoginFragment extends Fragment {

    private NavController controller;

    private Button loginButton;
    private Button signInButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        controller = NavHostFragment.findNavController(this);

        loginButton = view.findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
}
