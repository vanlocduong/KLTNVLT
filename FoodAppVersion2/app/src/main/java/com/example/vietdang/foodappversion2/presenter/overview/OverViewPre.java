package com.example.vietdang.foodappversion2.presenter.overview;

import android.util.Log;

import com.example.vietdang.foodappversion2.helper.APIService;
import com.example.vietdang.foodappversion2.helper.ApiUtil;
import com.example.vietdang.foodappversion2.model.overview.Calorie;
import com.example.vietdang.foodappversion2.model.overview.OverViewNutrition;
import com.example.vietdang.foodappversion2.model.overview.UserBodyInfo;
import com.example.vietdang.foodappversion2.view.overview.IOverViewFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vietdang on 3/9/2018.
 */

public class OverViewPre implements IOverViewPre {
    private IOverViewFragment mIOverViewFragmentView;
    private APIService mService;

    public OverViewPre(IOverViewFragment mIOverViewFragmentView) {
        this.mIOverViewFragmentView = mIOverViewFragmentView;
        mService = ApiUtil.getAPIService();
    }

    @Override
    public void onLoadOverViewSuccess(String createDate, int userId) {
        mService.getAllNutri(createDate,userId).enqueue(new Callback<List<OverViewNutrition>>() {
            @Override
            public void onResponse(Call<List<OverViewNutrition>> call, Response<List<OverViewNutrition>> response) {
                if (response.isSuccessful()) {
                    if(response.body().size()!=0)
                        mIOverViewFragmentView.showData(response.body());
                    else{
                        mIOverViewFragmentView.showErrorLoadData("Khong co du lieu");
                    }
                } else {
                    Log.d("Loi", "Loi");
                }
            }

            @Override
            public void onFailure(Call<List<OverViewNutrition>> call, Throwable t) {
                mIOverViewFragmentView.showErrorLoadData("Khong co du lieu");
            }
        });
    }

    @Override
    public void onLoadOverViewNutrition(String createDate, int userId) {
        mService.GetOverViewNutrition(createDate,userId).enqueue(new Callback<List<OverViewNutrition>>() {
            @Override
            public void onResponse(Call<List<OverViewNutrition>> call, Response<List<OverViewNutrition>> response) {
                if (response.isSuccessful()) {
                    if(response.body().size()!=0)
                        mIOverViewFragmentView.showNutrition(response.body());
                    else{
                        mIOverViewFragmentView.showErrorLoadData("Khong co du lieu");
                    }
                } else {
                    Log.d("Loi", "Loi");
                }
            }

            @Override
            public void onFailure(Call<List<OverViewNutrition>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onLoadUserInfo(int userId) {
        mService.getUserInfo(userId).enqueue(new Callback<UserBodyInfo>() {
            @Override
            public void onResponse(Call<UserBodyInfo> call, Response<UserBodyInfo> response) {
                if (response.isSuccessful()) {
                    if(response.body()!=null)
                        mIOverViewFragmentView.showUserInfo(response.body());
                    else{
                        mIOverViewFragmentView.showErrorLoadData("Khong co du lieu");
                    }
                } else {
                    Log.d("Loi", "Loi");
                }
            }

            @Override
            public void onFailure(Call<UserBodyInfo> call, Throwable t) {
                mIOverViewFragmentView.showErrorLoadData("Khong co du lieu");
            }
        });
    }
}
