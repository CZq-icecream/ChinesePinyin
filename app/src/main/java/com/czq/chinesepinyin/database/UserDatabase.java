package com.czq.chinesepinyin.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.czq.chinesepinyin.dao.UserDao;
import com.czq.chinesepinyin.entity.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 存储User
 * @date 2020.2.23
 * @author czq
 */
@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    /**
     * 单例模式，使用volatile修饰防止在DLC时发生引用逃逸
     */
    private static volatile UserDatabase userDatabase;

    public abstract UserDao userDao();

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static UserDatabase getUserDatabase(final Context context) {
        if (userDatabase == null) {
            synchronized (UserDatabase.class) {
                if (userDatabase == null) {
                    userDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            UserDatabase.class, "user_database")
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return userDatabase;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(SupportSQLiteDatabase db) {
            super.onOpen(db);

            databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    UserDao userDao = userDatabase.userDao();
                    userDao.deleteAll();
                    User user = new User("user", "pass", 14, 25, 15, 10, 1);
                    userDao.insertUser(user);
                }
            });
        }
    };
}
