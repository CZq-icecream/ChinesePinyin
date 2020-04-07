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
     * 主键
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;

    /**
     * 用户uuid
     */
    @ColumnInfo(name = "uuid")
    private String uuid;
    /**
     * 代表课程id
     */
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
    public HistoryLesson(String uuid, Integer lessonId, Integer progress) {
        this.uuid = uuid;
        this.lessonId = lessonId;
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "HistoryLesson{" +
                "uuid='" + uuid + '\'' +
                ", lessonId=" + lessonId +
                ", progress=" + progress +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
