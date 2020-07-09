package com.example.jobscheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AssignedAdapter extends RecyclerView.Adapter<AssignedAdapter.MyViewHolder> {

    List<Assigned> aList;
    Context context;

    public AssignedAdapter(List<Assigned> aList, Context context) {
        this.aList = aList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =  LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.assigned_list,parent,false);
        return new AssignedAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTitle.setText(aList.get(position).getTitle());
        holder.tvDesc.setText(aList.get(position).getDesc());
        holder.assignedTo.setText(aList.get(position).getAssigned());
        holder.timePicker.setText(aList.get(position).getTime());
        holder.datePicker.setText(aList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return aList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvDesc,assignedTo,datePicker, timePicker;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.taskTitle);
            tvDesc = itemView.findViewById(R.id.taskDeskripsi);
            assignedTo = itemView.findViewById(R.id.assignedTo);
            datePicker = itemView.findViewById(R.id.dateDue);
            timePicker = itemView.findViewById(R.id.timeDue);
        }
    }

}
