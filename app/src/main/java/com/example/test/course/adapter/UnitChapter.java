package com.example.test.course.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.model.Unit;

import java.util.List;

public class UnitChapter extends RecyclerView.Adapter<UnitChapter.ViewHolder> {
    List<Unit> listUnit;
    Context context;

    public UnitChapter(List<Unit> listUnit, Context context) {
        this.listUnit = listUnit;
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
        Unit unit = listUnit.get(position);
        holder.titleUnit.setText(unit.getUnitName());
    }

    @Override
    public int getItemCount() {
        return listUnit.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleUnit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleUnit = itemView.findViewById(R.id.tv_title_unit_chapter);
        }
    }
}
