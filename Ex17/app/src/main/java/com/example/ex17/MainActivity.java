package com.example.ex17;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView txtDate;
    EditText editWork , editHour , editMinute ;
    Button btnAdd;
    ListView listView;
    static ArrayList<String> arr;
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
        Map();
        getDate();
        getData();
        Event();
    }

    private void getData() {
        SharedPreferences sharedPref = this.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        String json = sharedPref.getString("products", null);
        if (json != null) {
            try {
                Type type = new TypeToken<ArrayList<String>>() {}.getType();
                Gson gson = new Gson();

                ArrayList<String> data = gson.fromJson(json, type);
                arr.clear();
                arr = data;
                adapter = new ArrayAdapter<>(this , android.R.layout.simple_list_item_1 , arr);
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
            }catch (Exception e){
                Log.d("loi" , e.getMessage().toString());
            }

        }

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
                try {
                    String input = charSequence.toString();
                    if (!input.isEmpty()) {
                        int time = Integer.parseInt(input);
                        if (time > 24) {
                            Toast.makeText(MainActivity.this, "Giờ chỉ được từ 00 đến 23", Toast.LENGTH_SHORT).show();
                            editHour.setText("");  // Xóa nội dung
                        }
                    }
                } catch (NumberFormatException e) {
                    // Nếu không phải là số
                    Toast.makeText(MainActivity.this, "Vui lòng nhập chính xác", Toast.LENGTH_SHORT).show();
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
                try {
                    String input = charSequence.toString();
                    if (!input.isEmpty()) {
                        int time = Integer.parseInt(input);
                        if (time > 60) {
                            Toast.makeText(MainActivity.this, "Phút chỉ được từ 00 đến 59", Toast.LENGTH_SHORT).show();
                            editMinute.setText("");  // Xóa nội dung
                        }
                    }
                } catch (NumberFormatException e) {
                    // Nếu không phải là số
                    Toast.makeText(MainActivity.this, "Vui lòng nhập chính xác", Toast.LENGTH_SHORT).show();
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
                    saveProducts();
                }
            }
        });
    }

    private void saveProducts() {
        SharedPreferences sharedPref = this.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arr);
        editor.putString("products", json);
        editor.apply();
    }

    private void Map() {
        txtDate = findViewById(R.id.txtDate);
        editHour = findViewById(R.id.editHour);
        editMinute = findViewById(R.id.editMinute);
        editWork = findViewById(R.id.editWork);
        btnAdd = findViewById(R.id.btnAdd);
        listView = findViewById(R.id.listWork);
    }
}