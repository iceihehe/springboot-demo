package com.iceihehe.cm.utils.processor;

import java.util.Date;

public class TimeProcessor {
    public static Long process(Date date) {
        if (date == null) {
            return null;
        }
        return date.getTime();
    }
}
