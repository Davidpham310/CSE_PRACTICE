package com.example.ex17;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView txtDate;
    EditText editWork , editHour , editMinute ;
    Button btnAdd;
    ListView listView;
    ArrayList<String> arr;
    ArrayAdapter<String> adapter;
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
        arr = new ArrayList<>();
        adapter = new ArrayAdapter<>(this , android.R.layout.simple_list_item_1 , arr);
        listView.setAdapter(adapter);
        Map();
        getDate();
        Event();
    }

    private void getDate() {
        Date currentDate = new Date();

        // Tạo một đối tượng SimpleDateFormat với mẫu dd/MM/yyyy
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        // Định dạng đối tượng Date theo mẫu và in ra kết quả
        String formattedDate = formatter.format(currentDate);
        txtDate.setText("Hôm nay : " + formattedDate);
    }

    private void Event() {
        editHour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(Integer.parseInt(charSequence.toString()) < 24 && Integer.parseInt(charSequence.toString()) > 0){
                    editHour.setText(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editMinute.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(Integer.parseInt(charSequence.toString()) < 59 && Integer.parseInt(charSequence.toString()) > 0){
                    editMinute.setText(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editHour.getText().toString().isEmpty() || editMinute.getText().toString().isEmpty() || editWork.getText().toString().isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Lỗi thông tin ");
                    builder.setMessage("Không được để trông thông tin");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.show();
                }else {
                    String work = editWork.getText().toString();
                    String hour = editHour.getText().toString();
                    String minute = editMinute.getText().toString();
                    arr.add(work + " - " + hour + " : " + minute);
                    adapter.notifyDataSetChanged();
                    listView.setAdapter(adapter);
                    editWork.setText("");
                    editHour.setText("");
                    editMinute.setText("");
                }
            }
        });
    }

    private void Map() {
        txtDate = findViewById(R.id.txtDate);
        editHour = findViewById(R.id.editHour);
        editMinute = findViewById(R.id.editMinute);
        editWork = findViewById(R.id.txtWork);
        btnAdd = findViewById(R.id.btnAdd);
        listView = findViewById(R.id.listWork);
    }
}