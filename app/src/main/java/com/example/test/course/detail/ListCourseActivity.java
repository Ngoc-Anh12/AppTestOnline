package com.example.test.course.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.example.test.R;
import com.example.test.core.retrofit.ApiClient;
import com.example.test.core.retrofit.RequestApi;
import com.example.test.course.adapter.ListCourseAdapter;
import com.example.test.model.ListCourse;
import com.example.test.model.ResponseDTO;
import com.example.test.utils.AppData;
import com.example.test.utils.DataServices;
import com.example.test.utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListCourseActivity extends AppCompatActivity {
    RecyclerView recycleViewList;
    ListCourseAdapter listCourseAdapter;
    List<ListCourse> listCourses;
    final RequestApi requestAPI = ApiClient.getClient().create(RequestApi.class);
    int SUBJECT_ID;
    int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_course);
        Intent intent=getIntent();
        SUBJECT_ID = intent.getIntExtra("subject_id", 0);

        //userId = AppData.currentUser.getUserId();

        init();
        initAdapter();
    }

    private void initAdapter() {
        listCourses = new ArrayList<ListCourse>();
        listCourseAdapter = new ListCourseAdapter(this);
        callAPI();
        recycleViewList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recycleViewList.setLayoutManager(linearLayoutManager);
        setOnclick();
    }

    private void setOnclick() {
        recycleViewList.addOnItemTouchListener(new RecyclerItemClickListener(this, recycleViewList, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ListCourseActivity.this, DetailCourseActivity.class);
                intent.putExtra("courseId",listCourses.get(position).getCourseId());
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

    private void callAPI() {
        String token  = DataServices.getInstance(this).getToken();
       requestAPI.getListCourse(15, 2, token).enqueue(new Callback<ResponseDTO<List<ListCourse>>>() {
           @Override
           public void onResponse(Call<ResponseDTO<List<ListCourse>>> call, Response<ResponseDTO<List<ListCourse>>> response) {
               if(response.body() != null && response.body().data != null && response.body().error==0){
                   for (ListCourse listCourse : response.body().data){
                       listCourses.add(listCourse);
                       listCourseAdapter.setData(listCourses, ListCourseActivity.this);
                       recycleViewList.setAdapter(listCourseAdapter);
                   }
               }
           }

           @Override
           public void onFailure(Call<ResponseDTO<List<ListCourse>>> call, Throwable t) {

           }
       });
    }

    private void init() {
        recycleViewList = findViewById(R.id.recycle_view_list_course);
    }
}