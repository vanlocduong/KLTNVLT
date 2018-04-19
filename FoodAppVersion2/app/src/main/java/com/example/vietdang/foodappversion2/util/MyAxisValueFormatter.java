package com.example.vietdang.foodappversion2.util;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

/**
 * Created by vanlo on 3/15/2018.
 */

public class MyAxisValueFormatter implements IAxisValueFormatter {
    private DecimalFormat mFormat;
    public MyAxisValueFormatter(){
        mFormat= new DecimalFormat("######.0");
    }
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mFormat.format(value)+ "%";
    }
}
