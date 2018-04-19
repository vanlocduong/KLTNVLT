package com.example.vietdang.foodappversion2.presenter.history;

import android.util.Log;

import com.example.vietdang.foodappversion2.helper.APIService;
import com.example.vietdang.foodappversion2.helper.ApiUtil;
import com.example.vietdang.foodappversion2.model.foodhistory.FoodHistory;
import com.example.vietdang.foodappversion2.view.history.IFoodHistoryFragmentView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vietdang on 3/4/2018.
 */

public class FoodHistoryPre implements IFoodHistoryPre {
    private IFoodHistoryFragmentView mIFoodHistoryView;
    private APIService mService;

    public FoodHistoryPre(IFoodHistoryFragmentView IFoodHistoryView) {
        mIFoodHistoryView = IFoodHistoryView;
        mService = ApiUtil.getAPIService();
    }

    @Override
    public void onLoadFoodHistorySuccess(String createDate, int userId) {
        mService.getFoodHistory(createDate, userId).enqueue(new Callback<List<FoodHistory>>() {
            @Override
            public void onResponse(Call<List<FoodHistory>> call, Response<List<FoodHistory>> response) {
                if (response.isSuccessful()) {
                    if (response.body().size() != 0)
                        mIFoodHistoryView.showData(response.body());
                    else {
                        mIFoodHistoryView.showErrorLoadData("Khong co du lieu");
                    }
                } else {
                    Log.d("Loi", "Loi");
                }
            }

            @Override
            public void onFailure(Call<List<FoodHistory>> call, Throwable t) {
                Log.d("Loi", t.toString());
            }
        });
    }

    @Override
    public void DeleteFoodHistory(int foodHistoryId) {
        mService.deleteFoodHistory(foodHistoryId).enqueue(new Callback<FoodHistory>() {
            @Override
            public void onResponse(Call<FoodHistory> call, Response<FoodHistory> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        mIFoodHistoryView.showErrorLoadData("Oke");
                    } else {
                        mIFoodHistoryView.showErrorLoadData("Error");
                    }
                }
            }

            @Override
            public void onFailure(Call<FoodHistory> call, Throwable t) {
                mIFoodHistoryView.showErrorLoadData("Error");
            }
        });
    }
}
