package com.example.test.course.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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
    ImageView imgBack;
    TextView title;
    View layoutHeader;
    int courseId =0 ;
    ListLessonFragment listLessonFragment;
    ListDocFragment listDocFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_coursectivity);
        init();
        setupViewPager(container);
        setOnclick();
        //set title toolbar
        title.setText(R.string.title_toolbar_detail_course);
        Intent intent = getIntent();
        courseId = intent.getIntExtra("courseId",0);
        Bundle bundle = new Bundle();
        bundle.putInt("courseId-fragment",courseId);
        listLessonFragment.setArguments(bundle);
        listDocFragment.setArguments(bundle);
    }

    private void setOnclick() {
        imgBack.setOnClickListener(view ->{
            finish();
        });

    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerTabLayoutAdapter adapter = new ViewPagerTabLayoutAdapter(getSupportFragmentManager());
        adapter.addFrag(listLessonFragment, getResources().getString(R.string.title_tab_lesson));
        adapter.addFrag(listDocFragment, getResources().getString(R.string.title_tab_document));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void init() {
        tabLayout = findViewById(R.id.tab_layout);
        container = findViewById(R.id.container);
        layoutHeader = findViewById(R.id.layout_header);
        imgBack = findViewById(R.id.image_button_back);
        title = findViewById(R.id.title);
         listLessonFragment = new ListLessonFragment();
         listDocFragment = new ListDocFragment();
    }
}