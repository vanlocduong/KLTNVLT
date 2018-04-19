package com.example.vietdang.foodappversion2.presenter.history;

/**
 * Created by vietdang on 3/4/2018.
 */

public interface IFoodHistoryPre {
    void onLoadFoodHistorySuccess(String createDate,int userId);
    void DeleteFoodHistory(int foodHistoryId);
}
