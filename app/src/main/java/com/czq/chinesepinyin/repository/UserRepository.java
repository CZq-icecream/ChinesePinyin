package com.czq.chinesepinyin.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.czq.chinesepinyin.dao.UserDao;
import com.czq.chinesepinyin.database.UserDatabase;
import com.czq.chinesepinyin.entity.Lesson;
import com.czq.chinesepinyin.entity.User;
import com.czq.chinesepinyin.util.AuthenticationState;
import com.czq.chinesepinyin.util.Cache;
import com.czq.chinesepinyin.util.Constant;
import com.czq.chinesepinyin.util.JsonToObject;
import com.czq.chinesepinyin.webservice.UserService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * 提供访问API的repository
 * @date 2020.2.23
 * @author czq
 */
public class UserRepository {

    private static final String TAG = "UserRepository";

    private UserService userService;
    private Retrofit retrofit;
    private UserDatabase userDatabase;
    private UserDao userDao;

    private Cache cache;

    public UserRepository(Application application) {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.getBaseUrl())
                .build();
        userService = retrofit.create(UserService.class);
        userDatabase = UserDatabase.getUserDatabase(application);
        userDao = userDatabase.userDao();
        cache = Cache.getInstance(application.getApplicationContext());
    }

    public LiveData<User> selectUser(){
        if (cache.get(Constant.getUSER()) == null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    splashLogin();
                }
            }).start();
        }
        return (LiveData<User>) cache.get(Constant.getUSER());
    }

    /**
     * Splash界面的用户登录
     */
    public void splashLogin() {
        //登录之前的初始化工作
        loginInit();
        //从数据库中获取用户信息
        User user = userDao.getUser();
        if (user == null) {
            Log.d(TAG, "userDatabase has no data");
            return;
        }
        Log.d(TAG, user.toString());
        //再次登录，获取最新数据
        login(user.getUsername(), user.getPassword());
    }

    /**
     * 登录
     * @param username
     * @param password
     */
    public void login(String username, String password) {
        Log.d(TAG, "login ---> " + username + ", " + password);
        JSONObject jsonObject = loginJsonObject(username, password);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        Call<ResponseBody> task = userService.login(body);
        task.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    ResponseBody responseBody = response.body();
                    //TODO 此处还未考虑异常情况
                    if (responseBody == null) {
                        Log.d(TAG, "responseBody is null");
                        return;
                    }
                    //设置用户状态为登录
                    cache.put(Constant.getAuthenticationState(), AuthenticationState.AUTHENTICATED);
                    MutableLiveData<AuthenticationState> authenticationStateMutableLiveData =
                            (MutableLiveData<AuthenticationState>) cache.get(Constant.getAuthenticationStateLiveData());
                    authenticationStateMutableLiveData.setValue(AuthenticationState.AUTHENTICATED);
                    try {
                        String jsonString = responseBody.string();
                        Log.d(TAG, "responseBody:" + jsonString);
                        User user = JsonToObject.JsonToUser(jsonString);
                        Log.d(TAG, user.toString());
                        //设置用户信息
                        MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();
                        userMutableLiveData.setValue(user);
                        //将用户信息放入缓存
                        cache.put(Constant.getUSER(), userMutableLiveData);
                        //把相关的信息放入缓存
                        cache.put(Constant.getCurrentLessonId(), user.getCurrentLessonId());
                        cache.put(Constant.getCurrentLessonProgress(), user.getCurrentLessonProgress());
                        cache.put(Constant.getGainToday(), user.getGainToday());
                        cache.put(Constant.getDailyGoal(), user.getDailyGoal());
                        //保存用户信息
                        saveUser(user);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Log.d(TAG, "response.code() == " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //登录失败
                Log.d(TAG, "login ---> " + t.getMessage());
            }
        });
    }


    private JSONObject loginJsonObject(String username, String password){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constant.getUSERNAME(), username);
            jsonObject.put(Constant.getPASSWORD(), password);
        } catch (JSONException e) {
            Log.d(TAG, e.getMessage());
        }
        return jsonObject;
    }

    private JSONObject registerJsonObject(String username, String password) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constant.getUSERNAME(), username);
            jsonObject.put(Constant.getPASSWORD(), password);
        } catch (JSONException e) {
            Log.d(TAG, e.getMessage());
        }
        return jsonObject;
    }

    /**
     * 用户登录前初始化的方法
     * 解决问题：如果等待连接太久，主界面无法得到缓存中的数据进而崩溃
     */
    private void loginInit(){
        //设置用户状态为未登录
        cache.put(Constant.getAuthenticationState(), AuthenticationState.UNAUTHENTICATED);
        MutableLiveData<AuthenticationState> authenticationStateMutableLiveData = new MutableLiveData<>(AuthenticationState.UNAUTHENTICATED);
        cache.put(Constant.getAuthenticationStateLiveData(), authenticationStateMutableLiveData);
        //设置用户信息为null
        MutableLiveData<User> userMutableLiveData = new MutableLiveData<>(Constant.getUser());
        cache.put(Constant.getUSER(), userMutableLiveData);
    }

    /**
     * 获得用户学习的历史记录
     */
    public void initHistoryLesson(){
        MutableLiveData<User> userMutableLiveData = (MutableLiveData<User>) cache.get(Constant.getUSER());
        User user = userMutableLiveData.getValue();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constant.getUUID(), user.getUuid());
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
            Call<ResponseBody> task = userService.getUserHistoryLesson(requestBody);
            task.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == HttpURLConnection.HTTP_OK) {
                        ResponseBody responseBody = response.body();
                        if (responseBody == null) {
                            Log.d(TAG, "responseBody == null");
                            return;
                        }
                        try {
                            String jsonString = responseBody.string();
                            Log.d(TAG, jsonString);
                            List<Lesson> list = JsonToObject.JsonToHistoryLesson(jsonString);
                            Log.d(TAG, list.toString());
                            //保存历史课程
                            saveHistoryLesson(list);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d(TAG, "initHistoryLesson ---> " + t.getMessage());
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * 开启新的线程，保存用户信息
     * @param user
     */
    private void saveUser(User user) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                //删除用户信息
                userDao.deleteAll();
                //插入用户信息
                userDao.insertUser(user);
            }
        });
    }

    /**
     * 开启新的线程，保存用户历史学习记录
     * @param list
     */
    private void saveHistoryLesson(List<Lesson> list) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                //删除历史课程记录
//                historyLessonDao.deleteAll();
//                for (Lesson historyLesson : list) {
//                    //插入最新课程记录
//                    historyLessonDao.insert(historyLesson);
//                }
            }
        });
    }

    public void register(String username, String password) {
        JSONObject jsonObject = registerJsonObject(username, password);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        Call<ResponseBody> task = userService.register(requestBody);
        task.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    ResponseBody responseBody = response.body();
                    try {
                        String jsonString = responseBody.string();
                        //注册成功
                        //TODO 注册失败检测
                        User user = JsonToObject.JsonToUser(jsonString);
                        Log.d(TAG, user.toString());
                        //将用户存入数据库
                        saveUser(user);
                        //设置登录状态
                        cache.put(Constant.getAuthenticationState(), AuthenticationState.AUTHENTICATED);
                        MutableLiveData<AuthenticationState> authenticationStateMutableLiveData =
                                (MutableLiveData<AuthenticationState>) cache.get(Constant.getAuthenticationStateLiveData());
                        authenticationStateMutableLiveData.setValue(AuthenticationState.AUTHENTICATED);
                        ///设置用户信息
                        MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();
                        userMutableLiveData.setValue(user);
                        //将用户信息放入缓存
                        cache.put(Constant.getUSER(), userMutableLiveData);
                        //把相关的信息放入缓存
                        cache.put(Constant.getCurrentLessonId(), user.getCurrentLessonId());
                        cache.put(Constant.getCurrentLessonProgress(), user.getCurrentLessonProgress());
                        cache.put(Constant.getGainToday(), user.getGainToday());
                        cache.put(Constant.getDailyGoal(), user.getDailyGoal());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    Log.d(TAG, "response.code() == " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "register --->  " + t.getMessage());
            }
        });
    }
}
