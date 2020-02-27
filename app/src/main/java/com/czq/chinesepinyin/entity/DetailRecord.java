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


}
