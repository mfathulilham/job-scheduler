package com.example.jobscheduler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {
    List<MyTask> tList;
    Context context;

    public HistoryAdapter(List<MyTask> tList, Context context) {
        this.tList = tList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.history_task_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTitle.setText(tList.get(position).getTitle());
        holder.tvDesc.setText(tList.get(position).getDesc());
        holder.timePicker.setText(tList.get(position).getTime());
        holder.datePicker.setText(tList.get(position).getDate());
//
//        final String getTitleTask = tList.get(position).getTitle();
//        final String getDescTask = tList.get(position).getDesc();
//        final String getTime = tList.get(position).getTime();
//        final String getDate = tList.get(position).getDate();
//        final String getKeyMyTask = tList.get(position).getKeyMyTask();
//
//        Log.d("TESTING","Value Of List"+tList.get(0).getTitle());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context,EditMyTaskActivity.class);
//                intent.putExtra("titleMyTask", getTitleTask);
//                intent.putExtra("DescMyTask", getDescTask);
//                intent.putExtra("DateMyTask", getDate);
//                intent.putExtra("TimeMyTask", getTime);
//                intent.putExtra("keyMyTask", getKeyMyTask);
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return tList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDesc, keyMyTask, datePicker, timePicker;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.taskTitle);
            tvDesc = itemView.findViewById(R.id.taskDeskripsi);
            datePicker = itemView.findViewById(R.id.dateDue);
            timePicker = itemView.findViewById(R.id.timeDue);
        }
    }
}