package org.adynatos.feel_at_now;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "feel_data")
class FeelData {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "date")
    private final Date mDate;

    @ColumnInfo(name = "feel")
    @NonNull
    private final Feel mFeel;

    FeelData(final Date date, final Feel feel) {
        this.mDate = date;
        this.mFeel = feel;
    }

    Date date() {
        return this.mDate;
    }

    Feel feel() {
        return this.mFeel;
    }
}
