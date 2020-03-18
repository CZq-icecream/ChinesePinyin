package com.czq.chinesepinyin.service.impl;

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
import com.czq.chinesepinyin.entity.DetailRecord;
import com.czq.chinesepinyin.entity.HistoryLesson;
import com.czq.chinesepinyin.entity.Sound;
import com.czq.chinesepinyin.entity.User;
import com.czq.chinesepinyin.entity.Video;
import com.czq.chinesepinyin.service.DetailRecordService;

import java.io.IOException;
import java.io.InputStream;

/**
 * @date 2020.3.1
 * @author czq
 */
public class DetailRecordServiceImpl implements DetailRecordService {

    private static final String TAG = "DetailRecordServiceImpl";
    private UserDatabase userDatabase;
    private HistoryLessonDatabase historyLessonDatabase;
    private MutableLiveData<DetailRecord> detailRecordMutableLiveData = new MutableLiveData<>();
    private DetailRecord detailRecord;
    private AssetManager assetManager;

    public DetailRecordServiceImpl(Application application) {
        userDatabase = UserDatabase.getUserDatabase(application);
        historyLessonDatabase = HistoryLessonDatabase.getHistoryLessonDatabase(application);
        assetManager = application.getAssets();
    }

    @Override
    public LiveData<DetailRecord> getDetailRecordMutableLiveData() {
        new FetchDetailRecordTask().execute();

        return detailRecordMutableLiveData;
    }


    private class FetchDetailRecordTask extends AsyncTask<Void, Void, Void> {

        private static final String TAG = "FetchDetailRecordTask";
        private static final String PATH = "chinesepinyin";
        private static final String SUFFIX_SOUND = ".mp3";
        private static final String SUFFIX_PIC = ".png";
        private static final String SUFFIX_ILLUSTRATION = "_illustration.jpeg";
        private static final String SUFFIX_MEANING = "_meaning.png";
        private static final String SUFFIX_VIDEO = ".mp4    ";

        @Override
        protected Void doInBackground(Void... voids) {
            //查询用户信息
            UserDao userDao = userDatabase.userDao();
            User user = userDao.getUser();
            //查询当前课程id
            int currentLessonId = user.getCurrentLessonId();
            //查询当前课程信息
            HistoryLessonDao historyLessonDao = historyLessonDatabase.historyLessonDao();
            HistoryLesson historyLesson = historyLessonDao.selectHistoryLesson(currentLessonId);
            //查询进度，即当前要显示的内容
            int progress = historyLesson.getProgress();

            try {
                String[] list = assetManager.list(PATH);
                String chinesePath = PATH + "/" + list[progress] + "/" + list[progress] + SUFFIX_PIC;
                Bitmap chinese = parseBitmap(chinesePath, list, assetManager);
                Sound sound = parseSound(progress, list, assetManager);
                String chineseIllustrationPath = PATH + "/" + list[progress] + "/" + list[progress] + SUFFIX_ILLUSTRATION;
                Bitmap chineseIllustration = parseBitmap(chineseIllustrationPath, list, assetManager);
                String chineseMeaningPath = PATH + "/" + list[progress] + "/" + list[progress] + SUFFIX_MEANING;
                Bitmap chineseMeaning = parseBitmap(chineseMeaningPath, list, assetManager);
//                Video video = parseVideo(progress, list, assetManager);
                Video video = null;
                detailRecord = new DetailRecord(currentLessonId, chinese, sound, chineseIllustration, chineseMeaning, video);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            detailRecordMutableLiveData.setValue(detailRecord);
        }

        /**
         * 提取图片
         * @param path
         * @param list
         * @param assetManager
         * @return
         */
        private Bitmap parseBitmap(String path, String[] list, AssetManager assetManager) {
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

        private Video parseVideo(int index, String[] list, AssetManager assetManager) {
            String videoPath = PATH + "/" + list[index] + SUFFIX_VIDEO;
            String videoName= list[index] + SUFFIX_VIDEO;
            return new Video(videoPath, videoName, assetManager);
        }
    }
}

