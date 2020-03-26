package com.czq.chinesepinyin.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

/**
 * 用户曾经参加的课程
 * @date 2020.2.22
 * @author czq
 */
@Entity(tableName = "history_lesson")
public class HistoryLesson {

    /**
     * 主键，代表课程id
     */
    @PrimaryKey
    @ColumnInfo(name = "lesson_id")
    private Integer lessonId;
    /**
     * 代表学习了该课程的进度
     */
    @ColumnInfo(name = "progress")
    private Integer progress;

    public HistoryLesson() {
    }

    @Ignore
    public HistoryLesson(Integer lessonId, Integer progress) {
        this.lessonId = lessonId;
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "HistoryLesson{" +
                "lessonId=" + lessonId +
                ", progress=" + progress +
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
}
