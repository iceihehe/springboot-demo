package com.iceihehe.cm.utils.processor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeProcessor {
    public static Long process(Date date) {
        if (date == null) {
            return null;
        }
        return date.getTime();
    }

    public static String formatDate(Long date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:S");
        return simpleDateFormat.format(date);
    }
}
