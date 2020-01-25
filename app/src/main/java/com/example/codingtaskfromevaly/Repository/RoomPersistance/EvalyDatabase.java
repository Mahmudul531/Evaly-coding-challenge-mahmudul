package com.example.codingtaskfromevaly.Repository.RoomPersistance;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.codingtaskfromevaly.Repository.RoomPersistance.entity.Category;
import com.example.codingtaskfromevaly.Repository.RoomPersistance.entity.Products;

@Database(entities = {Category.class, Products.class}, version = 8,exportSchema = false)
public abstract  class EvalyDatabase extends RoomDatabase {
    private static EvalyDatabase instance;
    public abstract EvalyDao evalyDao();

    public static synchronized EvalyDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    EvalyDatabase.class, "evaly_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

        }
    };

}
