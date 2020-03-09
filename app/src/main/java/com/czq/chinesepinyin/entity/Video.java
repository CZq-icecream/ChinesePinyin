package com.czq.chinesepinyin.entity;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * 表示视频
 * @date 2020.3.3
 * @author czq
 */
public class Video {

    /**
     * 文件路径
     */
    private String path;
    /**
     * 文件名称
     */
    private String name;
    private AssetManager assetManager;

    public Video(String path, String name, AssetManager assetManager) {
        this.path = path;
        this.name = name;
        this.assetManager = assetManager;
    }

    /**
     * 视频播放
     * https://juejin.im/post/5bec0958e51d454c7d0f9a32#heading-2
     */
    public void play(){
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            AssetFileDescriptor assetFileDescriptor = assetManager.openFd(path);
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),
                    assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //设置准备就绪状态监听
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //开始播放
                mediaPlayer.start();
            }
        });

        //准备播放
        mediaPlayer.prepareAsync();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
