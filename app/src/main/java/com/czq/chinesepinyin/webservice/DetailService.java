package com.czq.chinesepinyin.webservice;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @date 2020..3.21
 * @author czq
 */
public interface DetailService {

    @GET("/chinesepinyin/detail/{lessonId}/{index}")
    Call<ResponseBody> getDetail(@Path("lessonId")Integer lessonId,
                                 @Path("index")Integer index);
}
