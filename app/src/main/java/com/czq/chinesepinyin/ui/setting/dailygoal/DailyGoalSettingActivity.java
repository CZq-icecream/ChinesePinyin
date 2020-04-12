package com.czq.chinesepinyin.ui.setting.dailygoal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.czq.chinesepinyin.R;
import com.czq.chinesepinyin.entity.User;

/**
 * @date 2020.4.7
 */
public class DailyGoalSettingActivity extends AppCompatActivity {

    private static final String TAG = "DailyGoalSettingActivity";

    private DailyGoalSettingViewModel dailyGoalSettingViewModel;
    private RadioGroup radioGroup;
    private RadioButton casualRadioButton;
    private RadioButton regularRadioButton;
    private RadioButton seriousRadioButton;
    private RadioButton insaneRadioButton;
    private TextView saveTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_goal_setting);

        dailyGoalSettingViewModel = ViewModelProviders.of(this).get(DailyGoalSettingViewModel.class);
        initView();
        subscribe();
    }

    private void initView(){
        radioGroup = findViewById(R.id.radio_group);
        casualRadioButton = findViewById(R.id.casual);
        regularRadioButton = findViewById(R.id.regular);
        seriousRadioButton = findViewById(R.id.serious);
        insaneRadioButton = findViewById(R.id.insane);
        saveTextView = findViewById(R.id.save);
    }

    private void subscribe() {
        dailyGoalSettingViewModel.getUserLiveData().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                switch (user.getDailyGoal()) {
                    case Casual:
                        casualRadioButton.setChecked(true);
                        break;
                    case Regular:
                        regularRadioButton.setChecked(true);
                        break;
                    case Serious:
                        seriousRadioButton.setChecked(true);
                        break;
                    case Insane:
                        insaneRadioButton.setChecked(true);
                        break;
                }
            }
        });

        //设置每日目标
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.casual:

                    case R.id.regular:
                    case R.id.serious:
                    case R.id.insane:
                }
            }
        });

        //保存修改
        saveTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (casualRadioButton.isChecked()) {

                } else if (regularRadioButton.isChecked()) {

                } else if (seriousRadioButton.isChecked()) {

                } else if (insaneRadioButton.isChecked()) {

                }
            }
        });
    }
}
