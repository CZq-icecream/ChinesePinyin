package com.czq.chinesepinyin.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
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


}
