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
    private SoundPool soundPool;    //该字读音
    private Bitmap illustration;    //该字的图片描述
    private Bitmap chineseMeaning;  //该字的解释
//    private MediaPlayer mediaPlayer;    //暂定为视频解释


    public DetailRecord(Integer lessonId, Bitmap chinese, SoundPool soundPool, Bitmap illustration, Bitmap chineseMeaning) {
        this.lessonId = lessonId;
        this.chinese = chinese;
        this.soundPool = soundPool;
        this.illustration = illustration;
        this.chineseMeaning = chineseMeaning;
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

    public SoundPool getSoundPool() {
        return soundPool;
    }

    public void setSoundPool(SoundPool soundPool) {
        this.soundPool = soundPool;
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
}
