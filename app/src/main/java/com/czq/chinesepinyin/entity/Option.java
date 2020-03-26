package com.czq.chinesepinyin.entity;

import java.util.Arrays;
import java.util.List;

/**
 * 代表一条选项
 * @date 2020.3.18
 * @author czq
 */
public class Option {

    /**
     * 课程编号
     */
    private Integer lessonId;
    /**
     * 课程进度
     */
    private Integer progress;
    /**
     * 音频路径（url）
     */
    private String audioPath;
    /**
     * 选项图片路径（url）
     */
    private String[] choicePath;
    /**
     * 正确选项编号
     */
    private Integer correctId;

    public Option(Integer lessonId, Integer progress, String audioPath, String[] choicePath, Integer correctId) {
        this.lessonId = lessonId;
        this.progress = progress;
        this.audioPath = audioPath;
        this.choicePath = choicePath;
        this.correctId = correctId;
    }

    @Override
    public String toString() {
        return "Option{" +
                "lessonId=" + lessonId +
                ", progress=" + progress +
                ", audioPath='" + audioPath + '\'' +
                ", choicePath=" + choicePath +
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

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public String[] getChoicePath() {
        return choicePath;
    }

    public void setChoicePath(String[] choicePath) {
        this.choicePath = choicePath;
    }

    public Integer getCorrectId() {
        return correctId;
    }

    public void setCorrectId(Integer correctId) {
        this.correctId = correctId;
    }
}
