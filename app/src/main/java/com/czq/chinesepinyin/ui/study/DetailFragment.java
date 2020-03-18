package com.czq.chinesepinyin.ui.study;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
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
import androidx.navigation.fragment.NavHostFragment;

import com.czq.chinesepinyin.R;
import com.czq.chinesepinyin.dao.HistoryLessonDao;
import com.czq.chinesepinyin.dao.UserDao;
import com.czq.chinesepinyin.database.HistoryLessonDatabase;
import com.czq.chinesepinyin.database.UserDatabase;
import com.czq.chinesepinyin.entity.DetailRecord;
import com.czq.chinesepinyin.entity.HistoryLesson;
import com.czq.chinesepinyin.entity.User;

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
    private ImageButton chineseImageButton;
    private ImageView chineseIllustrationImageView;
    private ImageView chineseMeaningImageView;
    private VideoView videoView;
    private Button nextButton;

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
        chineseImageButton = view.findViewById(R.id.chinese);
        chineseIllustrationImageView = view.findViewById(R.id.chinese_illustration);
        chineseMeaningImageView = view.findViewById(R.id.chinese_meaning);
        videoView = view.findViewById(R.id.video);
        nextButton = view.findViewById(R.id.next);
    }


    private void subscribe(DetailViewModel detailViewModel, NavController controller) {

        detailViewModel.getDetailRecordLiveData().observe(this, new Observer<DetailRecord>() {
            @Override
            public void onChanged(DetailRecord detailRecord) {
                //获取屏幕的宽和高
                //https://blog.csdn.net/noige/article/details/79225833
                Display display = getActivity().getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;
                int height = size.y;

                detailViewModel.getDetailRecordLiveData().observe(DetailFragment.this, new Observer<DetailRecord>() {
                    @Override
                    public void onChanged(DetailRecord detailRecord) {
                        chineseImageButton.setImageBitmap(getScaleBitmap(detailRecord.getChinese(), width / 3, height / 5));
                        chineseImageButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                detailRecord.getSound().play();
                            }
                        });
                        chineseIllustrationImageView.setImageBitmap(getScaleBitmap(detailRecord.getIllustration(), width * 2 / 3, height / 4));
                        chineseMeaningImageView.setImageBitmap(getScaleBitmap(detailRecord.getChineseMeaning(), width * 2 / 3, height / 6));
                    }
                });

                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateProgress();
                        controller.navigate(R.id.action_detailFragment_to_optionFragment);
                    }
                });
            }
        });

        String rawPath = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.a;
        videoView.setVideoPath(rawPath);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.d(TAG, "onPrepared");
            }
        });
        videoView.setVideoURI(Uri.parse(rawPath));
        videoView.setMediaController(new MediaController(getContext()));
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d(TAG, "onCompletion");
            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.d(TAG, "onError");
                return false;
            }
        });
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.start();
            }
        });
//                videoView.start();
        Log.d(TAG, getActivity().getPackageName());
    }

    /**
     * 按下next按钮之后更新课程的进度
     */
    private void updateProgress(){
        UserDatabase userDatabase = UserDatabase.getUserDatabase(getContext());
        UserDao userDao = userDatabase.userDao();
        HistoryLessonDatabase historyLessonDatabase = HistoryLessonDatabase.getHistoryLessonDatabase(getContext());
        HistoryLessonDao historyLessonDao = historyLessonDatabase.historyLessonDao();
        UserDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                User user = userDao.getUser();
                int lessonId = user.getCurrentLessonId();
                HistoryLesson historyLesson = historyLessonDao.selectHistoryLesson(lessonId);
                int progress = historyLesson.getProgress();
                historyLessonDao.updateProgress(lessonId, progress + 1);
            }
        });
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
