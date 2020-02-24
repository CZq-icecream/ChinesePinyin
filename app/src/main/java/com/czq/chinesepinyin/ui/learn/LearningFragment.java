package com.czq.chinesepinyin.ui.learn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.czq.chinesepinyin.R;

/**
 * 位于主页的LearningFragment
 * @date 2020.2.19
 * @author czq
 */
public class LearningFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_learn, container, false);

        LearningViewModel learningViewModel = ViewModelProviders.of(requireActivity()).get(LearningViewModel.class);
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

        return view;
    }
}
