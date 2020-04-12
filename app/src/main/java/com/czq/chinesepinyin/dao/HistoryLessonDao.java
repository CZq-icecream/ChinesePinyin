package com.czq.chinesepinyin.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.czq.chinesepinyin.entity.Lesson;

/**
 * @date 2020.2.28
 * @author czq
 */
@Dao
public interface HistoryLessonDao {


//    /**
//     * 根据课程id查询历史课程信息
//     * @return
//     */
//    @Query("SELECT * FROM history_lesson WHERE lesson_id = :lessonId")
//    Lesson selectHistoryLesson(Integer lessonId);

    /**
     * 删除该表的记录
     */
    @Query("DELETE FROM Lesson")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Lesson lesson);


//    /**
//     * 插入一条记录
//     * @param historyLesson
//     */
//    @Insert(onConflict = OnConflictStrategy.ABORT)
//    void insertHistoryLesson(Lesson historyLesson);
//
//    /**
//     * 更新用户学习进度
//     * @param lessonId
//     * @param progress
//     */
//    @Query("UPDATE history_lesson SET progress = :progress WHERE lesson_id = :lessonId")
//    void updateProgress(Integer lessonId, int progress);
}
