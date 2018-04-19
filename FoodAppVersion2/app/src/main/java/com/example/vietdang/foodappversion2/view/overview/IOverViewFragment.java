package com.example.vietdang.foodappversion2.view.overview;

import com.example.vietdang.foodappversion2.model.overview.OverViewNutrition;
import com.example.vietdang.foodappversion2.model.overview.UserBodyInfo;

import java.util.List;

/**
 * Created by vietdang on 3/9/2018.
 */

public interface IOverViewFragment {
    void showData(List<OverViewNutrition> nutritionList);
    void showNutrition(List<OverViewNutrition>overViewNutritions);
    void showUserInfo(UserBodyInfo userBodyInfo);
    void showErrorLoadData(String message);
}
