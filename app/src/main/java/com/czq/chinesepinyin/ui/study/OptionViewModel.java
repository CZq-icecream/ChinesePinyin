package com.czq.chinesepinyin.ui.study;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.czq.chinesepinyin.R;
import com.czq.chinesepinyin.entity.Option;
import com.czq.chinesepinyin.repository.OptionRepository;
import com.czq.chinesepinyin.util.Cache;


/**
 * @date 2020.2.27
 * @author czq
 */
public class OptionViewModel extends AndroidViewModel {

    private static final String TAG = "OptionViewModel";
    private OptionRepository optionRepository;
    private Cache cache = null;

    public OptionViewModel(@NonNull Application application) {
        super(application);
        cache = Cache.getInstance(application.getApplicationContext());
        optionRepository = new OptionRepository();
    }


    public LiveData<Option> getOption(){
        return optionRepository.getOption(1, 1);
    }
}
