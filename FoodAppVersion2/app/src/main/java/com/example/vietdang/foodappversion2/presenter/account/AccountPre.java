package com.example.vietdang.foodappversion2.presenter.account;

import android.util.Log;

import com.example.vietdang.foodappversion2.helper.APIService;
import com.example.vietdang.foodappversion2.helper.ApiUtil;
import com.example.vietdang.foodappversion2.model.user.UserProfile;
import com.example.vietdang.foodappversion2.view.account.IDetailAccountView;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by vanlo on 3/20/2018.
 */

public class AccountPre implements IAccountPre {
    private IDetailAccountView mIDetailAccountView;
    private APIService mService;

    public AccountPre(IDetailAccountView mIDetailAccountView) {
        this.mIDetailAccountView = mIDetailAccountView;
        mService = ApiUtil.getAPIService();
    }


    @Override
    public void onLoadAccountSuccess(int foodId) {
        mService.getInForUser(foodId).enqueue(new retrofit2.Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        mIDetailAccountView.showData(response.body());
                        mIDetailAccountView.showErrorLoadData("Da co du lieu");
                    }else {
                        mIDetailAccountView.showErrorLoadData("khonng co du lieu");
                    }
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                mIDetailAccountView.showErrorLoadData(t.getMessage());
            }
        });
        Log.d("run", "du lieu chua chay qua day");
    }

    @Override
    public void onLoadAccountError() {

    }
}
