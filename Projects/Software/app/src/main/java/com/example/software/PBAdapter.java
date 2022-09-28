package com.example.software;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.software.provider.Task;

import java.util.ArrayList;
import java.util.List;

public class PBAdapter extends RecyclerView.Adapter<PBAdapter.PBViewHolder> {

    private List<Task> pbListRecycle = new ArrayList<>();

    public void setTask(List<Task> data){
        this.pbListRecycle = data;
    }

    public PBAdapter() {}

    @NonNull
    @Override
    public PBViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sprint_allocate_product_backlog,parent,false);
        return new PBViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PBViewHolder holder, int position) {
        holder.pbTitle.setText(pbListRecycle.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return pbListRecycle.size();
    }

    public class PBViewHolder extends RecyclerView.ViewHolder{
        public TextView pbTitle;

        public PBViewHolder(View itemView){
            super(itemView);
            pbTitle = itemView.findViewById(R.id.pbTitle);
        }
    }


}
