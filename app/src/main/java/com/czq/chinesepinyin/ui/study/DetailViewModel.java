package com.czq.chinesepinyin.ui.study;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.czq.chinesepinyin.entity.DetailRecord;
import com.czq.chinesepinyin.entity.OptionRecord;
import com.czq.chinesepinyin.repository.DetailRecordRepository;

/**
 * @date 2020.2.27
 * @author czq
 */
public class DetailViewModel extends AndroidViewModel {

    private static final String TAG = "DetailViewModel";
    private DetailRecordRepository detailRecordRepository;
    private LiveData<DetailRecord> detailRecordLiveData;

    public DetailViewModel(@NonNull Application application) {
        super(application);

        detailRecordRepository = new DetailRecordRepository(application);
    }

    public LiveData<DetailRecord> getDetailRecordLiveData(){
        return detailRecordRepository.getDetailRecord();
    }
}
