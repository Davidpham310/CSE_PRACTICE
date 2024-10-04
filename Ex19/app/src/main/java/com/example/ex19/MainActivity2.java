package com.example.ex19;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    TextView txtPhoneDetail;
    ImageView imgPhoneDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtPhoneDetail = findViewById(R.id.txtPhoneDetail);
        imgPhoneDetail = findViewById(R.id.imgPhoneDetail);
        Intent intent = getIntent();
        int img_data = intent.getIntExtra("imgPhone" , 0);
        String txt_data = intent.getStringExtra("txtPhone");
        txtPhoneDetail.setText(txt_data);
        imgPhoneDetail.setImageResource(img_data);
    }
}