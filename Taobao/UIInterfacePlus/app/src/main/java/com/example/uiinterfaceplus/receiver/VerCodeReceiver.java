package com.example.uiinterfaceplus.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.telephony.SmsMessage;
import android.util.Log;

import com.example.uiinterfaceplus.activity.LoginActivity;

public class VerCodeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        Log.i("Mainactivity","运行到这里");
        Object[] objects=(Object[]) intent.getExtras().get("pdus");
        for(Object obj: objects){
            SmsMessage smsMessage=SmsMessage.createFromPdu((byte[]) obj);
            String body=smsMessage.getMessageBody();
            String sender=smsMessage.getOriginatingAddress();
            if(body.contains("验证码")){
                String s = new String();
                for(int i=0;i<body.length();i++){
                    if (body.charAt(i)>='0'&&body.charAt(i)<='9'){
                        int j=i;
                        while(j<body.length()&&body.charAt(j)>='0'&&body.charAt(j)<='9')
                        {
                            s+=body.charAt(j);
                            j++;
                        }
                        break;
                    }
                }
                Message message = LoginActivity.handler.obtainMessage();
                Bundle codeBundle = new Bundle();
                codeBundle.putString("vercode", s);
                message.setData(codeBundle);
                LoginActivity.handler.sendMessage(message);
            }

        }
    }
}
