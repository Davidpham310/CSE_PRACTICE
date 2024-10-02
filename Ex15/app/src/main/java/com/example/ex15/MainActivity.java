package com.example.ex15;

import static android.content.Intent.ACTION_VIEW;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText editText;
    WebView webView;
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
        btn = findViewById(R.id.btn);
        editText = findViewById(R.id.editText);
        webView = findViewById(R.id.web);
        // Cấu hình WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Kích hoạt JavaScript
        webSettings.setLoadsImagesAutomatically(true); // Tải hình ảnh tự động
        webSettings.setDomStorageEnabled(true); // Hỗ trợ DOM storage
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        // Đảm bảo các liên kết được mở trong WebView thay vì trình duyệt bên ngoài
        webView.setWebViewClient(new WebViewClient());


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://" + editText.getText().toString()));
//                startActivity(intent);
                try{
                    webView.loadUrl("https://" + editText.getText().toString());
                }catch (Exception e){
                    e.getMessage().toString();
                }
            }
        });
    }
}