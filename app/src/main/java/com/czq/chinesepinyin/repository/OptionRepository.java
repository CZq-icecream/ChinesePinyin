package com.czq.chinesepinyin.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.czq.chinesepinyin.entity.Option;
import com.czq.chinesepinyin.util.Constant;
import com.czq.chinesepinyin.util.JsonToObject;
import com.czq.chinesepinyin.webservice.OptionService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @date 2020.3.18
 * @author czq
 */
public class OptionRepository {

    private static final String TAG = "OptionRepository";

    private OptionService optionService;
    private Retrofit retrofit;

    public OptionRepository() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.getBaseUrl())
                .build();
        optionService = retrofit.create(OptionService.class);
    }

    /**
     * 获取题目
     * @param lessonId
     * @param progress
     * @return
     */
    public LiveData<Option> getOption(Integer lessonId, Integer progress){
        MutableLiveData<Option> optionMutableLiveData = new MutableLiveData<>();
        Call<ResponseBody> task = optionService.getOption(1, 1);
        task.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    ResponseBody responseBody = response.body();
                    try {
                        String jsonString = responseBody.string();
                        Log.d(TAG, jsonString);
                        Option option = JsonToObject.JsonToOption(jsonString);
                        Log.d(TAG, option.toString());
                        optionMutableLiveData.setValue(option);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
        return optionMutableLiveData;
    }
}
