package com.example.ex04;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText editC , editF;
    Button btnConverttoC , btnConverttoF , btnClear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Map();
        Event();
    }

    private void Event() {
        btnConverttoC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editF.getText().toString().isEmpty() && editC.getText().toString().isEmpty() || !editF.getText().toString().isEmpty()  && !editC.getText().toString().isEmpty()){
                    Convert(editF , "toC");
                }
                else {
                    Toast.makeText(MainActivity.this, "Nhiệt độ F không được rỗng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnConverttoF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editC.getText().toString().isEmpty()  && editF.getText().toString().isEmpty() || !editC.getText().toString().isEmpty()  && !editF.getText().toString().isEmpty() ){
                    Convert(editC , "toF");
                }
                else {
                    Toast.makeText(MainActivity.this, "Nhiệt độ C không được rỗng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editC.setText("");
                editF.setText("");
            }
        });
    }

    private void Convert(EditText editText, String str) {
        double result;
        double i = Double.parseDouble(editText.getText().toString());
        switch (str){
            case "toC":
                result = (i - 32) * 5/9;
                editC.setText(String.format(Locale.US ,"%.2f" , result));
                break;
            case "toF":
                result = i * 9/5 + 32;
                editF.setText(String.format(Locale.US , "%.2f" , result));
                break;
        }
    }

    private void Map() {
        editC = findViewById(R.id.editC);
        editF = findViewById(R.id.editF);
        btnConverttoC = findViewById(R.id.btnConverttoC);
        btnConverttoF = findViewById(R.id.btnConverttoF);
        btnClear = findViewById(R.id.btnClear);
    }
}