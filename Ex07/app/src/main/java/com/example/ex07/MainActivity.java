package com.example.ex07;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView txtResult;
    EditText editA , editB , editC;
    Button btnContinue , btnSolve , btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Map();
        Event();
    }

    private void Event() {
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editA.setText("");
                editB.setText("");
                editC.setText("");
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editA.getText().toString().isEmpty() && !editB.getText().toString().isEmpty() && !editC.getText().toString().isEmpty()){
                    double a = Double.parseDouble(editA.getText().toString());
                    double b = Double.parseDouble(editB.getText().toString());
                    double c = Double.parseDouble(editC.getText().toString());
                    Solve(a , b , c);
                }else {
                    Toast.makeText(MainActivity.this, "A , B , C không được rỗng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Solve(double a, double b, double c) {
        String result = "";
        if(a == 0){
            if (b == 0){
                if (c == 0){
                    result = "PT vô số nghiệm";
                }
                else {
                    result = "PT vô nghiệm";
                }
            }else {
                result = "PT có 1 nghiệm : x = " + (-c/b);
            }
        }
        else {
            double delta = b * b - 4 * a * c;
            if(delta < 0){
                result = "PT vô nghiệm";
            }
            else if(delta == 0){
                result = "PT có nghiệm kép : x1 = x2 = " +  (-b/2*a);
            }
            else {
                result = String.format("PT có 2 nghiệm : x1 =  %.2f ; x2 = %.2f" ,(-b + Math.sqrt(delta))/ (2 * a) , (-b - Math.sqrt(delta))/ (2 * a)  );
            }
        }
        txtResult.setText(result);
    }

    private void Map() {
        editA = findViewById(R.id.editA);
        editB = findViewById(R.id.editB);
        editC = findViewById(R.id.editC);
        btnContinue = findViewById(R.id.btnContinue);
        btnSolve = findViewById(R.id.btnSolve);
        btnExit = findViewById(R.id.btnExit);
        txtResult = findViewById(R.id.txtResult);
    }
}