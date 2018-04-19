package com.example.vietdang.foodappversion2.view.account;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vietdang.foodappversion2.R;
import com.example.vietdang.foodappversion2.model.user.UserProfile;
import com.example.vietdang.foodappversion2.presenter.account.AccountPre;
import com.example.vietdang.foodappversion2.presenter.account.IAccountPre;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment implements IDetailAccountView {
    TextView txtName,txvEmail,txtAddress, txtFullName,txtGender,txtBirthDay,txt_Height,txt_Weight;
    EditText edt_acName,edt_acEmail,edt_acAddress,edt_acFullName,edt_acGender,edt_acBirthDay,edt_acHeight,edt_acWeight;
    IAccountPre mIAccountPre;
    private int userID=1;

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

        edt_acAddress=view.findViewById(R.id.edt_acAdress);
        edt_acBirthDay=view.findViewById(R.id.edt_acBirthDay);
        edt_acEmail=view.findViewById(R.id.edt_acEmail);
        edt_acHeight=view.findViewById(R.id.edt_acHeight);
        edt_acWeight=view.findViewById(R.id.edt_acWeight);

//        txt_Height=view.findViewById(R.id.txt_Height);
//        txt_Weight=view.findViewById(R.id.txt_Weight);


        mIAccountPre = new AccountPre(this);
        mIAccountPre.onLoadAccountSuccess(userID);

        return view;
    }

    @Override
    public void showData(UserProfile detailUser) {
        txtName.setText(detailUser.getUserName());
        txvEmail.setText(detailUser.getEmail());
        edt_acEmail.setText(detailUser.getEmail());
        txtAddress.setText(detailUser.getAddress());
        edt_acAddress.setText(detailUser.getAddress());
        txtFullName.setText(detailUser.getFullName());
//        txt_Height.setText(detailUser.getHeight());
//        txt_Weight.setText(detailUser.getWeight());
        txtBirthDay.setText(detailUser.getBirthDay().toString());
        edt_acBirthDay.setText(detailUser.getBirthDay().toString());
        if(detailUser.getGender()== 1){
            txtGender.setText("Nam");
            edt_acGender.setText("Nam");
        }else {
            txtGender.setText("Nữ");
            edt_acGender.setText("Nữ");
        }
        Log.d("usernamethanhcong",detailUser.getUserName().toString());

    }
    @Override
    public void showErrorLoadData(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();

    }

}
