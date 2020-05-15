package com.hecheng.springbootquartz.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {
    public static String getDateTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(new Date());
    }
}
