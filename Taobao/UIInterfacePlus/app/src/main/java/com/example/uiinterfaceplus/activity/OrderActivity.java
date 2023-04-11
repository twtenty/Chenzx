package com.example.uiinterfaceplus.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uiinterfaceplus.R;
import com.example.uiinterfaceplus.adapter.RecyclerOrderAdapter;
import com.example.uiinterfaceplus.bean.Account;
import com.example.uiinterfaceplus.dao.AccountDao;
import com.example.uiinterfaceplus.tools.TwoDecimal;

import java.util.List;

public class OrderActivity extends AppCompatActivity {

    // 需要适配的数据集合
    private List<Account> list;
    // 数据库增删改查操作类
    private AccountDao dao;
    // 适配器
    private RecyclerOrderAdapter adapter;
    // RecyclerView
    private RecyclerView recyclerView;
    //总金额文本
    private TextView tv_sum;
    //返回按钮
    private ImageView fanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        //购物车总金额监听
        tv_sum = (TextView) findViewById(R.id.sum_order);
        dao = new AccountDao(OrderActivity.this);
        // 从数据库查询出所有数据
        list = dao.queryisselected();
        //        recycler
        recyclerView = (RecyclerView) findViewById(R.id.accountRV_order);
        recyclerView.setLayoutManager(new LinearLayoutManager(OrderActivity.this));
//        adapter = new RecyclerShopAdapter(getActivity().getApplicationContext(),list,shop_shop,shop_jiesuan,tv_sum,all_checked);
        adapter = new RecyclerOrderAdapter(list);
        recyclerView.setAdapter(adapter);
        //初始化控件
        initView();

        //返回监听
        fanhui = (ImageView) findViewById(R.id.fanhui_order);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.CART_BROADCAST");
                intent.putExtra("data","refresh");
                LocalBroadcastManager.getInstance(OrderActivity.this).sendBroadcast(intent);
                sendBroadcast(intent);
                finish();
            }
        });
    }

    private void initView() {
        Double p =0.0;
        for (int i=0;i<list.size();i++){

            if (list.get(i).getFlag()==1){
                p += list.get(i).getSum();
            }
        }
        tv_sum.setText("¥" + new TwoDecimal().solve(p));
    }

}