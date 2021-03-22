package com.example.test.course;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.course.detail.DetailCourseActivity;
import com.example.test.login.ViewPagerAdapter;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class CourseActivity extends AppCompatActivity {
    View layout;
    LinearLayout llMath, llEnglish, llHistory, llLiterature;
    TextView titleMath, titleEnglish, titleHistory, titleLiterature , titleToolbar;
    ViewPager viewPager;
    int NUM_PAGES = 2;
    Timer timer;
    final long DELAY_MS = 3000;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.
    CircleIndicator layoutDots;
    ImageButton imageButtonBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        init();
        handle();
        viewPage();
        setOnclick();
    }

    private void setOnclick() {
        imageButtonBack.setOnClickListener(view ->{
            finish();
        });

        llEnglish.setOnClickListener(click ->{
            Intent detailEnglish = new Intent(CourseActivity.this, DetailCourseActivity.class);
            startActivity(detailEnglish);
        });
    }

    private void viewPage() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                viewPager.post(new Runnable() {
                    @Override
                    public void run() {
                        viewPager.setCurrentItem((viewPager.getCurrentItem()+1)%NUM_PAGES);
                    }
                });
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, DELAY_MS,PERIOD_MS);
        layoutDots.setViewPager(viewPager);
    }

    private void handle() {
        titleMath.setText(R.string.title_math);
        titleEnglish.setText(R.string.title_english);
        //titleHistory.setText(R.string.title_history);
        titleLiterature.setText(R.string.title_literature);
        titleToolbar.setText(R.string.title_list_course);
    }

    private void init() {
        layout = findViewById(R.id.layout);
        llMath = layout.findViewById(R.id.ll_course);
        llEnglish = layout.findViewById(R.id.ll_test);
       // llHistory = layout.findViewById(R.id.ll_document);
        llLiterature = layout.findViewById(R.id.ll_acc);
        titleMath = llMath.findViewById(R.id.text_course);
        titleEnglish = llEnglish.findViewById(R.id.text_test);
       // titleHistory = llHistory.findViewById(R.id.text_doc);
        titleLiterature = llLiterature.findViewById(R.id.text_acc);
        viewPager = findViewById(R.id.view_pager);
        layoutDots = findViewById(R.id.layout_dots);
        imageButtonBack = findViewById(R.id.image_button_back);
        titleToolbar = findViewById(R.id.title);
    }
    @Override
    protected void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }
}