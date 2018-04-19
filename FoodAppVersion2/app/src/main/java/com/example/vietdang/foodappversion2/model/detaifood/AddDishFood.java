package com.example.vietdang.foodappversion2.model.detaifood;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vietdang on 3/10/2018.
 */

public class AddDishFood {
    @SerializedName("FoodHistoryId")
    @Expose
    private int FoodHistoryId;
    @SerializedName("FoodId")
    @Expose
    private int FoodId;
    @SerializedName("UserId")
    @Expose
    private int UserId;
    @SerializedName("CreateDate")
    @Expose
    private String CreateDate;
    @SerializedName("Quantity")
    @Expose
    private float Quantity;
    @SerializedName("MealType")
    @Expose
    private int MealType;

    public AddDishFood() {
    }

    public AddDishFood(int foodHistoryId, int foodId, int userId, String createDate, float quantity, int mealType) {
        FoodHistoryId = foodHistoryId;
        FoodId = foodId;
        UserId = userId;
        CreateDate = createDate;
        Quantity = quantity;
        MealType = mealType;
    }

    public AddDishFood(int foodId, int userId, String createDate, float quantity, int mealType) {
        FoodId = foodId;
        UserId = userId;
        CreateDate = createDate;
        Quantity = quantity;
        MealType = mealType;
    }

    public int getFoodHistoryId() {
        return FoodHistoryId;
    }

    public void setFoodHistoryId(int foodHistoryId) {
        FoodHistoryId = foodHistoryId;
    }

    public int getFoodId() {
        return FoodId;
    }

    public void setFoodId(int foodId) {
        FoodId = foodId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public float getQuantity() {
        return Quantity;
    }

    public void setQuantity(float quantity) {
        Quantity = quantity;
    }

    public int getMealType() {
        return MealType;
    }

    public void setMealType(int mealType) {
        MealType = mealType;
    }
}
