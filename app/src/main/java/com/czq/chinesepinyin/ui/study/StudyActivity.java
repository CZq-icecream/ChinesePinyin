package com.czq.chinesepinyin.ui.study;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.czq.chinesepinyin.BaseActivity;
import com.czq.chinesepinyin.R;

/**
 * 日常学习页面
 * @date 2019.2.17
 * @author czq
 */
public class StudyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_study);
        //获取NavController
        NavController controller = Navigation.findNavController(this, R.id.nav_host_fragment);
    }
}