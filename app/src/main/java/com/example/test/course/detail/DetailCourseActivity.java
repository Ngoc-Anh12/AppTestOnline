package com.example.test.course.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.course.adapter.ViewPagerTabLayoutAdapter;
import com.example.test.course.fragment.ListDocFragment;
import com.example.test.course.fragment.ListLessonFragment;
import com.example.test.home.HomeActivity;
import com.google.android.material.tabs.TabLayout;

public class DetailCourseActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager container;
    ImageButton imgBack, imgHome;
    TextView title;
    View layoutHeader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_coursectivity);
        init();
        setupViewPager(container);
        setOnclick();
        //set title toolbar
        title.setText(R.string.title_toolbar_detail_course);
        imgHome.setVisibility(View.GONE);
    }

    private void setOnclick() {
        imgBack.setOnClickListener(view ->{
            finish();
        });

    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerTabLayoutAdapter adapter = new ViewPagerTabLayoutAdapter(getSupportFragmentManager());
        adapter.addFrag(new ListLessonFragment(), getResources().getString(R.string.title_tab_lesson));
        adapter.addFrag(new ListDocFragment(), getResources().getString(R.string.title_tab_document));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void init() {
        tabLayout = findViewById(R.id.tab_layout);
        container = findViewById(R.id.container);
        layoutHeader = findViewById(R.id.layout_header);
        imgBack = layoutHeader.findViewById(R.id.image_button_back);
        imgHome = layoutHeader.findViewById(R.id.home);
        title = layoutHeader.findViewById(R.id.title);
    }
}