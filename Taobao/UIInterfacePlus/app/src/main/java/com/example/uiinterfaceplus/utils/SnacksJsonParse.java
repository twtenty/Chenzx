package com.example.uiinterfaceplus.utils;

import android.content.Context;

import com.example.uiinterfaceplus.info.SnacksInfo;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SnacksJsonParse {
    private static SnacksJsonParse instance;
    private SnacksJsonParse() {


    }
    public static SnacksJsonParse getInstance(){
        if (instance==null){
            instance=new SnacksJsonParse();
        }
        return instance;
    }



    public List<SnacksInfo>getSnacksList(String json) {
        Gson gson= new Gson();//使用json库
        //创建一个TypeToken匿名子类对象，并调用对象的getType()方法
        Type listType = new TypeToken<List<SnacksInfo>>(){
        }.getType();
        //返回信息集合
        List<SnacksInfo> shopList = gson.fromJson(json, listType);
        return shopList;

    }




}
