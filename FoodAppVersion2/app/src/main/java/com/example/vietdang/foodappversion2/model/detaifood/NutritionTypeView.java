package com.example.vietdang.foodappversion2.model.detaifood;


/**
 * Created by vietdang on 10/22/2017.
 */

public class NutritionTypeView extends NutrtionTypeHolder {
    private int nutritionTypeId;
    private String nutritionType;

    public NutritionTypeView(int nutritionTypeId, String nutritionType) {
        this.nutritionTypeId = nutritionTypeId;
        this.nutritionType = nutritionType;
    }

    public int getNutritionTypeId() {
        return nutritionTypeId;
    }

    public String getNutritionType() {
        return nutritionType;
    }

    @Override
    public int getType() {
        return TYPE_GROUP;
    }
}
