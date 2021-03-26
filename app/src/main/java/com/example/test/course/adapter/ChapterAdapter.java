package com.example.test.course.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.model.Chapter;
import com.example.test.model.Unit;

import java.util.ArrayList;
import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder> {
    List<Chapter> listChapter;
    Context context;

    public void setData (List<Chapter> listChapter, Context context) {
        this.listChapter = listChapter;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_details_doc, parent, false);
        ViewHolder  viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chapter chapter = listChapter.get(position);
        holder.titleChapter.setText(chapter.getChapterName());
        UnitChapter unitChapter = new UnitChapter(chapter.getUnit(), context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        holder.recycle_view_unit_chapter.setHasFixedSize(true);
        holder.recycle_view_unit_chapter.setLayoutManager(linearLayoutManager);
        holder.recycle_view_unit_chapter.setAdapter(unitChapter);
    }

    @Override
    public int getItemCount() {
        return listChapter.size();
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
