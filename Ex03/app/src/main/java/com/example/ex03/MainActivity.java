package com.example.ex03;

import android.annotation.SuppressLint;
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
    EditText editA , editB , editResult ;
    Button btnSum , btnSub , btnMul , btnDiv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Map();
        Event();
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    private void Event() {
        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Caculator(editA , editB , editResult , "+");
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Caculator(editA , editB , editResult , "-");
            }
        });
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Caculator(editA , editB , editResult , "*");
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Caculator(editA , editB , editResult , "/");
            }
        });

    }

    private void Caculator(EditText editA, EditText editB, EditText editResult, String s) {
        if(editA.getText().toString().isEmpty() || editB.getText().toString().isEmpty()){
            Toast.makeText(this, "Các số A và B không được rỗng", Toast.LENGTH_SHORT).show();
        }
        else {
            double a = Double.parseDouble(editA.getText().toString());
            double b = Double.parseDouble(editB.getText().toString());
            switch (s){
                case "+" :
                    editResult.setText(String.format("%.1f + %.1f = %.1f" , a , b , a + b));
                    break;
                case "-" :
                    editResult.setText(String.format("%.1f - %.1f = %.1f" , a , b , a - b));
                    break;
                case "*" :
                    editResult.setText(String.format("%.1f * %.1f = %.1f" , a , b , a * b));
                    break;
                case "/" :
                    if(b == 0){
                        editB.setText("");
                        Toast.makeText(this, "B phải khác 0", Toast.LENGTH_SHORT).show();
                    }else {
                        editResult.setText(String.format("%.1f / %.1f = %.1f" , a , b , a / b));
                    }
                    break;
            }
        }
    }

    private void Map() {
        editA = findViewById(R.id.editA);
        editB = findViewById(R.id.editB);
        editResult = findViewById(R.id.editResult);
        btnSum = findViewById(R.id.btnSum);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
    }


}