package com.example.vietdang.foodappversion2.presenter.login;

import android.util.Log;

import com.example.vietdang.foodappversion2.helper.APIService;
import com.example.vietdang.foodappversion2.helper.ApiUtil;
import com.example.vietdang.foodappversion2.model.login.Login;
import com.example.vietdang.foodappversion2.view.login.ILoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vietdang on 3/9/2018.
 */

public class LoginPresenter implements ILoginPresenter {
    private ILoginView mILoginView;
    private APIService mService;
    private static final String TAG = "ListFoodPresenter";

    public LoginPresenter(ILoginView mILoginView) {
        this.mILoginView = mILoginView;
        mService = ApiUtil.getAPIService();
    }
    @Override
    public void login(Login login) {
        mILoginView.showProgress();
        mService.Login(login).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    if(Integer.valueOf(response.body())!=0)
                    mILoginView.navigateMainActivity(response.body());
                    else{
                        mILoginView.showMessage("Tai khoan hoac mat khau khong dung");
                    }
                } else {
                    Log.d(TAG, "Loi");
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                mILoginView.showMessage(t.toString());
            }
        });
    }
}
