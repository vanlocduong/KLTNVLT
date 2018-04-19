package com.example.vietdang.foodappversion2.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by vietdang on 3/18/2018.
 */

public class UserInfoUtil {
    public static int getYearBirthDay(String birthDay) {
        int age = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Calendar c = Calendar.getInstance();
            int yearCurrent = c.get(Calendar.YEAR);
            Date date = formatter.parse(birthDay);
            c.setTime(date);
            int yearBirthDay = c.get(Calendar.YEAR);
            age = yearCurrent - yearBirthDay;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return age;
    }

    public static float getUserBMI(float weight, float height) {
        float heightkh = height / 100;
        return (weight / (heightkh * heightkh));
    }
    public static String getDiagnose(float bmi){
        if(bmi<18.5)return "Chỉ số BMI "+bmi+": Bạn thuộc dạng thiếu cân";
        if(bmi<22.9)return "Chỉ số BMI "+bmi+": Bạn có chỉ số BMI lý tưởng";
        if(bmi==23)return "Chỉ số BMI "+bmi+": Bạn thuộc dạng thừa cân";
        if(bmi<24.9)return "Chỉ số BMI "+bmi+": Bạn thuộc dang tiền béo phì";
        if(bmi<29.9)return "Chỉ số BMI "+bmi+": Bạn thuộc dang tiền béo phì độ I";
        if(bmi<30)return "Chỉ số BMI "+bmi+": Bạn thuộc dang tiền béo phì độ II";
        return "Chỉ số BMI "+bmi+": Bạn thuộc dang tiền béo phì độ III";
    }
}
