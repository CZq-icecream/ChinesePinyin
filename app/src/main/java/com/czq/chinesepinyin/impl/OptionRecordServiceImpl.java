package com.czq.chinesepinyin.impl;

import android.app.Application;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.czq.chinesepinyin.dao.HistoryLessonDao;
import com.czq.chinesepinyin.dao.UserDao;
import com.czq.chinesepinyin.database.HistoryLessonDatabase;
import com.czq.chinesepinyin.database.UserDatabase;
import com.czq.chinesepinyin.entity.HistoryLesson;
import com.czq.chinesepinyin.entity.OptionRecord;
import com.czq.chinesepinyin.entity.Sound;
import com.czq.chinesepinyin.entity.User;
import com.czq.chinesepinyin.service.OptionRecordService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 用于获取学习题目
 * @date 2020.2.28
 * @author czq
 */
public class OptionRecordServiceImpl implements OptionRecordService {

    private static final String TAG = "OptionRecordServiceImpl";

    private UserDatabase userDatabase;
    private HistoryLessonDatabase historyLessonDatabase;
    private OptionRecord optionRecord;
    private MutableLiveData<OptionRecord> optionRecordMutableLiveData = new MutableLiveData<>();
    private AssetManager assetManager;

    public OptionRecordServiceImpl(Application application) {
        userDatabase = UserDatabase.getUserDatabase(application);
        historyLessonDatabase = HistoryLessonDatabase.getHistoryLessonDatabase(application);
        assetManager = application.getAssets();
    }


    @Override
    public LiveData<OptionRecord> getOptionRecordLiveData(){
        //1. 从数据库获取用户当前的课程id，每日目标，以及课程进度
        //2. 向网络请求数据（这里先从asset文件夹获得数据）
        //3. 包装成MutableLiveData返回

        //获取用户信息
        new FetchUser().execute();

        return optionRecordMutableLiveData;
    }

    /**
     * 从assets中加载图片
     * @param path
     * @return
     */
    private Bitmap loadBitmap(String path){
        return null;
    }

    public OptionRecord getOptionRecord(){
        return optionRecord;
    }

    /**
     * Room does not allow accessing the database on the main thread unless you called
     * allowMainThreadQueries() on the builder because it might potentially lock the UI for a long periods of time.
     * 不允许在UI线程上查询，所以进行异步查询，无法直接获得用户的课程id，每日目标，课程进度等数据
     * 所以在异步线程中进行这些操作
     */
    private class FetchUser extends AsyncTask<Void, Void, OptionRecord>{
        private static final String TAG = "FetchUser";

        @Override
        protected OptionRecord doInBackground(Void... voids) {
            //获得User信息（然后这里让UserDao的返回值为User，而不是LiveData，因为LiveData仍然进行异步查询，这样得到的User就为null）
            //TODO 1 user为null时的异常处理
            UserDao userDao = userDatabase.userDao();
            User user = userDao.selectUser();
            //TODO 2 historyLesson为null时的异常处理
            //获取HistoryLesson信息（此处同理获取HistoryLesson）
            HistoryLessonDao historyLessonDao = historyLessonDatabase.historyLessonDao();
            HistoryLesson historyLesson = historyLessonDao.selectHistoryLesson(user.getCurrentLessonId());

            try {
                //此处暂时把这个进度作为assets文件夹中开始出题的位置
                Integer progress = historyLesson.getProgress();
                String[] list = assetManager.list("chinesepinyin");
                String assetPath = "chinesepinyin" + "/" + list[progress] + "/" + list[progress] + ".mp3";
                Sound sound = new Sound(assetPath, assetManager);
                List<Bitmap> bitmaps = new ArrayList<>();
                String bitmapPath = "chinesepinyin" + "/" + list[progress] + "/" + list[progress] + ".png";
                InputStream inputStream = null;
                Bitmap bitmap = null;
                try {
                    inputStream = assetManager.open(bitmapPath);
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    inputStream.close();
                    bitmaps.add(bitmap);
                } catch (Exception e) {
                    Log.d(TAG, "error occur");
                }
                Log.d(TAG, bitmap.toString());
                optionRecord = new OptionRecord(user.getCurrentLessonId(), progress, sound, bitmaps, progress);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.d(TAG, "does this repeat?");

            return optionRecord;
        }

        @Override
        protected void onPostExecute(OptionRecord optionRecord) {
            optionRecordMutableLiveData.setValue(optionRecord);
            Log.d(TAG, optionRecord.toString());
        }
    }

}
