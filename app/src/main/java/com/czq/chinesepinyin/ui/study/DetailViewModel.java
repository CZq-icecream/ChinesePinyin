package com.czq.chinesepinyin.ui.study;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.czq.chinesepinyin.entity.Detail;
import com.czq.chinesepinyin.repository.DetailRepository;
import com.czq.chinesepinyin.util.Cache;
import com.czq.chinesepinyin.util.Constant;

/**
 * @date 2020.2.27
 * @author czq
 */
public class DetailViewModel extends AndroidViewModel {

    private static final String TAG = "DetailViewModel";

    private DetailRepository detailRepository;
    private Cache cache;

    public DetailViewModel(@NonNull Application application) {
        super(application);
        detailRepository = new DetailRepository();
        cache = Cache.getInstance(application.getApplicationContext());
    }


    public LiveData<Detail> getDetailLiveData(){
        int lessonId = (int) cache.get(Constant.getCurrentLessonId());
        int progress = (int) cache.get(Constant.getCurrentLessonProgress());
        return detailRepository.getDetail(lessonId, progress);
    }

    public void updateProgress() {
        int progress = (int) cache.get(Constant.getCurrentLessonProgress());
        cache.put(Constant.getCurrentLessonProgress(), progress + 1);
    }
}
