package com.czq.chinesepinyin.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 代表用户可以选择的课程
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
     * 课程标题
     */
    @ColumnInfo(name = "title")
    private String title;
    /**
     * 课程描述
     */
    @ColumnInfo(name = "description")
    private String description;
    /**
     * 通过学习该课程新获得的xp
     */
    private Integer totalXP;
}
