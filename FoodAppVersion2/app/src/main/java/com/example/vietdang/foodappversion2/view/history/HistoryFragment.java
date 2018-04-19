package com.example.vietdang.foodappversion2.view.history;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vietdang.foodappversion2.R;
import com.example.vietdang.foodappversion2.adapter.RecyclerViewFoodHistoryAdapter;
import com.example.vietdang.foodappversion2.model.foodhistory.FoodHistory;
import com.example.vietdang.foodappversion2.presenter.history.FoodHistoryPre;
import com.example.vietdang.foodappversion2.presenter.history.IFoodHistoryPre;
import com.example.vietdang.foodappversion2.util.UtilDateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HistoryFragment extends Fragment implements IFoodHistoryFragmentView{
    private static final String STATE_LIST = "ListData";

    private RecyclerView recyclerView_breakfast, recyclerView_lunch, recyclerView_dinner, recyclerView_snack;
    //
//    private List<FoodHistory> listFoodHistory= Collections.emptyList();

    //
    private LinearLayoutManager mLinearLayoutManager;

    //
    private IFoodHistoryPre mIFoodHistoryPre;
    //chon ngay de xem
    private TextView txtChooseDay;

    //xem history ry theo userID
    private int userID=0;
    public HistoryFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView_breakfast = (RecyclerView) view.findViewById(R.id.recyclerView_history);
        recyclerView_lunch=(RecyclerView)view.findViewById(R.id.recyclerView_history_lunch);
        recyclerView_dinner=(RecyclerView)view.findViewById(R.id.recyclerView_history_dinner);
        recyclerView_snack=(RecyclerView)view.findViewById(R.id.recyclerView_history_snack);
        userID = getArguments().getInt("UserID");
        //chon ngay
        txtChooseDay = (TextView) view.findViewById(R.id.txtchoose_day_history);


//        addlistFood();
        //lay thong tin user ID

//        SharedPreferences sharedPreferences= this.getActivity().getSharedPreferences("USer", Context.MODE_PRIVATE);
//
//        if(sharedPreferences!= null) {
//
//            userID = sharedPreferences.getInt("UserID", 0);
//        }

        mIFoodHistoryPre=new FoodHistoryPre(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIFoodHistoryPre.onLoadFoodHistorySuccess(UtilDateTime.getCurrentDate(),userID);
        txtChooseDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    dialogDatePicker();
                } else {
                    dialogSlectDate();
                }
            }
        });
    }

    private void setupRecyler(RecyclerView recyclerView, List<FoodHistory>list) {
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        RecyclerViewFoodHistoryAdapter mAdapter = new RecyclerViewFoodHistoryAdapter(getContext(), list,mIFoodHistoryPre);
        recyclerView.setAdapter(mAdapter);
    }
    @Override
    public void showData(List<FoodHistory> listFoodHistories) {
//        Toast.makeText(getContext(), "size: "+listFoodHistories.size(), Toast.LENGTH_SHORT).show();
//        listFoodHistory=listFoodHistories;
         List<FoodHistory> listBreakfast=new ArrayList<>();
         List<FoodHistory>listLunch=new ArrayList<>();
         List<FoodHistory>listDinner=new ArrayList<>();
         List<FoodHistory>listSnack=new ArrayList<>();
        for (FoodHistory foodHisttory :listFoodHistories){
            if(foodHisttory.getMealType()==1){
                listBreakfast.add(foodHisttory);
            }
            if(foodHisttory.getMealType()==2){
                listLunch.add(foodHisttory);
            }
            if(foodHisttory.getMealType()==3){
                listDinner.add(foodHisttory);
            }
            if(foodHisttory.getMealType()==4){
                listSnack.add(foodHisttory);
            }
        }
        setupRecyler(recyclerView_breakfast,listBreakfast);
        setupRecyler(recyclerView_lunch,listLunch);
        setupRecyler(recyclerView_dinner,listDinner);
        setupRecyler(recyclerView_snack,listSnack);
        listFoodHistories.clear();
//        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorLoadData(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void dialogDatePicker() {
//        setEmptyAllList();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layout_custom_datepicker, null);
        DatePicker datePicker = (DatePicker) alertLayout.findViewById(R.id.datePicker);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(calendar.YEAR);
        final int month = calendar.get(calendar.MONTH);
        final int day = calendar.get(calendar.DAY_OF_MONTH);
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(alertLayout);
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("Xem", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                txtChooseDay.setText(year + "-" + (month + 1) + "-" + day);
                mIFoodHistoryPre.onLoadFoodHistorySuccess(year + "-" + (month + 1) + "-" + day,userID);

            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    private void dialogSlectDate() {
//        setEmptyAllList();
        Calendar getTimeNow = Calendar.getInstance();
        DatePickerDialog date = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtChooseDay.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        mIFoodHistoryPre.onLoadFoodHistorySuccess(txtChooseDay.getText().toString(),userID);
                    }
                },
                getTimeNow.get(Calendar.YEAR),
                getTimeNow.get(Calendar.MONTH),
                getTimeNow.get(Calendar.DAY_OF_MONTH));
        date.show();
    }
//    private void setEmptyAllList(){
//        listFoodHistory.clear();
//        listBreakfast.clear();
//        listDinner.clear();
//        listLunch.clear();
//        listSnack.clear();
//    }
}
