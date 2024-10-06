package com.example.ex23;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ArrAdapter  extends ArrayAdapter<List_item> {
    private Activity context;
    private ArrayList<List_item> arr;

    public ArrAdapter(Activity context, ArrayList<List_item> arr) {
        super(context, R.layout.list_item, arr);
        this.context = context;
        this.arr = arr;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(R.layout.list_item, null);

        List_item lst = arr.get(position);

        ImageView imageView = convertView.findViewById(R.id.img);
        TextView txtTitle = convertView.findViewById(R.id.txtTitle);
        TextView txtInfo = convertView.findViewById(R.id.txtInfo);
        Glide.with(context)
                .load(lst.getImg())
                .into(imageView);
        txtTitle.setText(lst.getTitle());
        txtInfo.setText(lst.getInfo());
        return convertView;
    }
}
