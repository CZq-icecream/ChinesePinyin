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
@Entity(tableName = "lesson")
public class Lesson {

    /**
     * 主键
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;

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
    /**
     * 代表学完该课程后获得的全部xp
     */
    @ColumnInfo(name = "total_xp")
    private Integer totalXP;
    /**
     * 代表课程名称
     */
    @ColumnInfo(name = "lesson_name")
    private String lessonName;
    /**
     * 代表该课程图片的路径
     */
    @ColumnInfo(name = "path")
    private String path;

    public Lesson() {
    }

    @Ignore
    public Lesson(Integer lessonId, Integer progress, Integer totalXP, String lessonName, String path) {
        this.lessonId = lessonId;
        this.progress = progress;
        this.totalXP = totalXP;
        this.lessonName = lessonName;
        this.path = path;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", lessonId=" + lessonId +
                ", progress=" + progress +
                ", totalXP=" + totalXP +
                ", lessonName='" + lessonName + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getTotalXP() {
        return totalXP;
    }

    public void setTotalXP(Integer totalXP) {
        this.totalXP = totalXP;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
