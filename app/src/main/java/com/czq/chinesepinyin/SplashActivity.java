package com.czq.chinesepinyin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.czq.chinesepinyin.ui.main.MainActivity;
import com.czq.chinesepinyin.util.CircleProgressbar;

/**
 * Splash页面
 * @date 2020.2.16
 * @author czq
 */
public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";

    CircleProgressbar circleProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();

        circleProgressbar = findViewById(R.id.circleProgressbar);
        circleProgressbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity();
            }
        });
        circleProgressbar.setOnProgressListener(new CircleProgressbar.OnProgressListener() {
            @Override
            public void onProgress() {
                circleProgressbar.stop();
                startActivity();
            }
        });
        circleProgressbar.start();
    }

    private void startActivity(){
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        finish();   //需要结束该页面，否则点击返回又来到该页面
        startActivity(intent);
    }
}
