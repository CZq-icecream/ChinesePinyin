package com.czq.chinesepinyin.entity;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;

/**
 * 音频类
 * @date 2020.2.28
 * @author czq
 */
public class Sound {

    /**
     * 文件路径，方便后面改为url
     */
    private String path;
    /**
     * 文件名
     */
    private String name;
    private AssetManager assetManager;

    public Sound(String path, String name, AssetManager assetManager) {
        this.path = path;
        this.name = name;
        this.assetManager = assetManager;
    }

    /**
     * 音频播放
     */
    public void play(){
        try {
            AssetFileDescriptor assetFileDescriptor = assetManager.openFd(path);
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.reset();
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),
                    assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
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

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }
}
