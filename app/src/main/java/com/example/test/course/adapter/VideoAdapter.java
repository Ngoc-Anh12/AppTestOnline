package com.example.test.course.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.model.Chapter;
import com.example.test.model.Document;
import com.example.test.model.Unit;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    List<Chapter> listVideo;
    Context context;

    public void setData(List<Chapter> listVideo, Context context){
        this.listVideo = listVideo;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_details_doc, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chapter  chapter = listVideo.get(position);
        holder.titleChapter.setVisibility(View.GONE);
        UnitChapter unitChapter = new UnitChapter();
        unitChapter.setData(chapter.getUnit(), context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        holder.recycle_view_unit_chapter.setHasFixedSize(true);
        holder.recycle_view_unit_chapter.setLayoutManager(linearLayoutManager);
        holder.recycle_view_unit_chapter.setAdapter(unitChapter);

      // holder.titleChapter.setText(chapter.getUnit().get(position).getDocument().get(position).getDocumentOfUnitName());
    }

    @Override
    public int getItemCount() {
        return listVideo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleChapter;
        RecyclerView recycle_view_unit_chapter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleChapter = itemView.findViewById(R.id.tv_title_chapter);
            recycle_view_unit_chapter = itemView.findViewById(R.id.recycle_view_unit_chapter);
        }
    }
}
