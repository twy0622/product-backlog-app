package com.example.software;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.software.provider.Task;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable {
    private List<Task> taskListRecycle = new ArrayList<>();
    private List<Task> taskListRecycleFull; /// copy of list with all items

    public void setTask(List<Task> data){
        this.taskListRecycle = data;
        taskListRecycleFull = new ArrayList<>(data);
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
    @Override
    public int getItemCount() {
        return taskListRecycle.size();
    }

    public Filter getFilter(){
        return taskListFilter;
    }

    private Filter taskListFilter = new Filter() {

        // run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Task> filteredTaskList = new ArrayList<>();

            if (charSequence.toString().isEmpty()){
                filteredTaskList.addAll(taskListRecycleFull);
            } else{
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Task data: taskListRecycleFull){
                    if (data.getTags().toLowerCase().contains(filterPattern)){
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
