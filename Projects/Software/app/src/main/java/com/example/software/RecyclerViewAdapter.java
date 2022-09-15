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
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.software.provider.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable {
    private List<Task> taskListRecycle = new ArrayList<>();
    private List<Task> taskListRecycleFull; /// copy of list with all items
    Context context;

    public void setTask(List<Task> data){
        this.taskListRecycle = data;
        taskListRecycleFull = new ArrayList<>(data);
    }

    public RecyclerViewAdapter(Context context){
        this.context = context;
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
        if (taskListRecycle.get(position).getStatus().equals("Not Started")){
            holder.status.setTextColor(Color.parseColor("#830000"));
        } else if (taskListRecycle.get(position).getStatus().equals("Completed")){
            holder.status.setTextColor(Color.parseColor("#004614"));
        } else {
            holder.status.setTextColor(Color.parseColor("#0D0C6F"));
        }


        if (taskListRecycle.get(position).getPriority().equals("Critical")){
            holder.cardview.setBackgroundColor(Color.parseColor("#E01E1E")); //#D85251
        } else if (taskListRecycle.get(position).getPriority().equals("High")){
            holder.cardview.setBackgroundColor(Color.parseColor("#FFA100")); //#f47b20
        } else if (taskListRecycle.get(position).getPriority().equals("Medium")){
            holder.cardview.setBackgroundColor(Color.parseColor("#E4F623")); // #ffd200
        } else{
            holder.cardview.setBackgroundColor(Color.parseColor("#1BE000")); // #8FBB09
        }

        int fPosition = position;
        holder.deleteTask.setOnClickListener(new View.OnClickListener() {
            int id = taskListRecycle.get(fPosition).getTaskId();
            String name = taskListRecycle.get(fPosition).getName();
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete " + name)
                        .setMessage("Confirm delete?")
                        .setCancelable(true)
                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mTaskViewModel.deleteById(id);
                            }
                        })
                        .setPositiveButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .show();
            }
        });

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
        public TextView title;
        public TextView description;
        public TextView priority;
        public TextView status;
        public LinearLayout cardview;
        public ImageView deleteTask;

        public ViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.task_title);
            description = itemView.findViewById(R.id.task_description);
            priority = itemView.findViewById(R.id.task_priority);
            status = itemView.findViewById(R.id.task_status);
            cardview = itemView.findViewById(R.id.task_cardview);
            deleteTask = itemView.findViewById(R.id.image_delete);
        }
    }

    public Filter getFilter(){
        return taskListFilter;
    }

    private Filter taskListFilter = new Filter() {

        // run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Task> filteredTaskList = new ArrayList<>();

            if (charSequence.toString().equals("None")){
                filteredTaskList.addAll(taskListRecycleFull);
            } else{
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Task data: taskListRecycleFull){
                    if (data.getTag().toLowerCase().contains(filterPattern)) {
                        filteredTaskList.add(data);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredTaskList;
            return results;
        }
        // runs on a UI thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            taskListRecycle.clear();
            taskListRecycle.addAll((Collection<? extends Task>) filterResults.values);
            notifyDataSetChanged();
        }
    };
}

