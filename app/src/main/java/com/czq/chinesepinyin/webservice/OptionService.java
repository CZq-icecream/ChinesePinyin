package com.czq.chinesepinyin.webservice;

import com.czq.chinesepinyin.entity.Option;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @date 2020.3.18
 * @author czq
 */
public interface OptionService {

    @GET("/chinesepinyin/{lessonId}/{index}")
    Call<ResponseBody> getOption(@Path("lessonId")Integer lessonId,
                           @Path("index")Integer index);


}
