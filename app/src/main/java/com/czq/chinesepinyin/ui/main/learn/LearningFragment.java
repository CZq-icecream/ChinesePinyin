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
import com.czq.chinesepinyin.ui.study.StudyActivity;

/**
 * 位于主页的LearningFragment
 * @date 2020.2.19
 * @author czq
 */
public class LearningFragment extends Fragment {

    private static final String TAG = "LearningFragment";

    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_learn, container, false);

        LearningViewModel learningViewModel = ViewModelProviders.of(this).get(LearningViewModel.class);
        subscribe(learningViewModel, view);

        Button practiceButton = view.findViewById(R.id.practice_button);
        practiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), StudyActivity.class);
                startActivity(intent);
            }
        });

        progressBar = view.findViewById(R.id.progress);
        progressBar.setProgress(50);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        progressBar.getLayoutParams().width = size.x / 3;
        progressBar.invalidate();

        return view;
    }

    /**
     * 更新UI视图s
     * @param learningViewModel
     * @param view
     */
    private void subscribe(LearningViewModel learningViewModel, View view){
        learningViewModel.getLearningDay().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                ((TextView)view.findViewById(R.id.learning_day)).setText(getResources().getString(R.string.learning_fragment_learning_day, integer));
            }
        });
        learningViewModel.getNewXP().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                ((TextView)view.findViewById(R.id.new_xp)).setText(getResources().getString(R.string.learning_fragment_new_xp, integer));
            }
        });
        learningViewModel.getReviewXP().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                ((TextView)view.findViewById(R.id.review_xp)).setText(getResources().getString(R.string.learning_fragment_review_xp, integer));
            }
        });
        learningViewModel.getGainToday().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                ((TextView)view.findViewById(R.id.gain_xp)).setText(getResources().getString(R.string.learning_fragment_gain_xp_today, integer));
            }
        });
    }
}
