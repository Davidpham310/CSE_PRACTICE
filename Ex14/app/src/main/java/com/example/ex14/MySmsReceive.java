package com.example.ex14;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MySmsReceive extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        processReceive(context , intent);
    }

    private void processReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        String message = "";
        String body = "";
        String address = "";
        if (extras != null){
            Object[] smsEtra = (Object[]) extras.get("pdus");
            for (int i = 0 ; i < smsEtra.length ; i++){
                SmsMessage sms = SmsMessage.createFromPdu((byte[])smsEtra[i]);
                body = sms.getMessageBody();
                address = sms.getOriginatingAddress();
                message += "Có một tin nhắn từ" + address + "\n" + body + "vừa gửi tới";
            }
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
}