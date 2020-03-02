package com.czq.chinesepinyin.service;

import androidx.lifecycle.LiveData;

import com.czq.chinesepinyin.entity.DetailRecord;

/**
 * @date 2020.3.1
 * @author czq
 */
public interface DetailRecordService {

    LiveData<DetailRecord> getDetailRecordMutableLiveData();
}
