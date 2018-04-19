package com.example.vietdang.foodappversion2.util;

/**
 * Created by neelmani.karn on 5/6/2015.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public final class NetworkConnectivity {

    private static NetworkConnectivity instance = new NetworkConnectivity();

    public static NetworkConnectivity getInstance() {
        return instance;
    }

    /**
     * Check if internet connection is available or not
     *
     * @return true if internet is available else false
     */
    public boolean isInternetConnectionAvaliable(Context context) {
        if (null == context) {
            return true;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // test for connection
        NetworkInfo netInfo = null;
        if (null != connectivityManager) {
            netInfo = connectivityManager.getActiveNetworkInfo();
        }
        if (null != netInfo && netInfo.isAvailable() && netInfo.isConnected()) {
            return true;
        } else {
            Log.d("Loi","khong co ket noi");
            return false;
        }
    }
}