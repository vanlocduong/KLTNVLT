package com.example.vietdang.foodappversion2.view.history;

import com.example.vietdang.foodappversion2.model.foodhistory.FoodHistory;

import java.util.List;

/**
 * Created by vietdang on 3/4/2018.
 */

public interface IFoodHistoryFragmentView {
    void showData(List<FoodHistory> listFoodHistories);
    void showErrorLoadData(String message);
}
