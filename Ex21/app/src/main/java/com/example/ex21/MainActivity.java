package com.example.ex21;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ex21.models.Student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editIDClass , editClassName , editValue;
    Button btnInsert  , btnDelete , btnUpdate , btnQuery;
    ListView listView;
    Student student ;
    static String index;
    static ArrayList<String> arr;
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String result = arr.get(i);
                String[] parts = result.split("-");
                String input = parts[0].trim();
                Cursor data = student.getDataByID(input);
                if (data != null) {
                    Log.d("loi", "tìm thấy sinh viên với ID: " + input);
                    displayDataByID(data);
                    index = input;
                } else {
                    Log.d("loi", "Không tìm thấy sinh viên với ID: " + input);
                }
                data.close();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Xác nhận");
                builder.setMessage("Bạn chắc chắn muốn xóa chứ ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        student.deleteStudent(index);
                        displayData(student.getAll());
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editClassName.getText().toString();
                int value = Integer.parseInt(editValue.getText().toString());
                student.updateStudent(index , name , value);
                displayData(student.getAll());
            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editIDClass.getText().toString().isEmpty() && !editClassName.getText().toString().isEmpty() && !editValue.getText().toString().isEmpty()){
                    String id = editIDClass.getText().toString();
                    String className = editClassName.getText().toString();
                    int value = Integer.parseInt(editValue.getText().toString());
                    insertData(id , className , value);
                }
            }
        });
    }

    private void displayDataByID(Cursor data) {
        if (data.moveToFirst()) {  // Di chuyển con trỏ tới bản ghi đầu tiên
            String id = data.getString(data.getColumnIndexOrThrow("id"));  // Lấy giá trị cột id
            String name = data.getString(data.getColumnIndexOrThrow("name"));  // Lấy giá trị cột name
            int value = data.getInt(data.getColumnIndexOrThrow("value"));  // Lấy giá trị cột value

            // Đặt giá trị vào các trường EditText
            editIDClass.setText(id);
            editClassName.setText(name);
            editValue.setText(String.valueOf(value));  // Phải chuyển từ int sang String
        } else {
            Log.d("loi", "Không có dữ liệu");
        }

        // Đóng con trỏ sau khi sử dụng
        data.close();
    }


    private void insertData(String id, String className, int value) {
        student.addStudent(id , className , value);
        displayData(student.getAll());
    }

    private void displayData(Cursor data) {
        if(data.getCount() == 0){
            Log.d("loi" , "Không co dữ liệu");
        }else {
            arr.clear();
            while (data.moveToNext()){
                String id = data.getString(0);
                String name = data.getString(1);
                int value = data.getInt(2);
                String result = String.format("%s - %s - %d" , id , name , value);
                arr.add(result);
            }
            listView.setAdapter(new ArrayAdapter<>(this , android.R.layout.simple_list_item_1 , arr));
        }
        data.close();
    }
    private void Map() {
        editClassName = findViewById(R.id.editClass_Name);
        editIDClass = findViewById(R.id.editID_Class);
        editValue = findViewById(R.id.editValue);
        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnQuery = findViewById(R.id.btnquery);
        listView = findViewById(R.id.listView);
        student = new Student(this);
        arr = new ArrayList<String>();
        displayData(student.getAll());
    }
}