package com.example.uiinterfaceplus.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.uiinterfaceplus.R;

public class ContactsActivity1 extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts1);

        //点击联系人事件
        LinearLayout contacts1_contacts = (LinearLayout) findViewById(R.id.contacts1_contacts);
        contacts1_contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactsActivity1.this, ContactsActivity2.class);
                startActivity(intent);
            }
        });

        //返回
        ImageView fanhui = (ImageView) findViewById(R.id.contacts1_fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.CART_BROADCAST");
                intent.putExtra("data","refresh");
                LocalBroadcastManager.getInstance(ContactsActivity1.this).sendBroadcast(intent);
                sendBroadcast(intent);
                finish();
            }
        });

    }

}