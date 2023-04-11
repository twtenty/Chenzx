package com.example.uiinterfaceplus.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.uiinterfaceplus.MainActivity;
import com.example.uiinterfaceplus.R;
import com.example.uiinterfaceplus.adapter.HomeAdapter;
import com.example.uiinterfaceplus.bean.Account;
import com.example.uiinterfaceplus.dao.AccountDao;
import com.example.uiinterfaceplus.info.SnacksInfo;
import com.example.uiinterfaceplus.tools.TwoDecimal;
import com.example.uiinterfaceplus.utils.Constant;
import com.example.uiinterfaceplus.utils.SnacksJsonParse;

import java.io.IOException;
import java.util.List;


import okhttp3.OkHttpClient;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity2 extends AppCompatActivity {

    public static final int MSG_SHOP_OK = 1; //获取数据
    private MHandler mHandler;
    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ActionBar actionBar= getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        mHandler=new MHandler();
        initData();
        recyclerView = (RecyclerView) findViewById(R.id.rv2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        homeAdapter = new HomeAdapter();
        recyclerView.setAdapter(homeAdapter);

        ImageView fanhui = (ImageView) findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.CART_BROADCAST");
                LocalBroadcastManager.getInstance(MainActivity2.this).sendBroadcast(intent);
                sendBroadcast(intent);
                finish();
            }
        });


    }

    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(Constant.WEB_SITE +
                Constant.REQUEST_SHOP_URL).build();
        Call call = okHttpClient.newCall(request);
        // 开启异步线程访问网络
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string(); //获取店铺数据
//                System.out.println("res="+res);
                Message msg = new Message();
                msg.what = MSG_SHOP_OK;
                msg.obj = res;
                mHandler.sendMessage(msg);
            }
            @Override
            public void onFailure(Call call, IOException e) {

            }
        });
    }

    /**
     * 事件捕获
     */
    class MHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case MSG_SHOP_OK:
                    if (msg.obj != null) {
                        String vlResult = (String) msg.obj;
                        //解析获取的JSON数据
                       List<SnacksInfo> pythonList = SnacksJsonParse.getInstance().
                                getSnacksList(vlResult);
                       System.out.println("pythonList="+pythonList);
                        homeAdapter.setData(pythonList);
                    }
                    break;
            }
        }
    }


}