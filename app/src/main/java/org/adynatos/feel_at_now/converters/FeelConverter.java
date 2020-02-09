package org.adynatos.feel_at_now.converters;

import androidx.room.TypeConverter;

import org.adynatos.feel_at_now.Feel;

import static org.adynatos.feel_at_now.Feel.HAPPY;
import static org.adynatos.feel_at_now.Feel.MEH;
import static org.adynatos.feel_at_now.Feel.SAD;

public class FeelConverter {
    @TypeConverter
    public Feel toFeel(final int value) {
        switch (value) {
            case -1: return SAD;
            case 0: return MEH;
            case 1: return HAPPY;
            default: return MEH;
        }
    }

    @TypeConverter
    public int toInt(final Feel data) {
        switch (data) {
            case SAD: return -1;
            case MEH: return 0;
            case HAPPY: return 1;
            default: return 0;
        }
    }
}
