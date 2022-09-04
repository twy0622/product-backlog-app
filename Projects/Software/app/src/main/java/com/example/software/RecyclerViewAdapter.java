package com.example.software;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.software.provider.Task;
import com.example.software.provider.TaskViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    List<Task> taskListRecycle = new ArrayList<>();


    public void setTask(List<Task> data){
        this.taskListRecycle = data;
    }

    public RecyclerViewAdapter(){
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.title.setText(taskListRecycle.get(position).getName() + " - " + taskListRecycle.get(position).getStoryPoints());
        holder.description.setText(" - " + taskListRecycle.get(position).getDescription());
        holder.priority.setText("Priority: " + taskListRecycle.get(position).getPriority());
        holder.status.setText(taskListRecycle.get(position).getStatus());


        if (taskListRecycle.get(position).getPriority().equals("Critical")){
            holder.cardview.setBackgroundColor(Color.parseColor("#e81300"));
        } else if (taskListRecycle.get(position).getPriority().equals("High")){
            holder.cardview.setBackgroundColor(Color.parseColor("#ff7b00"));
        } else if (taskListRecycle.get(position).getPriority().equals("Medium")){
            holder.cardview.setBackgroundColor(Color.parseColor("#eeff00"));
        } else{
            holder.cardview.setBackgroundColor(Color.parseColor("#00ff15"));
        }


//        int newPosition = position;
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            int year = taskListRecycle.get(newPosition).getYear();
//            @Override public void onClick(View v) {
//                ProductBacklog.mTaskViewModel.deleteMovieYear(year);
//                Toast msg = Toast.makeText(v.getContext(), "Movie No." + (newPosition + 1) + " with title: " + movieListRecycle.get(newPosition).getTitle() + " is selected", Toast.LENGTH_SHORT);
////                msg.show();
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return taskListRecycle.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView description;
        public TextView priority;
        public TextView status;
        public LinearLayout cardview;

        public ViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.task_title);
            description = itemView.findViewById(R.id.task_description);
            priority = itemView.findViewById(R.id.task_priority);
            status = itemView.findViewById(R.id.task_status);
            cardview = itemView.findViewById(R.id.task_cardview);
        }
    }
}
