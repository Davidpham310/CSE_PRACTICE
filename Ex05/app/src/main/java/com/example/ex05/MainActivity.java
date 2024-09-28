package com.example.ex05;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText editName , editHeight , editWeight , editBMI , editDiagnosis;
    Button btnBMI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Map();
        Event();
    }

    private void Event() {
        btnBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editWeight.getText().toString().isEmpty() && !editHeight.getText().toString().isEmpty() && !editName.getText().toString().isEmpty()) {
                    double weight = Double.parseDouble(editWeight.getText().toString());
                    double height = Double.parseDouble(editHeight.getText().toString());
                    double result = weight / (height * height);
                    String bmi;
                    if (result > 35) {
                        bmi = "người béo phì độ III";
                    } else if (result >= 30) {
                        bmi = "người béo phì độ II";
                    } else if (result >= 25) {
                        bmi = "người béo phì độ I";
                    } else if (result >= 18) {
                        bmi = "người bình thường";
                    } else {
                        bmi = "người gầy";
                    }
                    editBMI.setText(String.format(Locale.UK, "%.2f", result));
                    editDiagnosis.setText(editName.getText().toString() + "_" + bmi);
                }
                else {
                    Toast.makeText(MainActivity.this, "Vui lòng điền đầy đủ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Map() {
        editName = findViewById(R.id.editName);
        editHeight = findViewById(R.id.editHeight);
        editWeight = findViewById(R.id.editWeight);
        editBMI = findViewById(R.id.editBMI);
        editDiagnosis = findViewById(R.id.editDiagnosis);
        btnBMI = findViewById(R.id.btnBMI);
    }
}