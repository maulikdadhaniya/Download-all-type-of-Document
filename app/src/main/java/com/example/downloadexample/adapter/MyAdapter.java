package com.example.downloadexample.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.downloadexample.ClickEvent;
import com.example.downloadexample.R;
import com.example.downloadexample.response.MyResponse;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<MyResponse.Data> brandList;
    ClickEvent clickEvent;


    public MyAdapter(ArrayList<MyResponse.Data> brandList, ClickEvent clickEvent) {
        this.brandList = brandList;
        this.clickEvent = clickEvent;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_recyclerview, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final MyResponse.Data brandModel = brandList.get(position);
        holder.txt_productName.setText(brandModel.getSubject_name());
        //holder.rl_product.setOnClickListener(v -> attributeClickListner.attClick(brandModel));
        holder.myCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickEvent.myClickEvent(brandModel.getDocument());
            }
        });

    }

    @Override
    public int getItemCount() {
        return brandList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_productName;
        LinearLayout rl_product;
        CardView myCard;

        MyViewHolder(View view) {
            super(view);
            txt_productName = view.findViewById(R.id.mytext);
            myCard = view.findViewById(R.id.myCard);

        }
    }
}

