package com.czq.chinesepinyin.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.czq.chinesepinyin.entity.OptionRecord;
import com.czq.chinesepinyin.impl.OptionRecordServiceImpl;
import com.czq.chinesepinyin.service.OptionRecordService;

/**
 * @date 2020.2.27
 * @author czq
 */
public class OptionRecordRepository {

    private OptionRecordService optionRecordService;

    public OptionRecordRepository(Application application){
        optionRecordService = new OptionRecordServiceImpl(application);
    }

    public LiveData<OptionRecord> getOptionRecord() {
        return optionRecordService.getOptionRecordLiveData();
    }

    public LiveData<Integer> getFlag() {
        return optionRecordService.getFlag();
    }
}

