package com.example.vietdang.foodappversion2.model.overview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vietdang on 3/17/2018.
 */

public class UserBodyInfo {

    @SerializedName("Weight")
    @Expose
    private float Weight;
    @SerializedName("Height")
    @Expose
    private float Height;
    @SerializedName("BirthDay")
    @Expose
    private String BirthDay;
    @SerializedName("UpdateDate")
    @Expose
    private String UpdateDate;
    @SerializedName("UserBodyId")
    @Expose
    private int UserBodyId;
    @SerializedName("Gender")
    @Expose
    private int Gender;
    @SerializedName("ActivityLevelID")
    @Expose
    private int ActivityLevelID;

    public float getWeight() {
        return Weight;
    }

    public void setWeight(float weight) {
        Weight = weight;
    }

    public float getHeight() {
        return Height;
    }

    public void setHeight(float height) {
        Height = height;
    }

    public String getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(String birthDay) {
        BirthDay = birthDay;
    }

    public String getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(String updateDate) {
        UpdateDate = updateDate;
    }

    public int getUserBodyId() {
        return UserBodyId;
    }

    public void setUserBodyId(int userBodyId) {
        UserBodyId = userBodyId;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public int getActivityLevelID() {
        return ActivityLevelID;
    }

    public void setActivityLevelID(int activityLevelID) {
        ActivityLevelID = activityLevelID;
    }

    @Override
    public String toString() {
        return "UserBodyInfo{" +
                "Weight=" + Weight +
                '}';
    }
}
