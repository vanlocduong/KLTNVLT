package com.example.vietdang.foodappversion2.presenter.overview;

/**
 * Created by vietdang on 3/9/2018.
 */

public interface IOverViewPre {
    void onLoadOverViewSuccess(String createDate,int userId);
    void onLoadOverViewNutrition(String createDate,int userId);
    void onLoadUserInfo(int userId);
}
