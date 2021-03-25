package com.example.test.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.test.R;
import com.example.test.account.AccountActivity;
import com.example.test.course.CourseActivity;
import com.example.test.document.DocumentActivity;
import com.example.test.online_course.OnlineCourseActivity;
import com.example.test.test.TestActivity;

public class HomeActivity extends AppCompatActivity {
    View layout;
    LinearLayout btnCourse, btnTest, btnDoc, btnAcc, btnOnline;
    RecyclerView recyclerNews;
    NewsAdapter newsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        setOnclick();
        initAdapter();
    }

    private void initAdapter() {
        newsAdapter = new NewsAdapter(this);
        recyclerNews.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerNews.setLayoutManager(linearLayoutManager);
        recyclerNews.setAdapter(newsAdapter);

    }

    private void setOnclick() {
        btnCourse.setOnClickListener(view ->{
            Intent interCourse = new Intent(HomeActivity.this, CourseActivity.class);
            startActivity(interCourse);
        });

        btnTest.setOnClickListener(view ->{
            Intent intentTest = new Intent(HomeActivity.this, TestActivity.class);
            startActivity(intentTest);
        });

        btnAcc.setOnClickListener(view ->{
            Intent intentAcc = new Intent(HomeActivity.this, AccountActivity.class);
            startActivity(intentAcc);
        });
        btnOnline.setOnClickListener(view ->{
            Intent intentAcc = new Intent(HomeActivity.this, OnlineCourseActivity.class);
            startActivity(intentAcc);
        });


    }

    private void init() {
        layout = findViewById(R.id.layout);
        btnCourse = layout.findViewById(R.id.ll_course);
        btnTest = layout.findViewById(R.id.ll_test);
     //   btnDoc = layout.findViewById(R.id.ll_document);
        btnAcc = layout.findViewById(R.id.ll_acc);
        btnOnline = layout.findViewById(R.id.ll_online_course);
        recyclerNews = findViewById(R.id.recycler_news);

    }
}