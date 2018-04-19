package com.example.vietdang.foodappversion2.presenter.signup;

import com.example.vietdang.foodappversion2.helper.APIService;
import com.example.vietdang.foodappversion2.helper.ApiUtil;
import com.example.vietdang.foodappversion2.model.user.UserProfile;
import com.example.vietdang.foodappversion2.view.signup.ISignupView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vanlo on 3/25/2018.
 */

public class SignupPre implements ISignupPre {

    private ISignupView iSignupView;
    private APIService apiService;
    public SignupPre(ISignupView iSignupView){
        this.iSignupView= iSignupView;
        apiService= ApiUtil.getAPIService();
    }

    @Override
    public void AddUserSuccess(UserProfile userProfile) {
        apiService.signUp(userProfile).enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()){
                    iSignupView.addUserSuccess("add thanh cong");
                }else {
                    iSignupView.addUserSuccess("add that bai");
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });

    }
}
