package com.example.vietdang.foodappversion2.model.overview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vietdang on 3/19/2018.
 */

public class OverViewNutrition {
    @SerializedName("NutritionId")
    @Expose
    private int NutritionId;
    @SerializedName("NutritionName")
    @Expose
    private String NutritionName;
    @SerializedName("Quantity")
    @Expose
    private float Quantity;
    @SerializedName("Value")
    @Expose
    private float Value;
    @SerializedName("UnitName")
    @Expose
    private String UnitName;
    @SerializedName("MealType")
    @Expose
    private int MealType;

    public int getNutritionId() {
        return NutritionId;
    }

    public void setNutritionId(int nutritionId) {
        NutritionId = nutritionId;
    }

    public String getNutritionName() {
        return NutritionName;
    }

    public void setNutritionName(String nutritionName) {
        NutritionName = nutritionName;
    }

    public float getQuantity() {
        return Quantity;
    }

    public void setQuantity(float quantity) {
        Quantity = quantity;
    }

    public float getValue() {
        return Value;
    }

    public void setValue(float value) {
        Value = value;
    }

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    public int getMealType() {
        return MealType;
    }

    public void setMealType(int mealType) {
        MealType = mealType;
    }
}
