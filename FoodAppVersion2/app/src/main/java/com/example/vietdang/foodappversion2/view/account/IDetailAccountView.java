package com.example.vietdang.foodappversion2.view.account;

import com.example.vietdang.foodappversion2.model.user.UserProfile;
import com.example.vietdang.foodappversion2.model.user.UserProfile1;

/**
 * Created by vanlo on 3/21/2018.
 */

public interface IDetailAccountView {

    void showData(UserProfile1 detailUser);
    void showErrorLoadData(String message);

}
