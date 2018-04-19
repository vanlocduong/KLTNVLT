package com.example.vietdang.foodappversion2.presenter.foodsearch;

import com.example.vietdang.foodappversion2.helper.APIService;
import com.example.vietdang.foodappversion2.helper.ApiUtil;
import com.example.vietdang.foodappversion2.model.searchfood.FoodSearch;
import com.example.vietdang.foodappversion2.view.searchfood.ISearchFoodView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vietdang on 3/10/2018.
 */

public class FoodSearchPre implements IFoodSearchPre{
    private ISearchFoodView mIFoodHistoryView;
    private APIService mService;

    public FoodSearchPre(ISearchFoodView mIFoodHistoryView) {
        this.mIFoodHistoryView = mIFoodHistoryView;
        mService = ApiUtil.getAPIService();
    }
    @Override
    public void onLoadFoodHistorySuccess(String foodName) {
        mService.getFoodSearch(foodName).enqueue(new Callback<List<FoodSearch>>() {
            @Override
            public void onResponse(Call<List<FoodSearch>> call, Response<List<FoodSearch>> response) {
                if(response.isSuccessful()){
                    if(response.body().size()!=0){
                        mIFoodHistoryView.showData(response.body());
                    }else{
                        mIFoodHistoryView.showErrorLoadData("Khong tim thay");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<FoodSearch>> call, Throwable t) {
                mIFoodHistoryView.showErrorLoadData(t.toString());
            }
        });
    }
}
