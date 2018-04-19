package com.example.vietdang.foodappversion2.model.overview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by vietdang on 3/9/2018.
 */

public class OverView {
    @SerializedName("FoodName")
    @Expose
    private String FoodName;
    @SerializedName("DishTypeId")
    @Expose
    private String DishTypeId;
    @SerializedName("FoodNutritionVMs")
    @Expose
    private List<FoodNutritionVM> FoodNutritionVMs;

    public OverView(String foodName, String dishTypeId, List<FoodNutritionVM> foodNutritionVMs) {
        FoodName = foodName;
        DishTypeId = dishTypeId;
        FoodNutritionVMs = foodNutritionVMs;
    }

    public OverView() {
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getDishTypeId() {
        return DishTypeId;
    }

    public void setDishTypeId(String dishTypeId) {
        DishTypeId = dishTypeId;
    }

    public List<FoodNutritionVM> getFoodNutritionVMs() {
        return FoodNutritionVMs;
    }

    public void setFoodNutritionVMs(List<FoodNutritionVM> foodNutritionVMs) {
        FoodNutritionVMs = foodNutritionVMs;
    }
}
