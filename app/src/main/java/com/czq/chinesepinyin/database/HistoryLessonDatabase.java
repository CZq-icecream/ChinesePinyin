package com.czq.chinesepinyin.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.czq.chinesepinyin.dao.HistoryLessonDao;
import com.czq.chinesepinyin.entity.HistoryLesson;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @date 2020.2.28
 * @author czq
 */
@Database(entities = {HistoryLesson.class}, version = 1, exportSchema = false)
public abstract class HistoryLessonDatabase extends RoomDatabase {

    private static final String TAG = "HistoryLessonDatabase";

    private static volatile HistoryLessonDatabase historyLessonDatabase;
    public abstract HistoryLessonDao historyLessonDao();
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static HistoryLessonDatabase getHistoryLessonDatabase(final Context context){
        if (historyLessonDatabase == null) {
            synchronized (HistoryLessonDatabase.class) {
                if (historyLessonDatabase == null) {
                    historyLessonDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            HistoryLessonDatabase.class, "history_lesson_database")
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return historyLessonDatabase;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    HistoryLessonDao historyLessonDao = historyLessonDatabase.historyLessonDao();
                    historyLessonDao.deleteAll();
                    HistoryLesson historyLesson = new HistoryLesson(1, 0);
                    historyLessonDao.insertHistoryLesson(historyLesson);
                }
            });
        }
    };
}
