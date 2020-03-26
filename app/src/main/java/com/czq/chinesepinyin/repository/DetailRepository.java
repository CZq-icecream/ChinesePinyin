package com.czq.chinesepinyin.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.czq.chinesepinyin.entity.Detail;
import com.czq.chinesepinyin.util.Constant;
import com.czq.chinesepinyin.util.JsonToObject;
import com.czq.chinesepinyin.webservice.DetailService;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @date 2020.3.21
 * @author czq
 */
public class DetailRepository{

    private static final String TAG = "DetailRepository";

    private DetailService detailService;
    private Retrofit retrofit;

    public DetailRepository(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.getBaseUrl())
                .build();
        detailService = retrofit.create(DetailService.class);
    }

    /**
     * 获取详细信息
     * @param lessonId
     * @param progress
     * @return
     */
    public LiveData<Detail> getDetail(Integer lessonId, Integer progress) {
        MutableLiveData<Detail> detailMutableLiveData = new MutableLiveData<>();
        Call<ResponseBody> task = detailService.getDetail(lessonId, progress);
        task.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    ResponseBody responseBody = response.body();
                    try {
                        String jsonString = responseBody.string();
                        Log.d(TAG, jsonString);
                        Detail detail = JsonToObject.JsonToDetail(jsonString);
                        Log.d(TAG, detail.toString());
                        detailMutableLiveData.setValue(detail);
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

        return detailMutableLiveData;
    }

}
