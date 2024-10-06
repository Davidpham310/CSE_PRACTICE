package com.example.ex23;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<List_item> myList;
    ArrAdapter myAdapter;
    String title = "";
    String img = "";
    String info = "";
    String URL = "https://vnexpress.net/rss/tin-noi-bat.rss";
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
        lv = findViewById(R.id.list_item);
        myList = new ArrayList<>();
        myAdapter = new ArrAdapter(MainActivity.this , myList);
        lv.setAdapter(myAdapter);
        LoadData task = new LoadData();
        task.execute();
    }


    // Tạo tiến trình chạy ngầm
    class LoadData extends AsyncTask<Void , Void , Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                // Tạo đối tượng Parser để chứa và phân tích dữ liệu
                XmlPullParserFactory fc = XmlPullParserFactory.newInstance();
                XmlPullParser xmlPullParser = fc.newPullParser();


                // Lấy dữ liệu XML từ url
                XMLParser xmlParser = new XMLParser();
                String xml = xmlParser.getXmlFromUrl(URL);

                // Copy từ String xml vào đối tượng xmlPullParser
                xmlPullParser.setInput(new StringReader(xml));

                // Bắt đầu duyệt
                int index = -1;
                String node = "";
                while (index != XmlPullParser.END_DOCUMENT) {
                    index = xmlPullParser.next();
                    switch (index) {
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            node = xmlPullParser.getName();
                            if (node.equals("item")) {
                                title = "";
                                img = "";
                                info = "";
                            } else if (node.equals("title")) {
                                title = xmlPullParser.nextText();
                            } else if (node.equals("description")) {
                                // Lấy nội dung của thẻ description
                                info = xmlPullParser.nextText();

                                // Sử dụng Jsoup để phân tích HTML trong phần info
                                Document doc = Jsoup.parse(info);

                                // Tìm thẻ img và lấy URL của ảnh
                                Elements imgElements = doc.select("img");
                                if (imgElements.size() > 0) {
                                    Element imgElement = imgElements.first();
                                    img = imgElement.attr("src");  // Lấy URL của ảnh
                                }

                                // Lấy nội dung văn bản (bỏ phần HTML)
                                info = doc.text();
                            }
                            Log.d("loi" , title + " " + img + " " + info );
                            break;
                        case XmlPullParser.END_TAG:
                            node = xmlPullParser.getName();
                            if (node.equals("item")) {
                                List_item listItem = new List_item(img , title , info);
                                myList.add(listItem);
                            }
                            break;
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            Log.d("loi", "Size of myList: " + myList.size());
            myAdapter.notifyDataSetChanged();
        }
    }
}