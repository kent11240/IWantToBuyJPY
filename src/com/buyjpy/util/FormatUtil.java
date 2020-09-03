package com.buyjpy.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtil {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
    private static final DecimalFormat df = new DecimalFormat("#0.0000");

    public static String formatDateTime(Date date) {
        return sdf.format(date);
    }

    public static String formatTime(Date time) {
        return sdf2.format(time);
    }

    public static String formatNumber(double num) {
        return df.format(num);
    }
}
