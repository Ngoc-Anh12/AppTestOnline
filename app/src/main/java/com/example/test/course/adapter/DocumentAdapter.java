package com.example.test.course.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.model.Document;

import java.util.List;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.ViewHolder> {
    List<Document> doc ;
    Context context;
    public void setData(List<Document> doc, Context context){
        this.doc = doc;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_unit_chapter, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Document document = doc.get(position);
        holder.titleChapter.setText(document.getDocumentOfUnitName());
    }

    @Override
    public int getItemCount() {
        return doc.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleChapter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleChapter = itemView.findViewById(R.id.tv_title_unit_chapter);
        }
    }
}
