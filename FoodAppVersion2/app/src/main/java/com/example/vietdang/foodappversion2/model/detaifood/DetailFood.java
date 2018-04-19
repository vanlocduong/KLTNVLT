package com.example.vietdang.foodappversion2.model.detaifood;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vietdang on 3/10/2018.
 */

public class DetailFood {
    @SerializedName("NutritionName")
    @Expose
    private String NutritionName;
    @SerializedName("NutritionValue")
    @Expose
    private float NutritionValue;
    @SerializedName("NutritionUnit")
    @Expose
    private String NutritionUnit;
    @SerializedName("NutritionTypeId")
    @Expose
    private int NutritionTypeId;

    public DetailFood(String nutritionName, float nutritionValue, String nutritionUnit, int nutritionTypeId) {
        NutritionName = nutritionName;
        NutritionValue = nutritionValue;
        NutritionUnit = nutritionUnit;
        NutritionTypeId = nutritionTypeId;
    }

    public DetailFood() {
    }

    public String getNutritionName() {
        return NutritionName;
    }

    public void setNutritionName(String nutritionName) {
        NutritionName = nutritionName;
    }

    public float getNutritionValue() {
        return NutritionValue;
    }

    public void setNutritionValue(float nutritionValue) {
        NutritionValue = nutritionValue;
    }

    public String getNutritionUnit() {
        return NutritionUnit;
    }

    public void setNutritionUnit(String nutritionUnit) {
        NutritionUnit = nutritionUnit;
    }

    public int getNutritionTypeId() {
        return NutritionTypeId;
    }

    public void setNutritionTypeId(int nutritionTypeId) {
        NutritionTypeId = nutritionTypeId;
    }
}
