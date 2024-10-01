package com.example.ex12;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
    ImageButton btn;
    ImageView imageView;
    private static final int REQUEST_IMAGE_CAPTURE = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btnClick);
        imageView = findViewById(R.id.imageView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Kiểm tra quyền camera
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // Yêu cầu quyền camera
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_IMAGE_CAPTURE);
                } else {
                    // Khởi động camera nếu đã có quyền
                    openCamera();
                }
            }
        });
    }

    // Phương thức khởi động camera
    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @Nullable String[] permissions, @Nullable int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Nếu quyền được cấp, khởi động camera
                openCamera();
            } else {
                // Nếu quyền không được cấp, có thể hiển thị thông báo
                // Bạn có thể thêm thông báo cho người dùng ở đây
            }
        }
    }
}
