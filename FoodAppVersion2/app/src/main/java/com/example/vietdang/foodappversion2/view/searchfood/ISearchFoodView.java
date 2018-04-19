package com.example.vietdang.foodappversion2.view.searchfood;

import com.example.vietdang.foodappversion2.model.searchfood.FoodSearch;

import java.util.List;

/**
 * Created by vietdang on 3/10/2018.
 */

public interface ISearchFoodView {
    void showData(List<FoodSearch> foodSearchList);
    void showErrorLoadData(String message);
}
