package com.czq.chinesepinyin.ui.main.learn;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.czq.chinesepinyin.R;
import com.czq.chinesepinyin.dao.UserDao;
import com.czq.chinesepinyin.database.UserDatabase;
import com.czq.chinesepinyin.entity.User;
import com.czq.chinesepinyin.ui.study.StudyActivity;

/**
 * 位于主页的LearningFragment
 * @date 2020.2.19
 * @author czq
 */
public class LearningFragment extends Fragment {

    private static final String TAG = "LearningFragment";

    private TextView learningDay;
    private TextView textProgress;
    private ProgressBar progressBar;
    private Button buttonPractice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_learn, container, false);
        //初始化视图上的控件
        initView(view);

        LearningViewModel learningViewModel = ViewModelProviders.of(this).get(LearningViewModel.class);
        subscribe(learningViewModel, view);

        return view;
    }

    /**
     * 初始化视图上的控件
     * @param view
     */
    private void initView(View view) {
        learningDay = view.findViewById(R.id.learning_day);
        textProgress = view.findViewById(R.id.text_progress);
        progressBar = view.findViewById(R.id.progress);
        //设置进度条
        setProgressBar();

        buttonPractice = view.findViewById(R.id.practice_button);
        buttonPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), StudyActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 设置进度条
     */
    private void setProgressBar(){
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        progressBar.getLayoutParams().width = size.x / 2;
        progressBar.invalidate();;
    }


    /**
     * 更新UI视图s
     * @param learningViewModel
     * @param view
     */
    private void subscribe(LearningViewModel learningViewModel, View view){
        learningViewModel.getUserLiveData().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null) {
                    learningDay.setText(getResources().getString(R.string.learning_fragment_learning_day, user.getLearningDays()));
                    textProgress.setText(getResources().getString(R.string.learning_fragment_progress, user.getGainToday(), user.getDailyGoal().getXP()));
                    progressBar.setMax(user.getDailyGoal().getXP());
                    progressBar.setProgress(user.getGainToday());
                }
            }
        });
    }
}
