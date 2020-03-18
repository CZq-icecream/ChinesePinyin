package com.czq.chinesepinyin.ui.study;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.czq.chinesepinyin.R;
import com.czq.chinesepinyin.entity.OptionRecord;
import com.czq.chinesepinyin.entity.Sound;

/**
 * 学习界面的Fragment
 * http://www.imooc.com/article/details/id/285989
 * http://www.imooc.com/article/286253
 * @date 2020.2.24
 * @author czq
 */
public class OptionFragment extends Fragment {

    private static final String TAG = "OptionFragment";

    private ImageView voice;
    private ImageButton option1;
    private ImageButton option2;
    private ImageButton option3;
    private ImageButton option4;

    private NavController controller;
    private OptionViewModel optionViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_option, container, false);
        controller = NavHostFragment.findNavController(this);
        optionViewModel = ViewModelProviders.of(this).get(OptionViewModel.class);
        //因为控件的设置涉及宽和高，所以需要等计算结束之后才将图片绘制到控件上
        view.post(new Runnable() {
            @Override
            public void run() {
                init(view);
                subscribe(optionViewModel, controller);
            }
        });

        return view;
    }

    private void init(View view){
        voice = view.findViewById(R.id.voice);
        option1 = view.findViewById(R.id.option1);
        option2 = view.findViewById(R.id.option2);
        option3 = view.findViewById(R.id.option3);
        option4 = view.findViewById(R.id.option4);
    }

    /**
     * 订阅更新
     * 这里需要等待图片加载出来才能进行点击（用asset模拟时，加载数据的时间过长，点击之后会有异常，所以在这里为ImageButton设置监听）
     * @param optionViewModel
     */
    private void subscribe(OptionViewModel optionViewModel, NavController controller) {
        optionViewModel.getOptionRecordLiveData().observe(this, new Observer<OptionRecord>() {
            @Override
            public void onChanged(OptionRecord optionRecord) {
                voice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Sound sound = optionRecord.getSound();
                        sound.play();
                    }
                });

                option1.setImageBitmap(getScaleBitmap(option1, optionRecord.getBitmaps()[0]));
                option2.setImageBitmap(getScaleBitmap(option2, optionRecord.getBitmaps()[1]));
                option3.setImageBitmap(getScaleBitmap(option3, optionRecord.getBitmaps()[2]));
                option4.setImageBitmap(getScaleBitmap(option4, optionRecord.getBitmaps()[3]));
                //TODO 4 点击过快时声音资源还未加载好

                option1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        controller.navigate(R.id.action_optionFragment_to_detailFragment);
                    }
                });
                option2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        controller.navigate(R.id.action_optionFragment_to_detailFragment);
                    }
                });
                option3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        controller.navigate(R.id.action_optionFragment_to_detailFragment);
                    }
                });
                option4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        controller.navigate(R.id.action_optionFragment_to_detailFragment);
                    }
                });
            }
        });
    }

    /**
     * 对Bitmap进行缩放，使图片适应ImageButton的大小
     * https://blog.csdn.net/zhudonggangg/article/details/70162614
     * @param imageButton
     * @param bitmap
     * @return
     */
    private Bitmap getScaleBitmap(ImageButton imageButton, Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int newWidth = imageButton.getWidth();
        int newHeight = imageButton.getHeight();
        float scaleWidth = ((float)newWidth) / width;
        float scaleHeight = ((float)newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
