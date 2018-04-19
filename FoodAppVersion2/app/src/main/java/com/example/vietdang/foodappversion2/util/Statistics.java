package com.example.vietdang.foodappversion2.util;

/**
 * Created by vietdang on 3/18/2018.
 */

public class Statistics {
    public static float ConvertBacsicCalories(int age, float heigt, float weight, int gender) {
        if (gender == 1) {
            return (float) (66.5 + (13.8 * weight) + ((5 * heigt) - (6.75 * age)));
        }
        return (float) (66.5 + (9.56 * weight) + ((1.85 * heigt) - (4.68 * age)));
    }

    public static float ConvertActivityLevel(float calories, int level) {
        if (level == 1)
            return (calories * 20) / 100;
        if (level == 2)
            return (calories * 30) / 100;
        if (level == 3)
            return (calories * 40) / 100;
        if (level == 4)
            return (calories * 50) / 100;
        return (calories * 10) / 100;
    }

    public static float ConvertCaloriesForFood(float caliries) {
        return (caliries * 10) / 100;
    }

    public static float ConvertLipidForFood(float protein, int age) {
        if (age <= 40) return protein;
        if (age <= 60) return (protein * 7) / 10;
        return protein / 2;
    }
}
