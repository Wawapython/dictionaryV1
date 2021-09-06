package com.example.dictionary1;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {
    private static WordDatabase  INSTANCE;
    static synchronized WordDatabase getDatabase(Context context){
        if (INSTANCE== null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(), WordDatabase.class, "word for Database")
                    .addMigrations(MIGRATION_1_2)
                    .build();
        }
        return  INSTANCE;
    }
    public abstract WordDao getWordDao();

    //版本由1~2進行遷移
        static final Migration MIGRATION_1_2 = new Migration(1,2) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                database.execSQL("ALTER TABLE visible ADD COLUMN chinese_invisible INTEGER NOT NULL DEFAULT 0");
            }
        };

}