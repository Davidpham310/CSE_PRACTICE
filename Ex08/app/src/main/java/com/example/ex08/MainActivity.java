package com.example.ex08;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editName , editID , editAdditionalInformation;
    CheckBox checkNewsPaper , checkReadBook , checkReadCoding;
    RadioGroup radioGroup;
    Button btnSend;
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

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Question");
        builder.setMessage("Bạn muốn thoát chứ ?");
        builder.setIcon(R.drawable.ic_launcher_background);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.create().show();
    }

    private void Event() {
        Person Obj = new Person();
        Obj.setDiploma("Đại học");
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i != -1){
                    RadioButton selectedRadioButton = findViewById(i);
                    String select = selectedRadioButton.getText().toString();
                    Obj.setDiploma(select);
                }
            }
        });
        ArrayList<String> hoppy = new ArrayList<String>();
        checkNewsPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hoppy.add(checkNewsPaper.getText().toString());
            }
        });
        checkReadBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hoppy.add(checkReadBook.getText().toString());
            }
        });
        checkReadCoding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hoppy.add(checkReadCoding.getText().toString());
            }
        });
        Obj.setHobby(hoppy);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editName.getText().toString().trim().length() < 3){
                    Toast.makeText(MainActivity.this, "Tên người dùng không được để trống và phải có ít nhất 3 ký tự ", Toast.LENGTH_SHORT).show();
                }
                else {
                    Obj.name = editName.getText().toString().trim();
                    Obj.id = editID.getText().toString().trim();


                    String list = editAdditionalInformation.getText().toString();
                    Obj.setSupplement(list);

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("thông tin cá nhân");
                    builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    String msg = "Họ và tên : " + Obj.getName() + "\n"
                            + "CMND : " + Obj.getId() + "\n"
                            + Obj.getDiploma() + "\n"
                            + Obj.getHobby() + "\n"
                            + "--------------------\n"
                            + "Thông tin bổ sung\n"
                            + Obj.getSupplement();
                    builder.setMessage(msg);
                    builder.create().show();
                }
            }
        });
    }

    private void Map() {
        editName = findViewById(R.id.editName);
        editID = findViewById(R.id.editID);
        editAdditionalInformation = findViewById(R.id.editAdditionalInformation);
        checkNewsPaper = findViewById(R.id.checkNewsPaper);
        checkReadBook = findViewById(R.id.checkReadBook);
        checkReadCoding = findViewById(R.id.checkReadCoding);
        btnSend = findViewById(R.id.btnSend);
        radioGroup = findViewById(R.id.radioGroup);
    }
}