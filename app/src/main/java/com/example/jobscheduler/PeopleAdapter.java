package com.example.jobscheduler;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.MyViewHolder> {

    List<People> pList;
    Context context;

    public PeopleAdapter(List<People> pList, Context context) {
        this.pList = pList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.people_list_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.imageView.setImageResource(pList.get(position).getImage());
//        holder.textView.setText(pList.get(position).getText());
        holder.tvName.setText(pList.get(position).getUsername());
        holder.tvOccup.setText(pList.get(position).getOccup());
//        holder.tvDesc.setText(tList.get(position).getDesc());
//        holder.tvDue.setText(tList.get(position).getDue());
    }

    @Override
    public int getItemCount() {
        return pList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
//        ImageView imageView;
        TextView tvName, tvOccup;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

//            imageView = itemView.findViewById(R.id.ivPeople);
            tvName = itemView.findViewById(R.id.tvName);
            tvOccup = itemView.findViewById(R.id.tvOccup);
        }

    }


}
