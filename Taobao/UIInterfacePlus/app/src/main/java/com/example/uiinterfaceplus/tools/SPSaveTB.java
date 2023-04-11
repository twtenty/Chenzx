package com.example.uiinterfaceplus.tools;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class SPSaveTB {
    public static boolean saveUserInfo(Context context, String name,String id,String password){
        SharedPreferences sp = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name",name);
        editor.putString("id",id);
        editor.putString("pwd",password);
        editor.commit();
        return true;
    }

    public static Map<String,String> getUserInfo(Context context){
        SharedPreferences sp = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        String name = sp.getString("name",null);
        String id = sp.getString("id",null);
        String password = sp.getString("pwd",null);
        Map<String,String> userMap = new HashMap<String,String>();
        userMap.put("name",name);
        userMap.put("id",id);
        userMap.put("password",password);
        return userMap;
    }
}
