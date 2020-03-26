package com.czq.chinesepinyin.ui.study;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.czq.chinesepinyin.entity.Detail;
import com.czq.chinesepinyin.repository.DetailRepository;

/**
 * @date 2020.2.27
 * @author czq
 */
public class DetailViewModel extends AndroidViewModel {

    private static final String TAG = "DetailViewModel";

    private DetailRepository detailRepository;

    public DetailViewModel(@NonNull Application application) {
        super(application);
        detailRepository = new DetailRepository();
    }


    public LiveData<Detail> getDetailLiveData(){
        return detailRepository.getDetail(1, 1);
    }
}
