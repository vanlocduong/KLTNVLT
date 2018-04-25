package com.example.vietdang.foodappversion2.view.login;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vietdang.foodappversion2.R;
import com.example.vietdang.foodappversion2.model.user.User;
import com.example.vietdang.foodappversion2.util.PrefUtil;
import com.example.vietdang.foodappversion2.view.account.AccountFragment;
import com.example.vietdang.foodappversion2.view.home.HomeActivity;
import com.example.vietdang.foodappversion2.view.overview.OverViewFragment;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.lang.reflect.Array;
import java.util.Arrays;

public class FacebookLoginActivity extends Activity implements GetUserCallback.IGetUserResponse {

    private TextView mId;
    public  static String codeId="";
    String userId="";
    private static  int REQUEST_CODE_LOGIN= 3;

    private static final String EMAIL = "email";
    private static final String PUBLIC_PROFILE = "public_profile";

    private static final String USER_POSTS = "user_posts";

    private ProfileTracker  profileTracker;

    private CallbackManager mCallbackManager;
    private PrefUtil prefUtil;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        mCallbackManager=CallbackManager.Factory.create();

        setContentView(R.layout.activity_facebook_login);


        mId=(TextView)findViewById(R.id.info);


        LoginButton mloginButton= (LoginButton)findViewById(R.id.login_button);

        // set initial permissions to request from the user while logging in
        mloginButton.setReadPermissions(Arrays.asList(PUBLIC_PROFILE,EMAIL,USER_POSTS));
        // register call back response to the user
        mloginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(final LoginResult loginResult) {

                // save accessToken to SharedPreference
                String accessToken = loginResult.getAccessToken().getToken();
                // save accessToken to SharedPreference
                prefUtil= new PrefUtil(FacebookLoginActivity.this);
                prefUtil.saveAccessToken(accessToken);

                profileTracker= new ProfileTracker() {
                    @Override
                    protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                        loginResult.getAccessToken();
                        String  id=currentProfile.getId();
                        String first_name =currentProfile.getFirstName();
                        String last_name=currentProfile.getLastName();
                        String profileURL=currentProfile.getLinkUri().toString();
                        // save to SharedPreferences
                        prefUtil.saveFacebookUserInfo_basic(id,first_name,last_name,profileURL);
                        mId.setText(id);
                        goManHinh_Activity("35");

                    }
                };



            }

            @Override
            public void onCancel() {
                setResult(RESULT_CANCELED);
                finish();

            }

            @Override
            public void onError(FacebookException error) {


                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        });

    }

    private  void HienThi_Thongtin(Profile profile){
        String id = profile.getId();
        String name =profile.getName();

    }
    private  void goManHinh_Activity(String value){


        Intent intent_Activity= new Intent(getApplicationContext(), HomeActivity.class);
        intent_Activity.putExtra("userId",""+value);
        intent_Activity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent_Activity,REQUEST_CODE_LOGIN);

    }
    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.linerFacebook, fragment);
        fragmentTransaction.commit(); // save the changes
    }
    @Override
    public void onCompleted(User user) {

        mId.setText(user.getName());

    }
}
