package com.example.vietdang.foodappversion2.model.searchfood;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vietdang on 3/10/2018.
 */

public class FoodSearch {
    @SerializedName("FoodId")
    @Expose
    private int FoodId;
    @SerializedName("FoodName")
    @Expose
    private String FoodName;
    @SerializedName("FoodDescription")
    @Expose
    private String FoodDescription;
    @SerializedName("FoodImages")
    @Expose
    private String FoodImages;

    public FoodSearch(int foodId, String foodName, String foodDescription, String foodImages) {
        FoodId = foodId;
        FoodName = foodName;
        FoodDescription = foodDescription;
        FoodImages = foodImages;
    }

    public FoodSearch() {
    }

    public int getFoodId() {
        return FoodId;
    }

    public void setFoodId(int foodId) {
        FoodId = foodId;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getFoodDescription() {
        return FoodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        FoodDescription = foodDescription;
    }

    public String getFoodImages() {
        return FoodImages;
    }

    public void setFoodImages(String foodImages) {
        FoodImages = foodImages;
    }
}
