package com.example.vietdang.foodappversion2.view.detailfood;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vietdang.foodappversion2.R;
import com.example.vietdang.foodappversion2.adapter.FoodNutritionDetailAdapter;
import com.example.vietdang.foodappversion2.model.detaifood.AddDishFood;
import com.example.vietdang.foodappversion2.model.detaifood.DetailFood;
import com.example.vietdang.foodappversion2.model.detaifood.NutritionItemsView;
import com.example.vietdang.foodappversion2.model.detaifood.NutritionTypeView;
import com.example.vietdang.foodappversion2.model.detaifood.NutrtionTypeHolder;
import com.example.vietdang.foodappversion2.presenter.detaifood.DetaiFoodPre;
import com.example.vietdang.foodappversion2.presenter.detaifood.IDetaiFoodPre;
import com.example.vietdang.foodappversion2.util.UtilDateTime;
import com.example.vietdang.foodappversion2.view.BaseFragment;
import com.example.vietdang.foodappversion2.view.searchfood.SearchFoodActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFoodFragment extends BaseFragment implements IDetailFoodView {

    String foodName = "", foodDes = "", FoodImage = "";
    int foodId=0,userId=0;
    //view
    private TextView txtFoodName, txtDescription;
    private ImageView imgFoodDetail;
    //
    private RecyclerView mRecyclerView;
    private TextView txtAddFoodForDishCurrent;
    private List<NutrtionTypeHolder> mNutritionViews = new ArrayList<>();
    //
    private IDetaiFoodPre mIDetaiFoodPresenter;

    public DetailFoodFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        foodName = getArguments().getString("FoodName");
        foodDes = getArguments().getString("FoodDes");
        FoodImage = getArguments().getString("FoodImg");
        foodId = getArguments().getInt("FoodId");
        SharedPreferences sharedPreferences= this.getActivity().getSharedPreferences("USer", Context.MODE_PRIVATE);
        if(sharedPreferences!= null) {
            userId = sharedPreferences.getInt("UserID", 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_food, container, false);
        txtFoodName = (TextView) view.findViewById(R.id.txt_detail_foodName);
        txtDescription = (TextView) view.findViewById(R.id.txt_detailfood_des);
        imgFoodDetail = (ImageView) view.findViewById(R.id.img_detailfood);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_nutrition_detailfood);
        txtAddFoodForDishCurrent = (TextView) view.findViewById(R.id.txt_addfood_detail_fragment);
        //
        Glide.with(getContext()).load(FoodImage)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgFoodDetail);
        txtFoodName.setText(foodName);
        txtDescription.setText(foodDes);
        mIDetaiFoodPresenter = new DetaiFoodPre(this);
        mIDetaiFoodPresenter.onLoadFoodHistorySuccess(foodId);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtAddFoodForDishCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialogAddFood(foodName);
            }
        });
    }

    private void showAlertDialogAddFood(String tenThucPham) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.alertdialog_addfood, null);
        final EditText edtValue=alertLayout.findViewById(R.id.edt_elert_value);
        //
        List<String> list = new ArrayList<>();
        list.add("Breakfast");
        list.add("Lunch");
        list.add("Dinner");
        list.add("Snack");

        ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, list);

        final Spinner spinner = (Spinner) alertLayout.findViewById(R.id.sp_alert_addfood);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle(tenThucPham);
        alert.setView(alertLayout);
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                AddDishFood dishFood=new AddDishFood(1,foodId,userId, UtilDateTime.getCurrentDate(),Float.valueOf(edtValue.getText().toString()),spinner.getSelectedItemPosition()+1);
                Log.d("Tagthu",dishFood.toString());
                mIDetaiFoodPresenter.AddDishFoodSuccess(dishFood);
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
    private Map<Integer, List<DetailFood>> toMap(@NonNull List<DetailFood> nutris) {
        Map<Integer, List<DetailFood>> map = new TreeMap<>();
        for (DetailFood nutri : nutris) {
            List<DetailFood> value = map.get(nutri.getNutritionTypeId());
            if (value == null) {
                value = new ArrayList<>();
                map.put(nutri.getNutritionTypeId(), value);
            }
            value.add(nutri);
        }
        return map;
    }

    @Override
    public void showProgress() {
        pd.show();
    }

    @Override
    public void hideProgress() {
        pd.dismiss();
    }

    @Override
    public void showData(List<DetailFood> detailFoods) {
        Map<Integer, List<DetailFood>> nutritions = toMap(detailFoods);
        for (Integer integer : nutritions.keySet()) {
            NutritionTypeView nt = new NutritionTypeView(integer, checkNutritionType(integer));
            mNutritionViews.add(nt);
            for (DetailFood foodDetail : nutritions.get(integer)) {
                NutritionItemsView item = new NutritionItemsView(foodDetail);
                mNutritionViews.add(item);
            }
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(new FoodNutritionDetailAdapter(mNutritionViews));
        hideProgress();
    }

    private String checkNutritionType(int id) {
        String type = "";
        switch (id) {
            case 1:
                type = "Các thành phần chính";
                break;
            case 2:
                type = "Đường tổng số và các đường đơn";
                break;
            case 3:
                type = "Các chất khoáng và vi khoáng";
                break;
            default:
                type = "Chưa phân loại";
        }
        return type;
    }

    @Override
    public void showErrorLoadData(String message) {
        hideProgress();
        ((SearchFoodActivity) getActivity()).ShowSnackbar(message);
    }

    @Override
    public void addDataOke(String message) {
        hideProgress();
        ((SearchFoodActivity) getActivity()).ShowSnackbar(message);
    }


}
