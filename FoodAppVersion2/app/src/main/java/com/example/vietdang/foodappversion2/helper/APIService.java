package com.example.vietdang.foodappversion2.helper;


import com.example.vietdang.foodappversion2.model.detaifood.AddDishFood;
import com.example.vietdang.foodappversion2.model.detaifood.DetailFood;
import com.example.vietdang.foodappversion2.model.foodhistory.FoodHistory;
import com.example.vietdang.foodappversion2.model.login.Login;
import com.example.vietdang.foodappversion2.model.overview.OverViewNutrition;
import com.example.vietdang.foodappversion2.model.overview.UserBodyInfo;
import com.example.vietdang.foodappversion2.model.recommend.SettingUserInfoRequest;
import com.example.vietdang.foodappversion2.model.searchfood.FoodSearch;
import com.example.vietdang.foodappversion2.model.user.UserProfile;
import com.example.vietdang.foodappversion2.model.user.UserProfile1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by PaulDuong on 17/09/2017.
 */

public interface APIService {
    @POST("api/account/login")
    Call<Integer> Login(@Body Login login);
    //overview
    @GET("api/overview/getall")
    Call<List<OverViewNutrition>> getAllNutri(@Query("createDate") String createDate, @Query("userId") int userId);

    @GET("api/overview/getall")
    Call<List<OverViewNutrition>> GetOverViewNutrition(@Query("createDate") String createDate, @Query("userId") int userId);
    //recommend
    @POST("api/recommend/add")
    Call<SettingUserInfoRequest> addUserInfo(@Body SettingUserInfoRequest settingUserInfoRequest);

    //history
    @GET("api/history/getall")
    Call<List<FoodHistory>> getFoodHistory(@Query("createDate") String createDate, @Query("userId") int userId);

    @DELETE("api/history/delete")
    Call<FoodHistory> deleteFoodHistory(@Query("id") int id);

    //account
    //search
    @GET("api/food/searchbyname")
    Call<List<FoodSearch>> getFoodSearch(@Query("foodName") String foodName);

    //detail
    @GET("api/food/fooddetail")
    Call<List<DetailFood>> getDetailFood(@Query("idFood") int idFood);


    @GET("api/overview/getuser")
    Call<UserBodyInfo> getUserInfo(@Query("userId") int userId);

    @POST("api/history/add")
    Call<AddDishFood> addFoodForDish(@Body AddDishFood addDishFood);



    @GET("api/account/detail/{id}")
    Call<UserProfile> getInForUser(@Path("id") int UserId);

    @GET("api/account/detail/{id}")
    Call<UserProfile1> getInForUser1(@Path("id") int UserId);

    @POST("api/account/add")
    Call<UserProfile> signUp(@Body UserProfile addUser);

    @PUT("api/account/update")
    Call<UserProfile> updateUser(@Body UserProfile userProfile);

}
