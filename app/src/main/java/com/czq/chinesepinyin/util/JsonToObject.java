package com.czq.chinesepinyin.util;

import com.czq.chinesepinyin.entity.Detail;
import com.czq.chinesepinyin.entity.HistoryLesson;
import com.czq.chinesepinyin.entity.Option;
import com.czq.chinesepinyin.entity.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于将Json字符串转为对象
 * 因为GsonConvertFactory解析有些问题
 * @date 2020.3.19
 * @author czq
 */
public class JsonToObject {

    private JsonToObject(){}

    public static Option JsonToOption(String jsonString) {
        Option option = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            int lessonId = jsonObject.getInt("lessonId");
            int progress = jsonObject.getInt("progress");
            String audioPath = jsonObject.getString("audioPath");
            JSONArray jsonArray = jsonObject.getJSONArray("choicePicPath");
            String[] choicePicPath = new String[4];
            for (int i = 0; i < 4; i++) {
                choicePicPath[i] = (String) jsonArray.get(i);
            }
            int correctId = jsonObject.getInt("correctId");
            option = new Option(lessonId, progress, audioPath, choicePicPath, correctId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return option;
    }

    public static Detail JsonToDetail(String jsonString) {
        Detail detail = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            int lessonId = jsonObject.getInt("lessonId");
            int index = jsonObject.getInt("index");
            String chinesePath = jsonObject.getString("chinesePath");
            String audioPath = jsonObject.getString("audioPath");
            String illustrationPath = jsonObject.getString("illustrationPath");
            String meaningPath = jsonObject.getString("meaningPath");
            String videoPath = jsonObject.getString("videoPath");
            detail = new Detail(lessonId, index, chinesePath, audioPath, illustrationPath, meaningPath, videoPath);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return detail;
    }

    public static User JsonToUser(String jsonString) {
        User user = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            String uuid = jsonObject.getString("uuid");
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");
            Integer learningDays = jsonObject.getInt("learningDays");
            Integer dailyGoal = jsonObject.getInt("dailyGoal");
            Integer gainToday = jsonObject.getInt("gainToday");
            Integer totalXP = jsonObject.getInt("totalXP");
            Integer currentLessonId = jsonObject.getInt("currentLessonId");
            Integer currentLessonProgress = jsonObject.getInt("currentLessonProgress");
            String profilePath = jsonObject.getString("profilePath");
            user = new User(uuid, username, password, learningDays,
                    dailyGoal, gainToday, totalXP, currentLessonId,
                    currentLessonProgress, profilePath);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static List<HistoryLesson> JsonToHistoryLesson(String jsonString) {
        List<HistoryLesson> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String uuid = jsonObject.getString("uuid");
                Integer lessonId = jsonObject.getInt("lessonId");
                Integer progress = jsonObject.getInt("progress");
                list.add(new HistoryLesson(uuid, lessonId, progress));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }
}
