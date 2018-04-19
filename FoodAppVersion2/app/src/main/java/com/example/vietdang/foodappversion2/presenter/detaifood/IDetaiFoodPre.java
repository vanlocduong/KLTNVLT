package com.example.vietdang.foodappversion2.presenter.detaifood;

import com.example.vietdang.foodappversion2.model.detaifood.AddDishFood;

/**
 * Created by vietdang on 3/10/2018.
 */

public interface IDetaiFoodPre {
   void onLoadFoodHistorySuccess(int foodId);
   void AddDishFoodSuccess(AddDishFood addDishFood);
}
