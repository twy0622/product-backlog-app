package com.example.software;

import static com.example.software.ProductBacklog.mTaskViewModel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.software.provider.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SprintRecyclerViewAdapter extends RecyclerView.Adapter<SprintRecyclerViewAdapter.ViewHolder> {
    List<Task> taskListRecycle;
    Context context;

    public void setTask(List<Task> data){
        this.taskListRecycle = data;
    }


    public SprintRecyclerViewAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public SprintRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sprint_task_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SprintRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.sprintTaskTitle.setText(taskListRecycle.get(position).getName());
        if (taskListRecycle.get(position).getStatus().equals("Not Started")){
            holder.sprintTaskStatus.setBackgroundColor(Color.parseColor("#830000"));
        } else if (taskListRecycle.get(position).getStatus().equals("Completed")){
            holder.sprintTaskStatus.setBackgroundColor(Color.parseColor("#004614"));
        } else {
            holder.sprintTaskStatus.setBackgroundColor(Color.parseColor("#0D0C6F"));
        }


    }

    @Override
    public int getItemCount() {
        return taskListRecycle.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView sprintTaskTitle;
        public Button sprintTaskStatus;

        public ViewHolder(View itemView){
            super(itemView);
            sprintTaskTitle = itemView.findViewById(R.id.sprint_task_title);
            sprintTaskStatus = itemView.findViewById(R.id.sprint_task_status);
        }
    }
}
