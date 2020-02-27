package com.czq.chinesepinyin.ui.study;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.czq.chinesepinyin.R;
import com.czq.chinesepinyin.ui.main.MainActivity;

/**
 * 答题后的细节界面
 * @date 2020.2.25
 * @author czq
 */
public class DetailFragment extends Fragment {

    private static final String TAG = "DetailFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        NavController controller = NavHostFragment.findNavController(this);
        init(view, controller);

        DetailViewModel detailViewModel = ViewModelProviders.of(requireActivity()).get(DetailViewModel.class);
        Log.d(TAG, detailViewModel.toString());
        return view;
    }

    private void init(View view, NavController controller){
        Button next = view.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.action_detailFragment_to_optionFragment);

//                完成后跳转到主界面
//                Intent intent = new Intent(getActivity(), MainActivity.class);
//                getActivity().finish();
//                startActivity(intent);
            }
        });
    }
}
