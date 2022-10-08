package com.example.software;

import static com.example.software.SprintOverview.mTaskViewModel;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.software.provider.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SprintRecyclerViewAdapter extends RecyclerView.Adapter<SprintRecyclerViewAdapter.ViewHolder> {
    List<Task> taskListRecycle = new ArrayList<>();
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

        int fPosition = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final View view1 = LayoutInflater.from(context).inflate(R.layout.log_time_spent, null);
                builder.setView(view1);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                final EditText logName = view1.findViewById(R.id.logName);
                final EditText logSP = view1.findViewById(R.id.logSP);
                final EditText logDesc = view1.findViewById(R.id.logDesc);

                // Dropdown list Values
                Spinner logPriority = (Spinner) view1.findViewById(R.id.logPriority);
                ArrayAdapter<String> priorityAdapter = new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_1, context.getResources().getStringArray(R.array.priority));
                priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                logPriority.setAdapter(priorityAdapter);

                Spinner logStatus = (Spinner) view1.findViewById(R.id.logStatus);
                ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_1, context.getResources().getStringArray(R.array.status));
                statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                logStatus.setAdapter(statusAdapter);

                Spinner logAssigned = (Spinner) view1.findViewById(R.id.logAssigned);
                ArrayAdapter<String> assignAdapter = new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_1, context.getResources().getStringArray(R.array.assigned));
                assignAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                logAssigned.setAdapter(assignAdapter);

                Spinner logTag = (Spinner) view1.findViewById(R.id.logTag);
                ArrayAdapter<String> tagAdapter = new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_1, context.getResources().getStringArray(R.array.tags));
                tagAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                logTag.setAdapter(tagAdapter);

                RadioGroup editCategory = (RadioGroup) view1.findViewById(R.id.logCategory);

//                set selected task values
                String Category = taskListRecycle.get(fPosition).getCategory();
                if (Category.equals("User Story")) {
                    editCategory.check(R.id.userStoryLog);
                }
                else if (Category.equals("Bug")) {
                    editCategory.check(R.id.bugLog);
                }
                logName.setText(taskListRecycle.get(fPosition).getName());
                logPriority.setSelection(priorityAdapter.getPosition(taskListRecycle.get(fPosition).getPriority()));
                logStatus.setSelection(statusAdapter.getPosition(taskListRecycle.get(fPosition).getStatus()));
                logAssigned.setSelection(assignAdapter.getPosition(taskListRecycle.get(fPosition).getAssigned()));
                logTag.setSelection(tagAdapter.getPosition(taskListRecycle.get(fPosition).getTag()));
                logSP.setText(String.valueOf(taskListRecycle.get(fPosition).getStoryPoints()));
                logDesc.setText(taskListRecycle.get(fPosition).getDescription());

                int id = taskListRecycle.get(fPosition).getTaskId();

//                editCategory.setEnabled(false);
//                logName.setEnabled(false);
//                logPriority.setEnabled(false);
//                logStatus.setEnabled(false);
//                logAssigned.setEnabled(false);
//                logTag.setEnabled(false);
//                logSP.setEnabled(false);
//                logDesc.setEnabled(false);
//
//                Button editButton = view1.findViewById(R.id.editButton);
//                editButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        editCategory.setEnabled(true);
//                        logName.setEnabled(true);
//                        logPriority.setEnabled(true);
//                        logStatus.setEnabled(true);
//                        logAssigned.setEnabled(true);
//                        logTag.setEnabled(true);
//                        logSP.setEnabled(true);
//                        logDesc.setEnabled(true);
//                    }
//                });

                Button updateButton = view1.findViewById(R.id.updateButton);
                updateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Task task = taskListRecycle.get(fPosition).getTask();
                        RadioGroup eCategory = (RadioGroup) view1.findViewById(R.id.logCategory);
                        RadioButton selected = (RadioButton) view1.findViewById(eCategory.getCheckedRadioButtonId());
                        String category = selected.getText().toString();
                        String name = logName.getText().toString();
                        String priority = logPriority.getSelectedItem().toString();
                        String status = logStatus.getSelectedItem().toString();
                        String assigned = logAssigned.getSelectedItem().toString();
                        String tag = logTag.getSelectedItem().toString();
                        int sp = Integer.valueOf(logSP.getText().toString());
                        String desc = (logDesc.getText().toString());

                        mTaskViewModel.updateTask(id,category,name,desc,priority,status,assigned,tag,sp);

                        alertDialog.dismiss();
                    }
                });

            }
        });


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
