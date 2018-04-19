package com.example.vietdang.foodappversion2.model.foodhistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vietdang on 12/21/2017.
 */

public class FoodHistory{
    @SerializedName("FoodName")
    @Expose
    private String FoodName;
    @SerializedName("Quantity")
    @Expose
    private float Quantity;
    @SerializedName("FoodImages")
    @Expose
    private String FoodImages;
    @SerializedName("FoodHistoryId")
    @Expose
    private int FoodHistoryId;
    @SerializedName("MealType")
    @Expose
    private int MealType;

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public float getQuantity() {
        return Quantity;
    }

    public void setQuantity(float quantity) {
        Quantity = quantity;
    }

    public String getFoodImages() {
        return FoodImages;
    }

    public void setFoodImages(String foodImages) {
        FoodImages = foodImages;
    }

    public int getFoodHistoryId() {
        return FoodHistoryId;
    }

    public void setFoodHistoryId(int foodHistoryId) {
        FoodHistoryId = foodHistoryId;
    }

    public int getMealType() {
        return MealType;
    }

    public void setMealType(int mealType) {
        MealType = mealType;
    }
}
