package com.example.vietdang.foodappversion2.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vietdang on 3/9/2018.
 */

public class Login {
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("passWord")
    @Expose
    private String passWord;

    public Login(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}

