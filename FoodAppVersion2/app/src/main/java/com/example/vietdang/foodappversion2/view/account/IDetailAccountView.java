package com.example.vietdang.foodappversion2.view.account;

import com.example.vietdang.foodappversion2.model.user.UserProfile;

/**
 * Created by vanlo on 3/21/2018.
 */

public interface IDetailAccountView {

    void showData(UserProfile detailUser);
    void showErrorLoadData(String message);

}
