package com.example.vietdang.foodappversion2.view.signup;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vietdang.foodappversion2.R;
import com.example.vietdang.foodappversion2.model.user.UserProfile;
import com.example.vietdang.foodappversion2.presenter.signup.ISignupPre;
import com.example.vietdang.foodappversion2.presenter.signup.SignupPre;
import com.example.vietdang.foodappversion2.view.login.LoginFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by vanlo on 3/25/2018.
 */

public class SignupFragment extends AppCompatActivity implements ISignupView,View.OnFocusChangeListener,View.OnClickListener{

    private ISignupPre iSignupPre;
    private EditText edtFullName, edtAddress,edtEmail,edtUserName,edtPass,edtcfPass;
    private RadioButton rdNam,rdNu;
    private RadioGroup rdGroupActivity;
    private TextInputLayout input_edt_resgiterlayout_fullname,input_edt_resgiterlayout_address,
            input_edt_resgiterlayout_email,
            input_edt_resgiterlayout_username,input_edt_resgiterlayout_pass,input_edt_resgiterlayout_cfpass;
    private TextView txt_showDate;

    private Boolean checkInfo = false;
    private Button btnSummit, btnCancel,btn_showDate;
    private LoginFragment loginFragment;


    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_register);
        iSignupPre = new SignupPre(this);
        anhXa();
        btnSummit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btn_showDate.setOnClickListener(this);

        edtUserName.setOnFocusChangeListener(this);
        edtPass.setOnFocusChangeListener(this);
        edtFullName.setOnFocusChangeListener(this);
        edtEmail.setOnFocusChangeListener(this);
        edtcfPass.setOnFocusChangeListener(this);
        edtAddress.setOnFocusChangeListener(this);
    }

    // get gioi tinh
    public int getGioiTinh(){
        int gioiTinh=1;
        if(rdNam.isChecked()){
            return  gioiTinh=1;
        }
        if (rdNu.isChecked()){
            return gioiTinh=0;
        }

        return gioiTinh;
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
    //2 lan nge su kien click
//    public void setupDatePicker(){
//        Calendar calendar = Calendar.getInstance();
//        // Lấy ra năm - tháng - ngày hiện tại
//        int year = calendar.get(calendar.YEAR);
//        final int month = calendar.get(calendar.MONTH);
//        int day = calendar.get(calendar.DAY_OF_MONTH);
//
//        // Khởi tạo sự kiện lắng nghe khi DatePicker thay đổi
//        dpBirthDay.init(year,month,day,new DatePicker.OnDateChangedListener() {
//            @Override
//            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                Toast.makeText(getApplicationContext(), dayOfMonth+"-"+monthOfYear+"-"+year, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    @Override
    public void addUserSuccess(String msg) {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();

    }


    @Override
    public void onFocusChange(View view, boolean b) {
        int id = view.getId();
        switch (id) {
            case R.id.edtName:
                if (b) {
                    String chuoi = ((EditText) view).getText().toString();
                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        input_edt_resgiterlayout_username.setErrorEnabled(true);
                        input_edt_resgiterlayout_username.setError("Bạn chưa nhận mục này !");
                        checkInfo = false;
                    } else {
                        input_edt_resgiterlayout_username.setErrorEnabled(false);
                        input_edt_resgiterlayout_username.setError("");
                        checkInfo = true;
                    }
                }
                break;

            case R.id.edtEmail:
                if (b) {
                    String chuoi = ((EditText) view).getText().toString();

                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        input_edt_resgiterlayout_email.setErrorEnabled(true);
                        input_edt_resgiterlayout_email.setError("Bạn chưa nhận mục này !");
                        checkInfo = false;
                    } else {

                        Boolean kiemtraemail = Patterns.EMAIL_ADDRESS.matcher(chuoi).matches();
                            if (!kiemtraemail) {
                            input_edt_resgiterlayout_email.setErrorEnabled(true);
                            input_edt_resgiterlayout_email.setError("Địa chỉ email đúng là abc@gmail.com !");
                            checkInfo = false;
                        } else {
                            input_edt_resgiterlayout_email.setErrorEnabled(false);
                            input_edt_resgiterlayout_email.setError("");
                            checkInfo = true;
                        }
                    }
                }
                break;

            case R.id.edtPass:
                if (b){
                    String chuoi = ((EditText) view).getText().toString();
                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        input_edt_resgiterlayout_cfpass.setErrorEnabled(true);
                        input_edt_resgiterlayout_cfpass.setError("Nhập Vào Mật Khẩu");
                        checkInfo = false;
                    }
                }
                break;
            case R.id.edtcfPass:
                if (b) {
                    String chuoi = ((EditText) view).getText().toString();
                    String matkhau = edtPass.getText().toString();
                    if (!chuoi.equals(matkhau)) {
                        input_edt_resgiterlayout_cfpass.setErrorEnabled(true);
                        input_edt_resgiterlayout_cfpass.setError("Mật khẩu không trùng khớp !");
                        checkInfo = false;
                    } else {
                        input_edt_resgiterlayout_cfpass.setErrorEnabled(false);
                        input_edt_resgiterlayout_cfpass.setError("");
                        checkInfo = true;
                    }
                }
                break;

        }

    }
    public void anhXa(){
        txt_showDate=(TextView)findViewById(R.id.txtHienThiDate);
        edtFullName=(EditText)findViewById(R.id.edtFullName);
        edtAddress=(EditText)findViewById(R.id.edtAddress);
        edtEmail=(EditText)findViewById(R.id.edtEmail);
        edtUserName=(EditText)findViewById(R.id.edtName);
        edtPass=(EditText)findViewById(R.id.edtPass);
        edtcfPass=(EditText)findViewById(R.id.edtcfPass);
        btnCancel=(Button)findViewById(R.id.cancel);
        btnSummit=(Button)findViewById(R.id.submit);
        rdNam=(RadioButton)findViewById(R.id.rdNam);
        rdNu=(RadioButton)findViewById(R.id.rdNu);

        input_edt_resgiterlayout_cfpass=(TextInputLayout)findViewById(R.id.input_edt_resgiterlayout_cfpass);
        input_edt_resgiterlayout_fullname=(TextInputLayout)findViewById(R.id.input_edt_resgiterlayout_fullname);
        input_edt_resgiterlayout_address=(TextInputLayout)findViewById(R.id.input_edt_resgiterlayout_address);
        input_edt_resgiterlayout_email=(TextInputLayout)findViewById(R.id.input_edt_resgiterlayout_email);
        input_edt_resgiterlayout_username=(TextInputLayout)findViewById(R.id.input_edt_resgiterlayout_username);
        input_edt_resgiterlayout_pass=(TextInputLayout)findViewById(R.id.input_edt_resgiterlayout_pass);

        btn_showDate=(Button)findViewById(R.id.btn_showDate);
    }
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean isEmailValid(String email) {
        boolean isValid = false;

//        String expression = "^[a-zA-Z0-9._-]+@[a-z]+.[a-z]{2,4}$";
        String expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
    @Override
    public void onClick(View view) {
        int id= view.getId();
        switch (id){
            case R.id.btn_showDate:

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        txt_showDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;

            case R.id.cancel:
                android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.linearLayout,loginFragment)
                        .commit();
                break;
            case R.id.submit:
                String Name = edtFullName.getText().toString().trim();
                String Pass = edtPass.getText().toString().trim();
                String FullName = edtFullName.getText().toString().trim();
//2 get date

//                Date date = getDateFromDatePicker(dpBirthDay);
                String Address = edtAddress.getText().toString().trim();
                String Email = edtEmail.getText().toString().trim();
                String cfMatKhau=edtcfPass.getText().toString().trim();
                String birthDayTemp = txt_showDate.getText().toString().trim();
                Integer gioiTinh = getGioiTinh();

                boolean invalid = false;

                if (FullName.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(), "Enter your Given name",
                            Toast.LENGTH_SHORT).show();
                } else
                if (Name.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(),
                            "Please enter your UserName", Toast.LENGTH_SHORT)
                            .show();
                } else
                if (Email.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(),
                            "Please enter your Email", Toast.LENGTH_SHORT)
                            .show();
                } else
                if (!emailValidator(Email)) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(),
                            "Email enter not OK, abc123@gmail.com", Toast.LENGTH_SHORT)
                            .show();
                } else
                if (Address.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(),
                            "Please enter your address", Toast.LENGTH_SHORT)
                            .show();
                }
                else if (Pass.equals("")) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(),
                            "Please enter your password", Toast.LENGTH_SHORT).show();
                }
                else if (Pass.length() < 6) {
                    invalid = true;
                    Toast.makeText(getApplicationContext(),
                            "Please enter atleast 6 digits password", Toast.LENGTH_SHORT).show();
                }else
                if (!Pass.equals(cfMatKhau)) {
                    invalid = true;
                    input_edt_resgiterlayout_cfpass.setErrorEnabled(true);
                    input_edt_resgiterlayout_cfpass.setError("Mật khẩu không trùng khớp !");
                    checkInfo = false;
                }
                else if (!invalid)
                {

                    UserProfile userProfile = new UserProfile(Name, Pass, FullName, Address, Email, gioiTinh, convertToDate(birthDayTemp));
                    iSignupPre.AddUserSuccess(userProfile);
                    Toast.makeText(getApplicationContext(),"thanh cong",Toast.LENGTH_SHORT).show();
//                    fragmentManager=getSupportFragmentManager();
//                    loginFragment= new LoginFragment();
//                    fragmentManager.beginTransaction()
//                            .replace(R.id.linearLayout,loginFragment)
//                            .commit();
                }
                break;
            }
    }
    private Boolean validationSuccess(){

        if(edtEmail.getText().toString().trim().length()<=0){
            Toast.makeText(getApplicationContext(),"Please enter Email",Toast.LENGTH_SHORT).show();

            return false;
        }
        if(edtUserName.getText().toString().trim().length()<=0){
            Toast.makeText(getApplicationContext(),"Please enter username",Toast.LENGTH_SHORT).show();
            return false;
        }else
        if(edtPass.getText().toString().trim().length()<=0){
            Toast.makeText(getApplicationContext(),"Please enter password",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(edtcfPass.getText().toString().trim().length()<=0){
            Toast.makeText(getApplicationContext(),"Please enter Xác nhận lại password",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(edtFullName.getText().toString().trim().length()<=0){
            Toast.makeText(getApplicationContext(),"Please enter FullName",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(edtAddress.getText().toString().trim().length()<=0){
            Toast.makeText(getApplicationContext(),"Please enter Địa Chỉ",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public Date convertToDate(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        Date date1=null;
        try {
            date1= dateFormat.parse(date);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }


}
