package com.example.vietdang.foodappversion2.model.detaifood;

/**
 * Created by vietdang on 10/22/2017.
 */

public class NutritionItemsView extends NutrtionTypeHolder{
    private DetailFood mFoodDetail;

    public DetailFood getFoodDetail() {
        return mFoodDetail;
    }

    public NutritionItemsView(DetailFood foodDetail) {

        mFoodDetail = foodDetail;
    }

    @Override
    public int getType() {
        return TYPE_ITEM;
    }
}
