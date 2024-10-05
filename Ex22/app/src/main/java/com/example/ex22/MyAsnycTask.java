package com.example.ex22;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyAsnycTask extends AsyncTask<Integer , Integer , Void> {
    Activity context;

    public MyAsnycTask(Activity context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        int max = integers[0];
        int time = 0;
        while (time <= max) {
            try {
                Thread.sleep(1000);  // Dừng 1 giây
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time++;
            publishProgress(time);  // Gửi giá trị mới để cập nhật giao diện
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        ProgressBar progressBar = context.findViewById(R.id.ProgressBar);
        TextView txtProgressBar = context.findViewById(R.id.txtProgressBar);
        int time = values[0];
        progressBar.setProgress(time);
        txtProgressBar.setText(String.valueOf(time) + "%");
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        TextView txtProgressBar = context.findViewById(R.id.txtProgressBar);
        txtProgressBar.setText("Done");
    }
}
