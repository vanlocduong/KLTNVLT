package com.example.vietdang.foodappversion2.view.login;
import android.app.Application;



import com.facebook.FacebookSdk;


public class Facebook_Initial extends Application{






        @Override

        public void onCreate() {

            super.onCreate();

            FacebookSdk.sdkInitialize(getApplicationContext());



        }

    }

