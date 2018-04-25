package com.example.vietdang.foodappversion2.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfile1 {
    @SerializedName("UserId")
    @Expose
    private Integer userId;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("PassWord")
    @Expose
    private String passWord;
    @SerializedName("FullName")
    @Expose
    private String fullName;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("BirthDay")
    @Expose
    private String birthDay;
    @SerializedName("Weight")
    @Expose
    private Integer weight;
    @SerializedName("Height")
    @Expose
    private Integer height;
    @SerializedName("ActivityLevelID")
    @Expose
    private Integer activityLevelID;
    @SerializedName("Gender")
    @Expose
    private Integer Gender;

    /**
     * No args constructor for use in serialization
     *
     */
    public UserProfile1() {
    }

    public UserProfile1(String userName, String passWord, String fullName, String address, String email, Integer weight, Integer height) {
        this.userName = userName;
        this.passWord = passWord;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.weight = weight;
        this.height = height;
    }

    public Integer getGender() {
        return Gender;
    }

    public void setGender(Integer gender) {
        Gender = gender;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getActivityLevelID() {
        return activityLevelID;
    }

    public void setActivityLevelID(Integer activityLevelID) {
        this.activityLevelID = activityLevelID;
    }
}
