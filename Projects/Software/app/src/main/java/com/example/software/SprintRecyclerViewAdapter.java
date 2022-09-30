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
                final View view1 = LayoutInflater.from(context).inflate(R.layout.edit_task, null);
                builder.setView(view1);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                final EditText editName = view1.findViewById(R.id.editName);
                final EditText editSP = view1.findViewById(R.id.editSP);
                final EditText editDesc = view1.findViewById(R.id.editDesc);

                // Dropdown list Values
                Spinner editPriority = (Spinner) view1.findViewById(R.id.editPriority);
                ArrayAdapter<String> priorityAdapter = new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_1, context.getResources().getStringArray(R.array.priority));
                priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                editPriority.setAdapter(priorityAdapter);

                Spinner editStatus = (Spinner) view1.findViewById(R.id.editStatus);
                ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_1, context.getResources().getStringArray(R.array.status));
                statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                editStatus.setAdapter(statusAdapter);

                Spinner editAssigned = (Spinner) view1.findViewById(R.id.editAssigned);
                ArrayAdapter<String> assignAdapter = new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_1, context.getResources().getStringArray(R.array.assigned));
                assignAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                editAssigned.setAdapter(assignAdapter);

                Spinner editTag = (Spinner) view1.findViewById(R.id.editTag);
                ArrayAdapter<String> tagAdapter = new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_1, context.getResources().getStringArray(R.array.tags));
                tagAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                editTag.setAdapter(tagAdapter);

                RadioGroup editCategory = (RadioGroup) view1.findViewById(R.id.editCategory);

//                set selected task values
                String Category = taskListRecycle.get(fPosition).getCategory();
                if (Category.equals("User Story")) {
                    editCategory.check(R.id.userStoryEdit);
                }
                else if (Category.equals("Bug")) {
                    editCategory.check(R.id.bugEdit);
                }
                editName.setText(taskListRecycle.get(fPosition).getName());
                editPriority.setSelection(priorityAdapter.getPosition(taskListRecycle.get(fPosition).getPriority()));
                editStatus.setSelection(statusAdapter.getPosition(taskListRecycle.get(fPosition).getStatus()));
                editAssigned.setSelection(assignAdapter.getPosition(taskListRecycle.get(fPosition).getAssigned()));
                editTag.setSelection(tagAdapter.getPosition(taskListRecycle.get(fPosition).getTag()));
                editSP.setText(String.valueOf(taskListRecycle.get(fPosition).getStoryPoints()));
                editDesc.setText(taskListRecycle.get(fPosition).getDescription());

                int id = taskListRecycle.get(fPosition).getTaskId();

                editCategory.setEnabled(false);
                editName.setEnabled(false);
                editPriority.setEnabled(false);
                editStatus.setEnabled(false);
                editAssigned.setEnabled(false);
                editTag.setEnabled(false);
                editSP.setEnabled(false);
                editDesc.setEnabled(false);

                Button editButton = view1.findViewById(R.id.editButton);
                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editCategory.setEnabled(true);
                        editName.setEnabled(true);
                        editPriority.setEnabled(true);
                        editStatus.setEnabled(true);
                        editAssigned.setEnabled(true);
                        editTag.setEnabled(true);
                        editSP.setEnabled(true);
                        editDesc.setEnabled(true);
                    }
                });

                Button saveButton = view1.findViewById(R.id.saveButton);
                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Task task = taskListRecycle.get(fPosition).getTask();
                        RadioGroup eCategory = (RadioGroup) view1.findViewById(R.id.editCategory);
                        RadioButton selected = (RadioButton) view1.findViewById(eCategory.getCheckedRadioButtonId());
                        String category = selected.getText().toString();
                        String name = editName.getText().toString();
                        String priority = editPriority.getSelectedItem().toString();
                        String status = editStatus.getSelectedItem().toString();
                        String assigned = editAssigned.getSelectedItem().toString();
                        String tag = editTag.getSelectedItem().toString();
                        int sp = Integer.valueOf(editSP.getText().toString());
                        String desc = (editDesc.getText().toString());

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