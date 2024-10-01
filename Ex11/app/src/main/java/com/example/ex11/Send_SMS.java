package com.example.ex11;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Send_SMS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_send_sms);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText editPhoneSend;
        ImageButton btnSend;
        Button btnBackSend;
        editPhoneSend = findViewById(R.id.editPhoneSend);
        btnSend = findViewById(R.id.btnSend);
        btnBackSend = findViewById(R.id.btnBackSend);
        btnBackSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Send_SMS.this , MainActivity.class);
                startActivity(intent);
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = editPhoneSend.getText().toString().trim();
                if (phoneNumber.isEmpty()) {
                    Toast.makeText(Send_SMS.this, "Vui lòng nhập số điện thoại mới có thể gửi được mã SMS", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNumber));
                    if(ActivityCompat.checkSelfPermission(Send_SMS.this ,
                            Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(Send_SMS.this , new String[] {Manifest.permission.CALL_PHONE} , 1);
                    }
                    startActivity(intent);
                }
            }
        });
    }
}