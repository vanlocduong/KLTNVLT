package com.example.vietdang.foodappversion2.view.login;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.vietdang.foodappversion2.R;
import com.example.vietdang.foodappversion2.customview.ClearEditText;
import com.example.vietdang.foodappversion2.customview.PasswordEditText;
import com.example.vietdang.foodappversion2.model.login.Login;
import com.example.vietdang.foodappversion2.presenter.login.ILoginPresenter;
import com.example.vietdang.foodappversion2.presenter.login.LoginPresenter;
import com.example.vietdang.foodappversion2.view.BaseFragment;
import com.example.vietdang.foodappversion2.view.home.HomeActivity;
import com.example.vietdang.foodappversion2.view.signup.SignupFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment implements ILoginView, View.OnClickListener {

    private EditText userName, password;
    private PasswordEditText passwordET;
    private ClearEditText clearEtUserName;
    private ILoginPresenter presenter;
    private String usName,pass;
    private CheckBox cbRememberUserPass;
   private Button loginBtn, btnSignup;
    private static final String SPF_NAME = "vidslogin"; //  <--- Add this
    private static final String USERNAME = "username";  //  <--- To save username
    private static final String PASSWORD = "password";  //  <--- To save password

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;
    private Boolean saveLogin;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.login_demo, container, false);
        presenter=new LoginPresenter(this);
//        userName = (EditText) rootView.findViewById(R.id.et_username);
//        password = (EditText) rootView.findViewById(R.id.et_password);

        clearEtUserName=(ClearEditText)rootView.findViewById(R.id.edt_signin_email) ;
        passwordET=(PasswordEditText)rootView.findViewById(R.id.edt_signin_password) ;
        cbRememberUserPass=(CheckBox)rootView.findViewById(R.id.checkBox_signin_remember);
        loginBtn = (Button) rootView.findViewById(R.id.btn_singin_login);
        btnSignup=(Button) rootView.findViewById(R.id.btn_singin_register) ;

        sharedPreferences= getActivity().getSharedPreferences("loginPrefs",Context.MODE_PRIVATE);
        sharedPreferencesEditor=sharedPreferences.edit();

        saveLogin = sharedPreferences.getBoolean("savelogin",false);
        if(saveLogin == true){
            passwordET.setText(sharedPreferences.getString("username",""));
            clearEtUserName.setText(sharedPreferences.getString("password", ""));
            cbRememberUserPass.setChecked(true);
        }


        loginBtn.setOnClickListener(this);
        btnSignup.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_singin_login:
                if (networkConnectivity.isInternetConnectionAvaliable(getActivity())) {
                    usName=clearEtUserName.getText().toString().trim();
                    pass=passwordET.getText().toString().trim();

                    presenter.login(new Login(usName,pass));
                } else
                    ((LoginActivity) getActivity()).ShowSnackbar(getString(R.string.noInternet));
                break;
            case R.id.btn_singin_register:
                Intent intent= new Intent(getActivity(), SignupFragment.class);
                startActivity(intent);

                break;
        }
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
    public void navigateMainActivity(Integer body) {
        Intent intent=new Intent(getActivity(), HomeActivity.class);
        intent.putExtra("userId",body+"");
        startActivity(intent);
    }

    @Override
    public void showMessage(String message) {
        hideProgress();
        ((LoginActivity) getActivity()).ShowSnackbar(message);
    }

    @Override
    public void onResume() {
        super.onResume();
        passwordET.setText("");
        clearEtUserName.setText("");
        pd.dismiss();
    }
}
