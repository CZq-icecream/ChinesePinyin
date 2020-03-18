package com.czq.chinesepinyin.ui.study;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.czq.chinesepinyin.entity.OptionRecord;
import com.czq.chinesepinyin.repository.OptionRecordRepository;

/**
 * @date 2020.2.27
 * @author czq
 */
public class OptionViewModel extends AndroidViewModel {

    private static final String TAG = "OptionViewModel";
    private OptionRecordRepository optionRecordRepository;
    private LiveData<OptionRecord> optionRecordLiveData;

    /**
     * 原本想作为缓存，结果发现每次替换后fragment都会重新获得viewmodel（重新调用viewmodel的构造函数）
     */
    private LiveData<Integer> flag;

    public OptionViewModel(@NonNull Application application) {
        super(application);
        Log.d(TAG, "I am optionviewmodel hhh");
        optionRecordRepository = new OptionRecordRepository(application);
    }

    public LiveData<OptionRecord> getOptionRecordLiveData(){
        return optionRecordRepository.getOptionRecord();
//        if (flag == null) {
//            flag = optionRecordRepository.getFlag();
//            return optionRecordRepository.getOptionRecord();
//        }else{
//            return optionRecordLiveData;
//        }

    }
}
