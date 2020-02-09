package org.adynatos.feel_at_now;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@androidx.room.Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void put(FeelData data);

    @Query("SELECT * FROM feel_data")
    List<FeelData> get();
}
