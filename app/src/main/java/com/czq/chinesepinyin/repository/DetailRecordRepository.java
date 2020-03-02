package com.czq.chinesepinyin.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.czq.chinesepinyin.entity.DetailRecord;
import com.czq.chinesepinyin.impl.DetailRecordServiceImpl;
import com.czq.chinesepinyin.service.DetailRecordService;

/**
 * @date 2020.3.1
 * @author czq
 */
public class DetailRecordRepository {

    private DetailRecordService detailRecordService;

    public DetailRecordRepository(Application application){
        detailRecordService = new DetailRecordServiceImpl(application);
    }

    public LiveData<DetailRecord> getDetailRecord() {
        return detailRecordService.getDetailRecordMutableLiveData();
    }
}
