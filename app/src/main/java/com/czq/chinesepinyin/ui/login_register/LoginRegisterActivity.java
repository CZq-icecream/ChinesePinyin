package com.czq.chinesepinyin.ui.login_register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.czq.chinesepinyin.R;
import com.czq.chinesepinyin.ui.main.user.ProfileFragment;

public class LoginRegisterActivity extends AppCompatActivity {

    private static final String TAG = "LoginRegisterActivity";

    private static final String LOGIN = "splashLogin";
    private static final String SIGN_IN = "sign_in";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        NavController controller = Navigation.findNavController(this, R.id.nav_host_fragment);
        if (getIntent().hasExtra(LOGIN)) {
            controller.navigate(R.id.navigation_login_fragment);
        } else if (getIntent().hasExtra(SIGN_IN)) {
            controller.navigate(R.id.navigation_register_fragment);
        }
    }

    public static Intent newLoginIntent(Context context){
        Intent intent = new Intent(context, LoginRegisterActivity.class);
        intent.putExtra(LOGIN, 1);
        return intent;
    }

    public static Intent newSignInIntent(Context context) {
        Intent intent = new Intent(context, LoginRegisterActivity.class);
        intent.putExtra(SIGN_IN, 1);
        return intent;
    }
}
