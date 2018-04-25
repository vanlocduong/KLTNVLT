package com.example.vietdang.foodappversion2.view.update_user;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.vietdang.foodappversion2.R;
import com.example.vietdang.foodappversion2.model.user.UserProfile;
import com.example.vietdang.foodappversion2.presenter.update_user.IUpdateUserPresenter;
import com.example.vietdang.foodappversion2.presenter.update_user.UpdateUserPresenter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UpdateUserView extends Activity implements View.OnClickListener,IUpdateUserView{
    EditText edt_fullname,edt_email,edt_username,edt_pass,edt_address,edt_date;
    RadioButton rd_nam,rd_nu;
    Button btn_showdate,btn_submit;
    private DatePicker dpBirthDay;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private int userID=1;

    private IUpdateUserPresenter iUpdateUserPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_update_user);

        iUpdateUserPresenter= new UpdateUserPresenter(this);
        Intent intent=getIntent();
        userID = intent.getIntExtra("userId",1);
        AnhXa();

        btn_showdate.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

    }
    void AnhXa(){
        edt_address=(EditText)findViewById(R.id.edtAddress);
        edt_email=(EditText)findViewById(R.id.edtEmail);
        edt_fullname=(EditText)findViewById(R.id.edtFullName);
        edt_username=(EditText)findViewById(R.id.edtName);
        edt_pass=(EditText)findViewById(R.id.edtPass);
        rd_nam=(RadioButton)findViewById(R.id.rdNam);
        rd_nu=(RadioButton)findViewById(R.id.rdNu);
        edt_date=(EditText) findViewById(R.id.edt_date);
        btn_showdate=(Button)findViewById(R.id.btn_showDate);
        btn_submit=(Button)findViewById(R.id.btn_submit);


    }
    //2 get day time he thong
    public static java.util.Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }
    //get sex
    public int getGioiTinh(){
        int gioiTinh=1;
        if(rd_nam.isChecked()){
            return  gioiTinh=1;
        }
        if (rd_nu.isChecked()){
            return gioiTinh=0;
        }

        return gioiTinh;
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id) {
            case R.id.btn_showDate:
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        edt_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
            case R.id.btn_submit:


                String userName = edt_username.getText().toString().trim();
                String passWord = edt_pass.getText().toString().trim();
                String full_name = edt_fullname.getText().toString().trim();
//2 get date

                String birthDayTemp = edt_date.getText().toString().trim();
                String address = edt_address.getText().toString().trim();
                String email = edt_email.getText().toString().trim();
                Integer gioiTinh=getGioiTinh();
                // update user
                UserProfile userProfile = new UserProfile(userName, passWord, full_name, address, email, gioiTinh, convertToDate(birthDayTemp));

                iUpdateUserPresenter.UpdateUserSucess(userProfile);
                break;

        }

    }
    public Date convertToDate(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy",Locale.US);
        Date date1=null;
          try {
             date1= dateFormat.parse(date);
             System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
}
    @Override
    public void updateUserSucess(String str) {

    }
}
