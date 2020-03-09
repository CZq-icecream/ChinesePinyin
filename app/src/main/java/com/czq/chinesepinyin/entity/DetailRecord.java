package com.czq.chinesepinyin.entity;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.SoundPool;

/**
 * 代表显示在DetailFragment的一条记录
 * @date 2020.2.27
 * @author czq
 */
public class DetailRecord {

    private Integer lessonId;   //该记录所属课程的id
    private Bitmap chinese;  //该记录的字形
    private Sound sound;    //该字读音
    private Bitmap illustration;    //该字的图片描述
    private Bitmap chineseMeaning;  //该字的解释
    private Video video;    //学习视频


    public DetailRecord(Integer lessonId, Bitmap chinese, Sound sound, Bitmap illustration, Bitmap chineseMeaning, Video video) {
        this.lessonId = lessonId;
        this.chinese = chinese;
        this.sound = sound;
        this.illustration = illustration;
        this.chineseMeaning = chineseMeaning;
        this.video = video;
    }

    @Override
    public String toString() {
        return "DetailRecord{" +
                "lessonId=" + lessonId +
                ", chinese=" + chinese +
                ", sound=" + sound +
                ", illustration=" + illustration +
                ", chineseMeaning=" + chineseMeaning +
                '}';
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public Bitmap getChinese() {
        return chinese;
    }

    public void setChinese(Bitmap chinese) {
        this.chinese = chinese;
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public Bitmap getIllustration() {
        return illustration;
    }

    public void setIllustration(Bitmap illustration) {
        this.illustration = illustration;
    }

    public Bitmap getChineseMeaning() {
        return chineseMeaning;
    }

    public void setChineseMeaning(Bitmap chineseMeaning) {
        this.chineseMeaning = chineseMeaning;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
