package com.example.vietdang.foodappversion2.view.account;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vietdang.foodappversion2.R;
import com.example.vietdang.foodappversion2.model.user.UserProfile1;
import com.example.vietdang.foodappversion2.presenter.account.AccountPre;
import com.example.vietdang.foodappversion2.presenter.account.IAccountPre;
import com.example.vietdang.foodappversion2.view.login.LoginActivity;
import com.example.vietdang.foodappversion2.view.update_user.UpdateUserView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment implements IDetailAccountView,View.OnClickListener {

    TextView txtName,txvEmail,txtAddress, txtFullName,txtGender,txtBirthDay,txt_Height,txt_Weight;
    IAccountPre mIAccountPre;
    ImageView img_logout;
    private int userID=1;
//    TextView txtName,txvEmail,txtAddress, txtFullName,txtGender,txtBirthDay,txt_Height,txt_Weight;
    EditText edt_acName,edt_acEmail,edt_acAddress,edt_acFullName,edt_acGender,edt_acBirthDay,edt_acHeight,edt_acWeight;
    Button btn_update_user;
//    IAccountPre mIAccountPre;
//    private int userID=1;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userID = getArguments().getInt("UserID");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_account, container, false);
        txtFullName = view.findViewById(R.id.user_profile_name);
        txvEmail= view.findViewById(R.id.txvEmail);
        txtAddress= view.findViewById(R.id.txtAddress);
        txtName=view.findViewById(R.id.user_profile_short_bio);
        txtGender=view.findViewById(R.id.txtGender);
        txtBirthDay=view.findViewById(R.id.txtBirthDay);
        img_logout=view.findViewById(R.id.img_logout);
        btn_update_user=(Button)view.findViewById(R.id.btn_updateProfile);

        edt_acAddress=view.findViewById(R.id.edt_acAdress);
        edt_acBirthDay=view.findViewById(R.id.edt_acBirthDay);
        edt_acEmail=view.findViewById(R.id.edt_acEmail);
        edt_acGender=view.findViewById(R.id.edt_acGender);

        img_logout.setOnClickListener(this);
        btn_update_user.setOnClickListener(this);
//        edt_acHeight=view.findViewById(R.id.edt_acHeight);
//        edt_acWeight=view.findViewById(R.id.edt_acWeight);

//        txt_Height=view.findViewById(R.id.txt_Height);
//        txt_Weight=view.findViewById(R.id.txt_Weight);


        mIAccountPre = new AccountPre(this);
        mIAccountPre.onLoadAccountSuccess(userID);

        return view;
    }

    @Override
    public void showData(UserProfile1 detailUser) {
//        txtName.setText(detailUser.getUserName());
//        txvEmail.setText(detailUser.getEmail());
//        txtAddress.setText(detailUser.getAddress());
//        txtFullName.setText(detailUser.getFullName());
//        txt_Height.setText(detailUser.getHeight());
//        txt_Weight.setText(detailUser.getWeight());
//        txtBirthDay.setText(detailUser.getBirthDay().toString());

        edt_acAddress.setText(detailUser.getAddress());
        edt_acEmail.setText(detailUser.getEmail());
        edt_acBirthDay.setText(detailUser.getBirthDay().toString().substring(0,10).trim());
        if(detailUser.getGender()== 1){
//            txtGender.setText("Nam");
            edt_acGender.setText("Nam");
        }else {
//            txtGender.setText("Nữ");
            edt_acGender.setText("Nữ");
        }
        Log.d("usernamethanhcong",detailUser.getUserName().toString());

    }
    @Override
    public void showErrorLoadData(String message) {


    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.img_logout:
                Intent intent= new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

                break;
            case  R.id.btn_updateProfile:
                Intent intent1= new Intent(getActivity(), UpdateUserView.class);

                intent1.putExtra("userId",userID);

                startActivity(intent1);
                ((AppCompatActivity) getActivity()).overridePendingTransition(0,0);
        }
    }
}
