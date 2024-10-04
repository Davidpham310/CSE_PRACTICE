package com.example.ex19;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.ViewHolder> {
    List<Phone> data;
    Context context;

    public PhoneAdapter(List<Phone> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_phone , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Phone phone = data.get(position);
        holder.txtPhone.setText(phone.getTxtphone());
        holder.imgPhone.setImageResource(phone.getImgPhone());
        holder.imgPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext() , MainActivity2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle bundle = new Bundle();
                bundle.putInt("imgPhone" , phone.getImgPhone());
                bundle.putString("txtPhone" , phone.getTxtphone());
                intent.putExtras(bundle);
                context.getApplicationContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtPhone;
        ImageView imgPhone;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPhone = itemView.findViewById(R.id.txtPhone);
            imgPhone = itemView.findViewById(R.id.imgPhone);

        }

    }
}
