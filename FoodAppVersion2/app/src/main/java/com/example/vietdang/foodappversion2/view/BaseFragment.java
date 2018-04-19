package com.example.vietdang.foodappversion2.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.vietdang.foodappversion2.R;
import com.example.vietdang.foodappversion2.util.NetworkConnectivity;

/**
 * Created by vietdang on 3/9/2018.
 */

public class BaseFragment extends Fragment {
    protected NetworkConnectivity networkConnectivity;
    protected ProgressDialog pd;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        networkConnectivity = NetworkConnectivity.getInstance();

        pd = new ProgressDialog(context);
        pd.setMessage(getString(R.string.hello_blank_fragment));
        pd.setIndeterminate(true);
        pd.setCancelable(false);
    }
}
