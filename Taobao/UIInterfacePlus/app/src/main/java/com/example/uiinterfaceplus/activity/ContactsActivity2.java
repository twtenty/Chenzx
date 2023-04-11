package com.example.uiinterfaceplus.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;

import com.example.uiinterfaceplus.R;
import com.example.uiinterfaceplus.adapter.RecyclerContacts2Adapter;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity2 extends AppCompatActivity {

    // 适配器
    private RecyclerContacts2Adapter adapter;
    // RecyclerView
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts2);

//        LinearLayout linearLayout = findViewById(R.id.contacts1_contacts);
//        linearLayout.setOnClickListener(view ->
//                // 请求读取联系人信息的权限
//                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 0x123));;
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS}, 0x123);

        //返回
        ImageView fanhui = (ImageView) findViewById(R.id.contacts2_fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.CART_BROADCAST");
                intent.putExtra("data","refresh");
                LocalBroadcastManager.getInstance(ContactsActivity2.this).sendBroadcast(intent);
                sendBroadcast(intent);
                finish();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == 0){
            if (requestCode == 0x123) {
                // 定义两个List来封装系统的联系人信息、指定联系人的电话号码、Email等详情
                List<String> names = new ArrayList<>();
                List<List<String>> details = new ArrayList<>();
                // 使用ContentResolver查找联系人数据
                Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                        null, null, null, null);
                // 遍历查询结果，获取系统中所有联系人
                while (cursor.moveToNext()) {
                    // 获取联系人ID
                    @SuppressLint("Range") String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    // 获取联系人的名字
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    names.add(name);
                    // 使用ContentResolver查找联系人的电话号码
                    Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                            null, null);
                    List<String> detail = new ArrayList<>();
                    detail.add("电话号码: ");
                    // 遍历查询结果，获取该联系人的多个电话号码
                    while (phones.moveToNext()) {
                        // 获取查询结果中电话号码列中数据
                        @SuppressLint("Range") String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        detail.add(phoneNumber);
                    }
                    phones.close();

                    details.add(detail);
                }
                cursor.close();
                //        recycler
                recyclerView = (RecyclerView) findViewById(R.id.contacts2_rv);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
//              adapter = new RecyclerShopAdapter(getActivity().getApplicationContext(),list,shop_shop,shop_jiesuan,tv_sum,all_checked);
                adapter = new RecyclerContacts2Adapter(names,details);
                recyclerView.setAdapter(adapter);
            }
        }
    }
}