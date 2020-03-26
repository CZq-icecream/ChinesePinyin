package com.czq.chinesepinyin.util;

import com.czq.chinesepinyin.entity.Detail;
import com.czq.chinesepinyin.entity.Option;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
}
