package com.example.jobscheduler;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

    public class MyTaskAdapter extends RecyclerView.Adapter<MyTaskAdapter.MyViewHolder> {
    List<MyTask> tList;
    Context context;

    public MyTaskAdapter(List<MyTask> tList, Context context) {
        this.tList = tList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =  LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.my_task_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTitle.setText(tList.get(position).getTitle());
        holder.tvDesc.setText(tList.get(position).getDesc());
        holder.tvDue.setText(tList.get(position).getDue());

        final String getTitleTask = tList.get(position).getTitle();
        final String getDescTask = tList.get(position).getDesc();
        final String getDueTask = tList.get(position).getDue();
        final String getKeyMyTask = tList.get(position).getKeyMyTask();

//        Log.d("TESTING","Value Of List"+tList.get(0).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,EditMyTaskActivity.class);
                intent.putExtra("titleMyTask", getTitleTask);
                intent.putExtra("DescMyTask", getDescTask);
                intent.putExtra("DueMyTask", getDueTask);
                intent.putExtra("keyMyTask", getKeyMyTask);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvDesc,tvDue,keyMyTask;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.taskTitle);
            tvDesc = itemView.findViewById(R.id.taskDeskripsi);
            tvDue = itemView.findViewById(R.id.taskDue);

        }
    }
}
