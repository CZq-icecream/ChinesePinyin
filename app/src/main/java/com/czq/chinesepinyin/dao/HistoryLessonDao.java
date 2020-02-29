package com.czq.chinesepinyin.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.czq.chinesepinyin.entity.HistoryLesson;

import java.util.jar.Attributes;

/**
 * @date 2020.2.28
 * @author czq
 */
@Dao
public interface HistoryLessonDao {


    /**
     * 根据课程id查询历史课程信息
     * @return
     */
    @Query("SELECT * FROM history_lesson WHERE lesson_id = :lessonId")
    HistoryLesson selectHistoryLesson(Integer lessonId);

    /**
     * 删除该表的记录
     */
    @Query("DELETE FROM history_lesson")
    void deleteAll();

    /**
     * 插入一条记录
     * @param historyLesson
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertHistoryLesson(HistoryLesson historyLesson);
}
