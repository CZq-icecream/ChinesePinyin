package com.czq.chinesepinyin.ui.immerse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.czq.chinesepinyin.R;

/**
 * 位于主页的ImmerseFragment
 * @date 2020.2.19
 * @author czq
 */
public class ImmerseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_immerse, container, false);

        return view;
    }
}
