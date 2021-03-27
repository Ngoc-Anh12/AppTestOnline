package com.example.test.document;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.model.Document;
import com.example.test.model.Down;

import java.util.List;

public class AdapterDown extends RecyclerView.Adapter<AdapterDown.ViewHolder> {
    List<Document> downList;
    Context context;
    public void setData(List<Document> downList, Context context){
        this.context = context;
        this.downList = downList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_doc, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return downList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title_doc_down);
        }
    }
}
