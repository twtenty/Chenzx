package com.example.uiinterfaceplus.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.uiinterfaceplus.R;

public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        LinearLayout fanhui = (LinearLayout) findViewById(R.id.fanhui_end);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.CART_BROADCAST");
                LocalBroadcastManager.getInstance(EndActivity.this).sendBroadcast(intent);
                sendBroadcast(intent);
                finish();
            }
        });

    }
}