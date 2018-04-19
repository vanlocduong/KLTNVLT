package com.example.vietdang.foodappversion2.model.recommend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vietdang on 3/19/2018.
 */

public class SettingUserInfoRequest {
    @SerializedName("Weight")
    @Expose
    private float Weight;
    @SerializedName("Height")
    @Expose
    private float Height;
    @SerializedName("ActivityLevelID")
    @Expose
    private int ActivityLevelID;
    @SerializedName("UserId")
    @Expose
    private int UserId;
    @SerializedName("UpdateDate")
    @Expose
    private String UpdateDate;

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

    public int getActivityLevelID() {
        return ActivityLevelID;
    }

    public void setActivityLevelID(int activityLevelID) {
        ActivityLevelID = activityLevelID;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(String updateDate) {
        UpdateDate = updateDate;
    }
}
