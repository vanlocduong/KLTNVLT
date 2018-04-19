package com.example.vietdang.foodappversion2.view.overview;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vietdang.foodappversion2.R;
import com.example.vietdang.foodappversion2.model.overview.OverViewNutrition;
import com.example.vietdang.foodappversion2.model.overview.UserBodyInfo;
import com.example.vietdang.foodappversion2.presenter.overview.IOverViewPre;
import com.example.vietdang.foodappversion2.presenter.overview.OverViewPre;
import com.example.vietdang.foodappversion2.util.MyAxisValueFormatter;
import com.example.vietdang.foodappversion2.util.MyValueFormatter;
import com.example.vietdang.foodappversion2.util.Statistics;
import com.example.vietdang.foodappversion2.util.UserInfoUtil;
import com.example.vietdang.foodappversion2.util.UtilDateTime;
import com.example.vietdang.foodappversion2.view.BaseFragment;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OverViewFragment extends BaseFragment implements IOverViewFragment {

    //view
//    private PieChartView pieChartView;
    private BarChart mBarChart;
    private TextView tvCanhbaodd;

    private TextView tvCaloriesused, tvCaloriesusnc;
    private TextView tvProteinused, tvProteinnc;
    private TextView tvLipidused, tvLipidnc;
    private TextView tvGlucidused, tvGlucidnc;
    //
    private IOverViewPre mIOverViewPre;
    private int userID = 0;

    private float caloriesUsed=0;

    public OverViewFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIOverViewPre = new OverViewPre(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_over_view, container, false);
//        pieChartView = (PieChartView)view.findViewById(R.id.pie_chart);
        //chart
        mBarChart = (BarChart) view.findViewById(R.id.chart1);
        mBarChart.setMaxVisibleValueCount(40);
        //
        tvCanhbaodd = (TextView) view.findViewById(R.id.tv_canhbaodd);
        tvCaloriesused = (TextView) view.findViewById(R.id.txt_overview_calories_used);
        tvCaloriesusnc = (TextView) view.findViewById(R.id.txt_overview_calories_nc);
        tvProteinused = (TextView) view.findViewById(R.id.txt_overview_protein_used);
        tvProteinnc = (TextView) view.findViewById(R.id.txt_overview_protein_nc);
        tvLipidused = (TextView) view.findViewById(R.id.txt_overview_lipid_used);
        tvLipidnc = (TextView) view.findViewById(R.id.txt_overview_lipid_nc);
        tvGlucidused = (TextView) view.findViewById(R.id.txt_overview_glucid_used);
        tvGlucidnc = (TextView) view.findViewById(R.id.txt_overview_glucid_nc);
        //
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userID = getArguments().getInt("UserID");
        mIOverViewPre.onLoadUserInfo(userID);
        mIOverViewPre.onLoadOverViewNutrition(UtilDateTime.getCurrentDate(), userID);
        mIOverViewPre.onLoadOverViewSuccess(UtilDateTime.getCurrentDate(), userID);
    }

    @Override
    public void showData(List<OverViewNutrition> nutritionList) {
//        Toast.makeText(getActivity(), calorieList.size()+"", Toast.LENGTH_SHORT).show();
        float calo_brf = 0, calo_lun = 0, calo_din = 0, calo_sna = 0;
//        ArrayList<BarEntry> yvalues = new ArrayList<>();
        int i = 0;
//        for (OverViewNutrition overViewNutrition :nutritionList){
//            if(overViewNutrition.getMealTypeID()==1){
//                calo_brf+=calorie.getCalorie();
//            }
//            if(calorie.getMealTypeID()==2){
//                calo_lun+=calorie.getCalorie();
//            }
//            if(calorie.getMealTypeID()==3){
//                calo_din+=calorie.getCalorie();
//            }
//            if(calorie.getMealTypeID()==4){
//                calo_sna+=calorie.getCalorie();
//            }
//            i++;
//            yvalues.add(new BarEntry(i, new float[]{calo_brf,calo_lun,calo_din,calo_sna}));
//        }
//        setData(yvalues);
        setData1(4,nutritionList);
        tvCanhbaodd.setText("Hôm nay bạn đã dùng " + caloriesUsed + " calo");
//        setDataPoint(calo_brf,calo_lun,calo_din,calo_sna);
    }

    @Override
    public void showNutrition(List<OverViewNutrition> overViewNutritions) {
        float calories = 0;
        float protein = 0;
        float lipid = 0;
        float glucid = 0;

        for (OverViewNutrition overViewNutrition : overViewNutritions) {
            if (overViewNutrition.getNutritionId() == 1) {
                calories += ((overViewNutrition.getValue() * overViewNutrition.getQuantity()) / 100);
            }
            if (overViewNutrition.getNutritionId() == 3) {
                protein += ((overViewNutrition.getValue() * overViewNutrition.getQuantity()) / 100);
            }
            if (overViewNutrition.getNutritionId() == 4) {
                lipid += ((overViewNutrition.getValue() * overViewNutrition.getQuantity()) / 100);
            }
            if (overViewNutrition.getNutritionId() == 5) {
                glucid += ((overViewNutrition.getValue() * overViewNutrition.getQuantity()) / 100);
            }
        }

        tvCaloriesused.setText((float) Math.round(calories * 100) / 100 + " Kcal");
        tvProteinused.setText((float) Math.round(protein * 100) / 100 + " g");
        tvLipidused.setText((float) Math.round(lipid * 100) / 100 + " g");
        tvGlucidused.setText((float) Math.round(glucid * 100) / 100 + " Kcal");
        caloriesUsed=calories;
    }

    @Override
    public void showUserInfo(UserBodyInfo userBodyInfo) {
        int age = UserInfoUtil.getYearBirthDay(userBodyInfo.getBirthDay());
        float calories = Statistics.ConvertBacsicCalories(age, userBodyInfo.getWeight(), userBodyInfo.getHeight(), userBodyInfo.getGender());
        float nangluongchuyenhoathucan = (calories * 10) / 100;
        float nangluongchohoatdongtheluc = Statistics.ConvertActivityLevel(calories, userBodyInfo.getActivityLevelID());
        float luongcalo = calories + nangluongchohoatdongtheluc + nangluongchuyenhoathucan;
        float nhucauPro = userBodyInfo.getWeight();
        tvCaloriesusnc.setText((float) Math.round(luongcalo * 100) / 100 + " Kcal");
        tvProteinnc.setText((float) Math.round(nhucauPro * 100) / 100 + " g");
        tvLipidnc.setText((float) Math.round(Statistics.ConvertLipidForFood(nhucauPro, age) * 100) / 100 + " g");
        tvGlucidnc.setText((float) Math.round(((luongcalo * 6) / 10) * 100) / 100 + " Kcal");
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USERINFO", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("UserAge", age);
        editor.commit();

    }

    @Override
    public void showErrorLoadData(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    //    private void setDataPoint(float brf,float lun,float din,float sna){
//        float[] datapoints = {brf, lun, din, sna};
//        pieChartView.setDataPoints(datapoints);
//    }
    public void setData1(int count,List<OverViewNutrition> nutritionList) {
        ArrayList<BarEntry> yvalues = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            float val1 = 25;
            float val2 = 25;
            float val3 = 25;
            float val4 = 25;
            yvalues.add(new BarEntry(i, new float[]{val1, val2, val3, val4}));
        }

        BarDataSet set1;
        set1 = new BarDataSet(yvalues, "Statistics of FoodApp");
        set1.setDrawIcons(false);
        set1.setStackLabels(new String[]{"bữa sáng", "bữa trưa", "bữa tối", "bữa phụ"});
        set1.setColors(ColorTemplate.MATERIAL_COLORS);

        BarData barData = new BarData(set1);
        barData.setValueFormatter(new MyValueFormatter());


        mBarChart.setData(barData);
        mBarChart.setFitBars(true);
        mBarChart.invalidate();
        mBarChart.setScaleEnabled(false);
        mBarChart.getDescription().setEnabled(false);
        mBarChart.setDrawValueAboveBar(false);
        //
        final String[] weekdays = {"Calories", "Protein", "Glucid", "Lipid"};

        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(weekdays));
        xAxis.setGranularity(1f);
        //
        YAxis leftAxis = mBarChart.getAxisLeft();
        leftAxis.setValueFormatter(new MyAxisValueFormatter());
        YAxis rightAxis = mBarChart.getAxisRight();
        rightAxis.setValueFormatter(new MyAxisValueFormatter());
//        leftAxis.setAxisMinimum(0f);
//        mChart.getAxisRight().setEnabled(false);
        XAxis xLabels = mBarChart.getXAxis();
        xLabels.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
    }
}
