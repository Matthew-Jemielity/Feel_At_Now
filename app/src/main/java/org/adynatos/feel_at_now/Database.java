package org.adynatos.feel_at_now;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import org.adynatos.feel_at_now.converters.DateConverter;
import org.adynatos.feel_at_now.converters.FeelConverter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@androidx.room.Database(entities = {FeelData.class}, version = 1)
@TypeConverters({DateConverter.class, FeelConverter.class})
abstract class Database extends RoomDatabase {
    private static volatile Database INSTANCE;

    private static final int NUMBER_OF_THREADS = 2;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static final String DB_NAME = "feel.db";

    abstract Dao dao();

    static Database get(final Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    Database.class,
                                    DB_NAME
                            )
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
