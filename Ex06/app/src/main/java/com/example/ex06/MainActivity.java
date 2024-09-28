package com.example.ex06;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnCustom;
    EditText editYear , editCustomYear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Map();
        Event();
    }

    private void Event() {
        btnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editYear.getText().toString().isEmpty()){
                    int value = Integer.parseInt(editYear.getText().toString());
                    String can , chi;
                    String[] a = {"Canh" , "Tân" , "Nhâm" , "Quý" , "Giáp" , "Ất" , "Bính" , "Đinh" , "Mậu" , "Kỷ"};
                    String[] b = {"Thân" , "Dậu" , "Tuất" , "Hợi" , "Tý" , "Sửu" , "Dần" , "Mão" , "Thìn" , "Tỵ" , "Ngọ" , "Mùi"};
                    can = a[value % 10];
                    chi = b[value % 12];
                    editCustomYear.setText(can + " " + chi);
                }
                else {
                    Toast.makeText(MainActivity.this, "Năm sinh không được để trống", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void Map() {
        btnCustom = findViewById(R.id.btnCustom);
        editYear = findViewById(R.id.editYear);
        editCustomYear = findViewById(R.id.editCustomYear);
    }
}