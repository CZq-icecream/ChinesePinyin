package com.czq.chinesepinyin.ui.study;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.czq.chinesepinyin.R;
import com.czq.chinesepinyin.entity.OptionRecord;
import com.czq.chinesepinyin.entity.Sound;

/**
 * 学习界面的Fragment
 * @date 2020.2.24
 * @author czq
 */
public class OptionFragment extends Fragment {

    private static final String TAG = "OptionFragment";

    private ImageButton option1;
    private ImageButton option2;
    private ImageButton option3;
    private ImageButton option4;

    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_option, container, false);

        NavController controller = NavHostFragment.findNavController(this);
        OptionViewModel optionViewModel = ViewModelProviders.of(this).get(OptionViewModel.class);

        init(view, controller, optionViewModel);

        textView = view.findViewById(R.id.test);
        subscribe(optionViewModel);

        return view;
    }

    private void init(View view, NavController controller, OptionViewModel optionViewModel){
        option1 = view.findViewById(R.id.option1);
        option2 = view.findViewById(R.id.option2);
        option3 = view.findViewById(R.id.option3);
        option4 = view.findViewById(R.id.option4);


        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, optionViewModel.getOptionRecordLiveData().getValue().toString());
                AssetManager assetManager = getActivity().getAssets();
                MediaPlayer mediaPlayer = new MediaPlayer();

                Sound sound = optionViewModel.getOptionRecordLiveData().getValue().getSound();

                try {
                    AssetFileDescriptor assetFileDescriptor = assetManager.openFd(sound.getAssetPath());
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),
                            assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (Exception e) {
                    Log.d(TAG, "error occur");
                }

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

    private void subscribe(OptionViewModel optionViewModel) {
        optionViewModel.getOptionRecordLiveData().observe(this, new Observer<OptionRecord>() {
            @Override
            public void onChanged(OptionRecord optionRecord) {
//                textView.setText("lessonId = " + optionRecord.getLessonId());
//                option1.setScaleType(ImageView.ScaleType.FIT_XY);
                //TODO 3 位图缩放，Bitmap只会存储实际像素数据，需要手动缩放
                //TODO 4 点击过快时声音资源还未加载好
//                Bitmap bitmap = optionViewModel.getOptionRecordLiveData().getValue().getBitmaps().get(0);

//                bitmap.setWidth(option1.getWidth());
//                bitmap.setHeight(option1.getHeight());
//                option1.setImageBitmap(bitmap);
//                option1.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        });
    }
}
