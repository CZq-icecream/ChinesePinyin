package com.czq.chinesepinyin.ui.study;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.czq.chinesepinyin.entity.OptionRecord;
import com.czq.chinesepinyin.impl.OptionRecordServiceImpl;
import com.czq.chinesepinyin.repository.OptionRecordRepository;

/**
 * @date 2020.2.27
 * @author czq
 */
public class OptionViewModel extends AndroidViewModel {

    private static final String TAG = "OptionViewModel";
    private OptionRecordRepository optionRecordRepository;
    private LiveData<OptionRecord> optionRecordLiveData;

    private LiveData<Integer> flag;

    public OptionViewModel(@NonNull Application application) {
        super(application);

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
