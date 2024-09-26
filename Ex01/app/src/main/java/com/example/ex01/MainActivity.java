package com.example.ex01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText editA , editB , editSum ;
    Button btnSum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        editA = findViewById(R.id.editA);
        editB = findViewById(R.id.editB);
        editSum = findViewById(R.id.editSum);
        btnSum = findViewById(R.id.btnSum);
        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editA.getText().toString().isEmpty() && !editB.getText().toString().isEmpty()){
                    int sum = Integer.parseInt(editA.getText().toString()) + Integer.parseInt(editB.getText().toString());
                    editSum.setText(String.valueOf(sum));
                }
                else {
                    Toast.makeText(MainActivity.this, "Nhập số A và B không được rỗng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}