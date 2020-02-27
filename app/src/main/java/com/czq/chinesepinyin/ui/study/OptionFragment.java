package com.czq.chinesepinyin.ui.study;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.czq.chinesepinyin.R;

/**
 * 学习界面的Fragment
 * @date 2020.2.24
 * @author czq
 */
public class OptionFragment extends Fragment {

    private static final String TAG = "OptionFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_option, container, false);

        NavController controller = NavHostFragment.findNavController(this);
        init(view, controller);

        //获得ViewModel
        //监听数据变化
        OptionViewModel optionViewModel = ViewModelProviders.of(requireActivity()).get(OptionViewModel.class);

        Log.d(TAG, optionViewModel.toString());
        return view;
    }

    private void init(View view, NavController controller){
        ImageButton option1 = view.findViewById(R.id.option1);
        ImageButton option2 = view.findViewById(R.id.option2);
        ImageButton option3 = view.findViewById(R.id.option3);
        ImageButton option4 = view.findViewById(R.id.option4);

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.action_optionFragment_to_detailFragment);
            }
        });
    }
}
