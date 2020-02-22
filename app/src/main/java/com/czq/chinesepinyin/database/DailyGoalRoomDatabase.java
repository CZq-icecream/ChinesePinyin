package com.czq.chinesepinyin.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.czq.chinesepinyin.dao.DailyGoalDao;
import com.czq.chinesepinyin.entity.DailyGoal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @date 2020.2.21
 * @authro czq
 */
@Database(entities = {DailyGoal.class}, version = 1, exportSchema = true)
public abstract class DailyGoalRoomDatabase extends RoomDatabase {

    private static volatile DailyGoalRoomDatabase dailyGoalRoomDatabase;

    private static final int NUMBER_OF_THREADS = 4;
    private static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract DailyGoalDao dailyGoalDao();

    /**
     * 保证只有一个实例，加上volatile修饰可以防止内存溢出
     * @param context
     * @return
     */
    public static DailyGoalRoomDatabase getDatabase(final Context context) {
        if (dailyGoalRoomDatabase == null) {
            synchronized (DailyGoalRoomDatabase.class) {
                if (dailyGoalRoomDatabase == null) {
                    dailyGoalRoomDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            DailyGoalRoomDatabase.class, "daily_goal_database")
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return dailyGoalRoomDatabase;
    }

    public ExecutorService getDatabaseWriteExecutor(){
        return databaseWriteExecutor;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    DailyGoalDao dailyGoalDao = dailyGoalRoomDatabase.dailyGoalDao();
                    DailyGoal dailyGoal = new DailyGoal(5, 10);
                    dailyGoalDao.insertDailyGoal(dailyGoal);
                }
            });
        }
    };
}
