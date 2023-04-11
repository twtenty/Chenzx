package com.example.uiinterfaceplus.tools;

import android.util.Log;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class TwoDecimal {
    public Double solve(Double price){
//        Log.e("price", ""+price,null);
        BigDecimal b = new BigDecimal(price);
//        Log.e("b", ""+b,null);
        //保留2位小数
        Double f1 = b.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();
//        Log.e("f1", ""+f1,null);
        return f1;
    }
}
