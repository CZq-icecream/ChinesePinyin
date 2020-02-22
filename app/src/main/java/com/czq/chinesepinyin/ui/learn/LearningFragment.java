package com.czq.chinesepinyin.ui.learn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.czq.chinesepinyin.R;
import com.czq.chinesepinyin.entity.DailyGoal;

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

        LearningViewModel learningViewModel = ViewModelProviders.of(requireActivity(), new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                if (modelClass.isAssignableFrom(LearningViewModel.class)) {
                    return (T)new LearningViewModel(requireActivity().getApplication(), 1);
                }
                throw new RuntimeException("Cannot create an instance of " + modelClass.getName());
            }
        }).get(LearningViewModel.class);
        learningViewModel.getDailyGoalMutableLiveData().observe(this, new Observer<DailyGoal>() {
            @Override
            public void onChanged(DailyGoal dailyGoal) {
                ((TextView)view.findViewById(R.id.learning_day)).setText(getResources().getString(R.string.learning, 100));
                ((TextView)view.findViewById(R.id.new_xp)).setText(getResources().getString(R.string.new_xp, dailyGoal.getNewXP()));
                ((TextView)view.findViewById(R.id.review_xp)).setText(getResources().getString(R.string.review_xp, dailyGoal.getReviewXP()));
            }
        });

        Button testButton = view.findViewById(R.id.test);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}
