package com.example.test.course.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.model.ListCourse;

import java.util.List;

public class ListCourseAdapter extends RecyclerView.Adapter<ListCourseAdapter.ViewHolder> {
    private List<ListCourse> listCourses;
    private Context context;

    public ListCourseAdapter(List<ListCourse> listCourses, Context context) {
        this.listCourses = listCourses;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_course, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListCourse listCourse = listCourses.get(position);
        holder.tv_title_course.setText(listCourse.getCourseName());
        holder.tv_over_view.setText(listCourse.getOverView());
        holder.tv_date_start.setText(listCourse.getDateStart());
        holder.tv_date_end.setText(listCourse.getDateEnd());
        holder.tv_time_start.setText(listCourse.getTimeStart());
        holder.tv_time_end.setText(listCourse.getTimeEnd());
    }

    @Override
    public int getItemCount() {
        return listCourses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title_course, tv_over_view, tv_date_start, tv_date_end, tv_time_start, tv_time_end;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title_course = itemView.findViewById(R.id.tv_title_course);
            tv_over_view = itemView.findViewById(R.id.tv_over_view);
            tv_date_start = itemView.findViewById(R.id.tv_date_start);
            tv_date_end = itemView.findViewById(R.id.tv_date_end);
            tv_time_start = itemView.findViewById(R.id.tv_time_start);
            tv_time_end = itemView.findViewById(R.id.tv_time_end);



        }
    }
}
