package com.example.vietdang.foodappversion2.presenter.recommend;

import android.util.Log;

import com.example.vietdang.foodappversion2.helper.APIService;
import com.example.vietdang.foodappversion2.helper.ApiUtil;
import com.example.vietdang.foodappversion2.model.recommend.SettingUserInfoRequest;
import com.example.vietdang.foodappversion2.view.recommend.IRecommendFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vietdang on 3/19/2018.
 */

public class RecommendPre implements IRecommendPre {
    private IRecommendFragment mRecommendFragment;
    private APIService mService;

    public RecommendPre(IRecommendFragment mRecommendFragment) {
        this.mRecommendFragment = mRecommendFragment;
        mService = ApiUtil.getAPIService();
    }
    @Override
    public void AddUserInfor(SettingUserInfoRequest userInfoRequest) {
        mService.addUserInfo(userInfoRequest).enqueue(new Callback<SettingUserInfoRequest>() {
            @Override
            public void onResponse(Call<SettingUserInfoRequest> call, Response<SettingUserInfoRequest> response) {
                if (response.isSuccessful()) {
                    if(response.body()!=null)
                        mRecommendFragment.onSettingSuccess("Add Oke");
                    else{
                        mRecommendFragment.onSettingError("Error");
                    }
                } else {
                    Log.d("Loi", "Loi");
                }
            }

            @Override
            public void onFailure(Call<SettingUserInfoRequest> call, Throwable t) {
                mRecommendFragment.onSettingError(t.toString());
            }
        });
    }
}
