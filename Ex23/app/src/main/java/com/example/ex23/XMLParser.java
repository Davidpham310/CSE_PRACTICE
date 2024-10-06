package com.example.ex23;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class XMLParser {
    public String getXmlFromUrl(String url){
        String xml = null;
        URL link = null;
        HttpURLConnection connection = null;
        try{
            // Tạo đường link
            link = new URL(url);
            // Tạo kết nối
            connection = (HttpURLConnection) link.openConnection();
            // Lấy dữ liệu  trả về
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            //  Tạo dữ liệu trong bộ đệm
            StringBuilder sb = new StringBuilder();
            String line;
            // Đọc từng dòng trong bộ đệm cho đến khi hết dữ liệu
            while ((line = br.readLine()) != null)
            {
                sb.append(line).append('\n');
            }
            // Lưu trữ kết quả vào chuỗi xml
            xml = sb.toString();
            // Đóng kết nối
            connection.disconnect();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return xml;
    }
}
