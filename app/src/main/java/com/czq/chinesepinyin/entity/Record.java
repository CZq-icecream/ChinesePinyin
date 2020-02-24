package com.czq.chinesepinyin.entity;

import androidx.room.Entity;

/**
 * 代表一条学习记录
 * @date 2020.2.21
 * @author czq
 */
@Entity
public class Record {

    private Integer id;
    private String chinese; //中文
    private String english; //英文解释
    private Integer lessonId;

}
