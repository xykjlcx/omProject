package com.xykj.ombase.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期时间工具类
 */
public class OceanDateUtil {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String converDate(long time){
        Date date = new Date(time);
        return format.format(date);
    }

    /**
     * 获取当前时间字符串
     * @return
     */
    public static String getNowTimeForString(){
        return converDate(System.currentTimeMillis());
    }


    /**
     * 获取当前时间戳
     * @return
     */
    public static Long getCurrentTime() {
        return System.currentTimeMillis();
    }


}
