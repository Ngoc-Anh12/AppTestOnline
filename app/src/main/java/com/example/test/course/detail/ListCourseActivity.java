package com.example.test.course.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.test.R;
import com.example.test.core.retrofit.ApiClient;
import com.example.test.core.retrofit.RequestApi;
import com.example.test.course.adapter.ListCourseAdapter;
import com.example.test.model.ListCourse;
import com.example.test.model.ResponseDTO;
import com.example.test.utils.AppData;

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

        userId = AppData.currentUser.getUserId();
        init();
        initAdapter();
    }

    private void initAdapter() {
        listCourses = new ArrayList<ListCourse>();
        callAPI();
        listCourseAdapter = new ListCourseAdapter(listCourses, this);
        recycleViewList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recycleViewList.setLayoutManager(linearLayoutManager);
        recycleViewList.setAdapter(listCourseAdapter);
    }

    private void callAPI() {
        requestAPI.getListCourse(SUBJECT_ID, userId).enqueue(new Callback<ResponseDTO<ListCourse>>() {
            @Override
            public void onResponse(Call<ResponseDTO<ListCourse>> call, Response<ResponseDTO<ListCourse>> response) {
                System.out.println(response);
            }

            @Override
            public void onFailure(Call<ResponseDTO<ListCourse>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    private void init() {
        recycleViewList = findViewById(R.id.recycle_view_list_course);
    }
}