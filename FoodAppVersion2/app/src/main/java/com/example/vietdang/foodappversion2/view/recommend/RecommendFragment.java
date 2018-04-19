package com.example.vietdang.foodappversion2.view.recommend;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vietdang.foodappversion2.R;
import com.example.vietdang.foodappversion2.model.recommend.SettingUserInfoRequest;
import com.example.vietdang.foodappversion2.presenter.recommend.IRecommendPre;
import com.example.vietdang.foodappversion2.presenter.recommend.RecommendPre;
import com.example.vietdang.foodappversion2.util.Statistics;
import com.example.vietdang.foodappversion2.util.UserInfoUtil;
import com.example.vietdang.foodappversion2.util.UtilDateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends Fragment implements IRecommendFragment{

    private Spinner spnActivityLevel;
    private CardView cvShowmore;
    private Button btnShowInfo, btnSetInfo;
    private TextView tvCalo, tvPro, tvLipid, tvGlu,tvBmi;
    private RadioGroup rdgGender;
    private EditText edtWeight, edtHeight;
    //

    int userGender=0;
    //service
    private IRecommendPre mIRecommendPre;
    int userID=0;

    public RecommendFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIRecommendPre=new RecommendPre(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        spnActivityLevel = (Spinner) view.findViewById(R.id.spn_recommend_activitylevel);
        cvShowmore = (CardView) view.findViewById(R.id.cv_recommend_showmore);
        btnShowInfo = (Button) view.findViewById(R.id.btn_recommend_tinhnhucau);
        btnSetInfo = (Button) view.findViewById(R.id.btn_recommend_thietlap);
        rdgGender=(RadioGroup)view.findViewById(R.id.radiogroup_reccommend);
        tvCalo = (TextView) view.findViewById(R.id.txt_recommend_calories_used);
        tvPro = (TextView) view.findViewById(R.id.txt_recommend_protein_used);
        tvLipid = (TextView) view.findViewById(R.id.txt_recommend_lipid_used);
        tvGlu = (TextView) view.findViewById(R.id.txt_recommend_glucid_used);
        tvBmi=(TextView)view.findViewById(R.id.txt_recommend_bmi);
        edtWeight = (EditText) view.findViewById(R.id.edt_recommend_weight);
        edtHeight = (EditText) view.findViewById(R.id.edt_recommend_height);
        List<String> list = new ArrayList<>();
        list.add("Nhẹ");
        list.add("Vừa");
        list.add("Nặng");
        list.add("Khác");
        ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnActivityLevel.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userID = getArguments().getInt("UserID");
//        spnActivityLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
        rdgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioButton_male:userGender=1;
                    case R.id.radioButton_female:userGender=2;
                    case R.id.radioButton_orther:userGender=0;
                }
            }
        });
        btnShowInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                slideUp(cvShowmore);
                int age =0;
                int spinner_pos = spnActivityLevel.getSelectedItemPosition();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USERINFO", Context.MODE_PRIVATE);
                if (sharedPreferences != null) {
                    age = sharedPreferences.getInt("UserAge", 0);
                }
                float weight=Float.valueOf(edtWeight.getText().toString());
                float height=Float.valueOf(edtHeight.getText().toString());
                float calories = Statistics.ConvertBacsicCalories(age,weight ,height, userGender);
                float nangluongchuyenhoathucan = (calories * 10) / 100;
                float nangluongchohoatdongtheluc = Statistics.ConvertActivityLevel(calories, spinner_pos);
                float luongcalo = calories + nangluongchohoatdongtheluc + nangluongchuyenhoathucan;
                float nhucauPro = weight;
                tvCalo.setText(luongcalo + " Kcal");
                tvPro.setText(nhucauPro + " g");
                tvLipid.setText(Statistics.ConvertLipidForFood(nhucauPro, age) + " g");
                tvGlu.setText((luongcalo * 6) / 10 + " Kcal");
                float indexBMI= UserInfoUtil.getUserBMI(weight,height);
                tvBmi.setText(UserInfoUtil.getDiagnose(indexBMI));
            }
        });
        btnSetInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                slideDown(cvShowmore);
                int activityLevelId = spnActivityLevel.getSelectedItemPosition();
//                Toast.makeText(getContext(), spnActivityLevel.getSelectedItem().toString() + " i= " + spinner_pos, Toast.LENGTH_SHORT).show();
                float weight= Float.parseFloat(edtWeight.getText().toString());
                float height= Float.parseFloat(edtHeight.getText().toString());
                String date= UtilDateTime.getCurrentDate();
                SettingUserInfoRequest userInfoRequest=new SettingUserInfoRequest();
                userInfoRequest.setActivityLevelID(activityLevelId+1);
                userInfoRequest.setHeight(height);
                userInfoRequest.setWeight(weight);
                userInfoRequest.setUserId(userID);
                userInfoRequest.setUpdateDate(date);
                mIRecommendPre.AddUserInfor(userInfoRequest);
            }
        });
    }

    private void slideUp(View view) {
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,
                0,
                view.getHeight(),
                0);
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    @Override
    public void onSettingSuccess(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSettingError(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }
//    public void slideDown(View view){
//        view.setVisibility(View.INVISIBLE);
//        TranslateAnimation animate = new TranslateAnimation(
//                0,                 // fromXDelta
//                0,                 // toXDelta
//                0,                 // fromYDelta
//                view.getHeight()); // toYDelta
//        animate.setDuration(500);
//        animate.setFillAfter(true);
//        view.startAnimation(animate);
//    }
}
