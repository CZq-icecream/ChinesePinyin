package com.czq.chinesepinyin.ui.study;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.czq.chinesepinyin.entity.OptionRecord;

/**
 * @date 2020.2.27
 * @author czq
 */
public class DetailViewModel extends AndroidViewModel {

    /**
     * 这里的optionRecord发生修改时可以监测到
     */
    private OptionRecord optionRecord;
    private MutableLiveData<OptionRecord> optionRecordLiveData;


    public DetailViewModel(@NonNull Application application) {
        super(application);

        //1. 访问数据库，得到自己的课程id和progress
        //2. 访问数据库或者网络获取OptionRecord
    }
}
