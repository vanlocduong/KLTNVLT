package com.example.vietdang.foodappversion2.view.login;

/**
 * Created by vietdang on 3/9/2018.
 */

public interface ILoginView {
    void showProgress();
    void hideProgress();
    void navigateMainActivity(Integer body);
    void showMessage(String message);
}
