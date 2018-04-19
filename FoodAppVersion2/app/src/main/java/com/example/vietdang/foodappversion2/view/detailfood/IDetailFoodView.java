package com.example.vietdang.foodappversion2.view.detailfood;

import com.example.vietdang.foodappversion2.model.detaifood.DetailFood;

import java.util.List;

/**
 * Created by vietdang on 3/10/2018.
 */

public interface IDetailFoodView {
    void showProgress();
    void hideProgress();
    void showData(List<DetailFood> detailFoods);
    void showErrorLoadData(String message);
    void addDataOke(String message);
}
