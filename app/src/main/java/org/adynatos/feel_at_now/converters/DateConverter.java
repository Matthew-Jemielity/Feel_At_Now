package org.adynatos.feel_at_now.converters;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {
    @TypeConverter
    public Date toDate(final long timestamp) {
        return new Date(timestamp);
    }

    @TypeConverter
    public long toTimestamp(final Date date) {
        return date.getTime();
    }
}
