package com.czq.chinesepinyin.service;

import androidx.lifecycle.LiveData;

import com.czq.chinesepinyin.entity.OptionRecord;

/**
 * @date 2020.2.28
 * @author czq
 */
public interface OptionRecordService {

    LiveData<OptionRecord> getOptionRecordLiveData();
}
