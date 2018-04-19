package com.example.vietdang.foodappversion2.model.overview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vietdang on 3/9/2018.
 */

public class Calorie {
    @SerializedName("MealTypeID")
    @Expose
    private int MealTypeID;
    @SerializedName("Calorie")
    @Expose
    private float Calorie;

    public Calorie(int mealTypeID, float calorie) {
        MealTypeID = mealTypeID;
        Calorie = calorie;
    }

    public Calorie() {
    }

    public int getMealTypeID() {
        return MealTypeID;
    }

    public void setMealTypeID(int mealTypeID) {
        MealTypeID = mealTypeID;
    }

    public float getCalorie() {
        return Calorie;
    }

    public void setCalorie(float calorie) {
        Calorie = calorie;
    }
}
