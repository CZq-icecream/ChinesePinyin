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
    private Sound sound;    //读音
    private Bitmap[] bitmaps;  //四张备选项的图片
    private Integer correctId;  //正确答案的id

    public OptionRecord() {
    }

    public OptionRecord(Integer lessonId, Integer progress, Sound sound, Bitmap[] bitmaps, Integer correctId) {
        this.lessonId = lessonId;
        this.progress = progress;
        this.sound = sound;
        this.bitmaps = bitmaps;
        this.correctId = correctId;
    }

    @Override
    public String toString() {
        return "OptionRecord{" +
                "lessonId=" + lessonId +
                ", progress=" + progress +
                ", sound=" + sound +
                ", bitmaps=" + bitmaps +
                ", correctId=" + correctId +
                '}';
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public Bitmap[] getBitmaps() {
        return bitmaps;
    }

    public void setBitmaps(Bitmap[] bitmaps) {
        this.bitmaps = bitmaps;
    }

    public Integer getCorrectId() {
        return correctId;
    }

    public void setCorrectId(Integer correctId) {
        this.correctId = correctId;
    }
}
