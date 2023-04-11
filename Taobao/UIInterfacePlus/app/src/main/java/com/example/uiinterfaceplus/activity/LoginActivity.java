package com.example.uiinterfaceplus.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uiinterfaceplus.R;
import com.example.uiinterfaceplus.receiver.VerCodeReceiver;
import com.example.uiinterfaceplus.service.VerCodeService;
import com.example.uiinterfaceplus.tools.SPSaveTB;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private static Button login_vercodebtn;
    private boolean vercode_flag = true;
    private static EditText login_vercodeet;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //验证码
        login_vercodebtn = findViewById(R.id.login_VerCodeBtn);
        login_vercodeet = findViewById(R.id.login_VerCodeEt);
        login_vercodebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vercode_flag){
                    Intent intent = new Intent(LoginActivity.this, VerCodeService.class);
                    startService(intent);
                    login_vercodebtn.setText("倒计时60秒");
                }
            }
        });

        MyBroadcastReceiver  receiver = new MyBroadcastReceiver(); //实例化广播接收者
        String action = "com.example.uiinterfaceplus.service";
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(action);
        registerReceiver(receiver,intentFilter); //注册广播

        EditText name = (EditText) findViewById(R.id.login_nc);
        EditText id = (EditText) findViewById(R.id.login_id);
//        EditText pwd = (EditText) findViewById(R.id.login_password);
        Button btn_login = (Button) findViewById(R.id.btn_login) ;

        Map<String,String> userInfo = SPSaveTB.getUserInfo(LoginActivity.this);
        if(userInfo!=null){
            name.setText(userInfo.get("name"));
            id.setText(userInfo.get("id"));
//            pwd.setText(userInfo.get("password"));
        }
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name",name.getText().toString());
                intent.putExtra("id",id.getText().toString());
                setResult(2,intent);
                switch (v.getId()){
                    case R.id.btn_login:
                        String name1 = name.getText().toString();
                        String id1 = id.getText().toString();
//                        String password = pwd.getText().toString();
                        if(TextUtils.isEmpty(name1)){
                            Toast.makeText(LoginActivity.this,"请输入昵称",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(TextUtils.isEmpty(id1)){
                            Toast.makeText(LoginActivity.this,"请输入账号",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                        break;

                }

                finish();
            }
        });

        //接收短信
        requestPermissions(new String[] {Manifest.permission.RECEIVE_SMS},1);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==1){
            for(int i=0;i<permissions.length;i++){
                if(permissions[i].equals("android.permission.RECEIVE_SMS")&&grantResults[i]== PackageManager.PERMISSION_GRANTED){
                    VerCodeReceiver myReceiver=new VerCodeReceiver();
                    IntentFilter intentFilter=new IntentFilter();
                    intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
                    intentFilter.setPriority(1000);
                    registerReceiver(myReceiver,intentFilter);
                }
            }

        }
    }

    public static Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle bundle = msg.getData();
            String code = bundle.getString("vercode");
            login_vercodeet.setText(code);
            login_vercodebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login_vercodeet.setText(code);
                }
            });

//            login_vercodeet.setSelection(code.length());
        }
    };

    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int time = intent.getIntExtra("time",60);
            if(time < 60 && time >0){
                vercode_flag = false;
            }else{
                vercode_flag = true;
            }
            login_vercodebtn.setText("倒计时"+time+"秒");
//            Log.e("yanwenhua","shengyu "+time);
        }
    }

}