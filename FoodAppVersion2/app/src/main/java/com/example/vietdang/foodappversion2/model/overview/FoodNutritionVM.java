package com.example.vietdang.foodappversion2.model.overview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vietdang on 3/9/2018.
 */

public class FoodNutritionVM {
    @SerializedName("NutritionName")
    @Expose
    private String NutritionName;
    @SerializedName("NutritionValue")
    @Expose
    private String NutritionValue;
    @SerializedName("NutritionUnit")
    @Expose
    private String NutritionUnit;
    @SerializedName("NutritionTypeId")
    @Expose
    private String NutritionTypeId;

    public FoodNutritionVM(String nutritionName, String nutritionValue, String nutritionUnit, String nutritionTypeId) {
        NutritionName = nutritionName;
        NutritionValue = nutritionValue;
        NutritionUnit = nutritionUnit;
        NutritionTypeId = nutritionTypeId;
    }

    public FoodNutritionVM() {
    }

    public String getNutritionName() {
        return NutritionName;
    }

    public void setNutritionName(String nutritionName) {
        NutritionName = nutritionName;
    }

    public String getNutritionValue() {
        return NutritionValue;
    }

    public void setNutritionValue(String nutritionValue) {
        NutritionValue = nutritionValue;
    }

    public String getNutritionUnit() {
        return NutritionUnit;
    }

    public void setNutritionUnit(String nutritionUnit) {
        NutritionUnit = nutritionUnit;
    }

    public String getNutritionTypeId() {
        return NutritionTypeId;
    }

    public void setNutritionTypeId(String nutritionTypeId) {
        NutritionTypeId = nutritionTypeId;
    }
}
