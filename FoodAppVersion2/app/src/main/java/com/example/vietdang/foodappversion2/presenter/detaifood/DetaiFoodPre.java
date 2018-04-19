package com.example.vietdang.foodappversion2.presenter.detaifood;

import com.example.vietdang.foodappversion2.helper.APIService;
import com.example.vietdang.foodappversion2.helper.ApiUtil;
import com.example.vietdang.foodappversion2.model.detaifood.AddDishFood;
import com.example.vietdang.foodappversion2.model.detaifood.DetailFood;
import com.example.vietdang.foodappversion2.view.detailfood.IDetailFoodView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vietdang on 3/10/2018.
 */

public class DetaiFoodPre implements IDetaiFoodPre{
    private IDetailFoodView mIDetailFoodView;
    private APIService mService;

    public DetaiFoodPre(IDetailFoodView mIDetailFoodView) {
        this.mIDetailFoodView = mIDetailFoodView;
        mService = ApiUtil.getAPIService();
    }
    @Override
    public void onLoadFoodHistorySuccess(int foodId) {
        mIDetailFoodView.showProgress();
        mService.getDetailFood(foodId).enqueue(new Callback<List<DetailFood>>() {
            @Override
            public void onResponse(Call<List<DetailFood>> call, Response<List<DetailFood>> response) {
                if(response.isSuccessful()){
                    if(response.body().size()!=0){
                        mIDetailFoodView.showData(response.body());
                    }else{
                        mIDetailFoodView.showErrorLoadData("Khong co du lieu");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<DetailFood>> call, Throwable t) {
                mIDetailFoodView.showErrorLoadData(t.toString());
            }
        });
    }

    @Override
    public void AddDishFoodSuccess(AddDishFood addDishFood) {
        mIDetailFoodView.showProgress();
        mService.addFoodForDish(addDishFood).enqueue(new Callback<AddDishFood>() {
            @Override
            public void onResponse(Call<AddDishFood> call, Response<AddDishFood> response) {
                if(response.code() == 201){
                    if(response.body()!=null){
                        mIDetailFoodView.addDataOke("Add thanh cong");
                    }else{
                        mIDetailFoodView.addDataOke("Add that bai");
                    }
                }else{
                    mIDetailFoodView.addDataOke("Add that bai");
                }
            }

            @Override
            public void onFailure(Call<AddDishFood> call, Throwable t) {
                mIDetailFoodView.addDataOke(t.toString());
            }
        });
    }
}
