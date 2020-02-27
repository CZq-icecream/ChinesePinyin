package com.czq.chinesepinyin.entity;

import android.graphics.Bitmap;
import android.media.SoundPool;

import androidx.room.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 代表显示在OptionFragment的一条学习记录
 * 暂且只有根据读音选择字形这一类型的题目
 * @date 2020.2.27
 * @author czq
 */
public class OptionRecord {

    private Integer lessonId; //该记录所属课程的id
    private Integer progress;  //进度，也表示该记录位于课程的位置（索引）
    private SoundPool soundPool;    //读音
    private List<Bitmap> bitmaps = new ArrayList<>(4);  //四张备选项的图片
    private Integer correctId;  //正确答案的id

}
