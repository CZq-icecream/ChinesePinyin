package com.czq.chinesepinyin.impl;

import android.app.Application;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import java.util.Random;

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
    MutableLiveData<Integer> flag;

    public OptionRecordServiceImpl(Application application) {
        Log.d(TAG, "i am OptionRecordServiceImpl hhh");
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
        new FetchOptionRecordTask().execute();

        Log.d(TAG, "flag = " + flag);

        return optionRecordMutableLiveData;
    }

    @Override
    public LiveData<Integer> getFlag(){
        return flag;
    }

    /**
     * Room does not allow accessing the database on the main thread unless you called
     * allowMainThreadQueries() on the builder because it might potentially lock the UI for a long periods of time.
     * 不允许在UI线程上查询，所以进行异步查询，无法直接获得用户的课程id，每日目标，课程进度等数据
     * 所以在异步线程中进行这些操作
     */
    private class FetchOptionRecordTask extends AsyncTask<Void, Void, OptionRecord>{
        private static final String TAG = "FetchOptionRecordTask";
        private static final String PATH = "chinesepinyin";
        private static final String SUFFIX_SOUND = ".mp3";
        private static final String SUFFIX_PIC = ".png";

        @Override
        protected OptionRecord doInBackground(Void... voids) {
            //获得User信息（然后这里让UserDao的返回值为User，而不是LiveData，因为LiveData仍然进行异步查询，这样得到的User就为null）
            //TODO 1 user为null时的异常处理
            UserDao userDao = userDatabase.userDao();
            User user = userDao.selectUser().getValue();
            //TODO 2 historyLesson为null时的异常处理
            //获取HistoryLesson信息（此处同理获取HistoryLesson）
            HistoryLessonDao historyLessonDao = historyLessonDatabase.historyLessonDao();
            HistoryLesson historyLesson = historyLessonDao.selectHistoryLesson(user.getCurrentLessonId());

            try {
                //此处暂时把这个进度作为assets文件夹中开始出题的位置
                Integer progress = historyLesson.getProgress();
                flag = new MutableLiveData<>(progress);
                Log.d(TAG, "flag in FetchOptionRecordTask = " + flag);
                String[] list = assetManager.list(PATH);
                Bitmap[] bitmaps = new Bitmap[4];
                Random random = new Random();
                int correctId = random.nextInt(4);

                bitmaps[correctId] = parseBitmap(progress, list, assetManager);
                Sound sound = parseSound(progress, list, assetManager);

                for (int i = 0; i < 4; i++) {
                    if (i == correctId) {
                        continue;
                    }
                    int j = random.nextInt(list.length);
                    while (j == progress) {
                        j = random.nextInt(list.length);
                    }
                    bitmaps[i] = parseBitmap(j, list, assetManager);
                }

                Log.d(TAG, "repeate??");
                optionRecord = new OptionRecord(user.getCurrentLessonId(), progress, sound, bitmaps, progress);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.d(TAG, "does this repeat?");

            return optionRecord;
        }

        /**
         * 提取图片
         * @param index
         * @param list
         * @param assetManager
         * @return
         */
        private Bitmap parseBitmap(int index, String[] list, AssetManager assetManager) {
            String path = PATH + "/" + list[index] + "/" + list[index] + SUFFIX_PIC;
            Bitmap bitmap = null;

            try (InputStream inputStream = assetManager.open(path)) {
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (Exception e) {
                Log.d(TAG, "error occur");
            }

            return bitmap;
        }

        /**
         * 提取音频
         * @param index 在文件夹中的位置
         * @param list asset中的文件目录
         * @param assetManager
         * @return
         */
        private Sound parseSound(int index, String[] list, AssetManager assetManager){
            String soundPath = PATH + "/" + list[index] + "/" + list[index] + SUFFIX_SOUND;
            String soundName = list[index] + SUFFIX_SOUND;
            return new Sound(soundPath, soundName, assetManager);
        }

        @Override
        protected void onPostExecute(OptionRecord optionRecord) {
            optionRecordMutableLiveData.setValue(optionRecord);
            Log.d(TAG, optionRecord.toString());
        }
    }

}
