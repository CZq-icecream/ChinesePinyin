package com.czq.chinesepinyin.ui.study;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.czq.chinesepinyin.R;
import com.czq.chinesepinyin.dao.HistoryLessonDao;
import com.czq.chinesepinyin.dao.UserDao;
import com.czq.chinesepinyin.database.HistoryLessonDatabase;
import com.czq.chinesepinyin.database.UserDatabase;
import com.czq.chinesepinyin.entity.Detail;
import com.czq.chinesepinyin.entity.HistoryLesson;
import com.czq.chinesepinyin.entity.User;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.io.IOException;

/**
 * 答题后的细节界面
 * @date 2020.2.25
 * @author czq
 */
public class DetailFragment extends Fragment {

    private static final String TAG = "DetailFragment";

    private DetailViewModel detailViewModel;
    private NavController controller;

    private ImageView back;
    private ImageView chineseImageView;
    private ImageView chineseIllustrationImageView;
    private ImageView chineseMeaningImageView;
    private Button nextButton;
    private MediaPlayer mediaPlayer;

    private PlayerView playerView;
    private SimpleExoPlayer player;

    private boolean playWhenReady = false;
    private int currentWindow = 0;
    private long playbackPosition = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        controller = NavHostFragment.findNavController(this);
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);

        //因为控件的设置涉及宽和高，所以需要等计算结束之后才将图片绘制到控件上
        view.post(new Runnable() {
            @Override
            public void run() {
                init(view, controller);
                subscribe(detailViewModel, controller);
            }
        });

        return view;
    }

    private void init(View view, NavController controller){
        back = view.findViewById(R.id.back);
        chineseImageView = view.findViewById(R.id.chinese);
        chineseIllustrationImageView = view.findViewById(R.id.chinese_illustration);
        chineseMeaningImageView = view.findViewById(R.id.chinese_meaning);
        playerView = view.findViewById(R.id.vedio_view);
        nextButton = view.findViewById(R.id.next);
    }

    public void subscribe(DetailViewModel detailViewModel, NavController controller) {
        detailViewModel.getDetailLiveData().observe(this, new Observer<Detail>() {
            @Override
            public void onChanged(Detail detail) {
                Glide.with(DetailFragment.this).load(detail.getChinesePath()).into(chineseImageView);
                Glide.with(DetailFragment.this).load(detail.getIllustrationPath()).into(chineseIllustrationImageView);
                Glide.with(DetailFragment.this).load(detail.getMeaningPath()).into(chineseMeaningImageView);
                chineseImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        if (mediaPlayer == null){
                            mediaPlayer = new MediaPlayer();
                            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                            try {
                                mediaPlayer.setDataSource(detail.getAudioPath());
                                mediaPlayer.prepare();
                            } catch (IOException e){
                                e.printStackTrace();
                            }
                            mediaPlayer.start();
                        }else {
                            mediaPlayer.start();
                        }
                    }
                });

                initializePlayer();

                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //释放音频资源
                        if (mediaPlayer != null) {
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }

                        //更新学习进度
                        detailViewModel.updateProgress();
                        //转到下一题
                        controller.navigate(R.id.action_detailFragment_to_optionFragment);
                    }
                });
            }
        });
    }

    private void initializePlayer(){
        player = ExoPlayerFactory.newSimpleInstance(getActivity().getApplicationContext());
        playerView.setPlayer(player);
        Uri uri = Uri.parse("http://192.168.43.42:8080/chinesepinyin/video/1/1");
        DataSource.Factory dataSourceFactor =
                new DefaultDataSourceFactory(getActivity(), "exoplayer");
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactor)
                .createMediaSource(uri);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.prepare(mediaSource, false, false);
    }

    private void releasePlayer(){
        if (player != null) {
            player.release();
            player = null;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }



    /**
     * 对Bitmap进行缩放，使图片适应ImageButton的大小
     * https://blog.csdn.net/zhudonggangg/article/details/70162614
     * @param bitmap
     * @param newWidth
     * @param newHeight
     * @return
     */
    private Bitmap getScaleBitmap(Bitmap bitmap, float newWidth, float newHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = newWidth / width;
        float scaleHeight = newHeight / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }
}
