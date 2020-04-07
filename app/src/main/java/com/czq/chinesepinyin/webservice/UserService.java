package com.czq.chinesepinyin.webservice;


import com.czq.chinesepinyin.entity.User;

import org.json.JSONObject;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @POST(value = "/chinesepinyin/login")
    Call<ResponseBody> login(@Body RequestBody requestBody);

    @POST(value = "/chinesepinyin/register")
    Call<ResponseBody> register(@Body RequestBody requestBody);

    @POST(value = "/chinesepinyin/userhistorylesson")
    Call<ResponseBody> getUserHistoryLesson(@Body RequestBody requestBody);
}