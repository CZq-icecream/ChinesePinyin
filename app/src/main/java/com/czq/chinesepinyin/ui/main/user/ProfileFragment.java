package com.czq.chinesepinyin.ui.main.user;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.czq.chinesepinyin.R;
import com.czq.chinesepinyin.ui.login_register.LoginRegisterActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";

    private Button loginButton;
    private Button signInButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
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
}
