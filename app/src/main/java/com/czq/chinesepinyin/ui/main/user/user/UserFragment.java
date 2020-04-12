package com.czq.chinesepinyin.ui.main.user.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.czq.chinesepinyin.R;
import com.czq.chinesepinyin.entity.User;
import com.czq.chinesepinyin.ui.setting.dailygoal.DailyGoalSettingActivity;
import com.czq.chinesepinyin.ui.setting.reminder.ReminderSettingActivity;

/**
 * 位于主页的UserFragment
 * @date 2020.2.19
 * @author czq
 */
public class UserFragment extends Fragment {

    private static final String TAG = "UserFragment";

    private UserViewModel userViewModel;

    private ImageView userProfileImageView;
    private TextView userLearningDaysTextView;
    private TextView userTotalXP;
    private LinearLayout userDailyGoalSetting;
    private LinearLayout userLessonSetting;
    private LinearLayout userReminderSetting;
    private LinearLayout userHistoryLessonSetting;
    private LinearLayout userPasswordSetting;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        view.post(new Runnable() {
            @Override
            public void run() {
                initView(view);
                subscribe();
            }
        });

        return view;
    }

    private void initView(View view) {
        userProfileImageView = view.findViewById(R.id.user_profile);
        userLearningDaysTextView = view.findViewById(R.id.user_learning_days);
        userTotalXP = view.findViewById(R.id.user_total_xp);

        userDailyGoalSetting = view.findViewById(R.id.daily_goal_setting);
        userDailyGoalSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DailyGoalSettingActivity.class);
                startActivity(intent);
            }
        });

        userLessonSetting = view.findViewById(R.id.lesson_setting);
        userLessonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        userReminderSetting = view.findViewById(R.id.reminder_setting);
        userReminderSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReminderSettingActivity.class);
                startActivity(intent);
            }
        });

        userHistoryLessonSetting = view.findViewById(R.id.history_lesson);
        userHistoryLessonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        userPasswordSetting = view.findViewById(R.id.password_change);
        userPasswordSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void subscribe(){
        userViewModel.getUserLiveData().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
//                Glide.with(UserFragment.this).load("").into(userProfileImageView);
                userLearningDaysTextView.setText(getResources().getString(R.string.user_fragment_days_learning, user.getLearningDays()));
                userTotalXP.setText(getResources().getString(R.string.user_fragment_total_xp, user.getTotalXP()));
            }
        });
    }
}
