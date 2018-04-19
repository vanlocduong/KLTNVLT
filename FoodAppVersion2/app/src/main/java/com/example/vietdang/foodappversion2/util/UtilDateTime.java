package com.example.vietdang.foodappversion2.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by vietdang on 3/9/2018.
 */

public class UtilDateTime {
    public static String getCurrentDate(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(Calendar.getInstance().getTime());
    }
}
