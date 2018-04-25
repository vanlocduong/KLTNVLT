package com.example.vietdang.foodappversion2.presenter.update_user;

import com.example.vietdang.foodappversion2.helper.APIService;
import com.example.vietdang.foodappversion2.helper.ApiUtil;
import com.example.vietdang.foodappversion2.model.user.UserProfile;
import com.example.vietdang.foodappversion2.view.update_user.IUpdateUserView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpdateUserPresenter implements IUpdateUserPresenter {

    private IUpdateUserView iUpdateUserView;
    private APIService apiService;

    public UpdateUserPresenter(IUpdateUserView iUpdateUserView){
        this.iUpdateUserView = iUpdateUserView;
        apiService= ApiUtil.getAPIService();

    }

    @Override
    public void UpdateUserSucess(UserProfile userProfile) {
        apiService.updateUser(userProfile).enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()){
                    iUpdateUserView.updateUserSucess("add thanh cong");
                }else {
                    iUpdateUserView.updateUserSucess("add that bai");
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });

    }
}
