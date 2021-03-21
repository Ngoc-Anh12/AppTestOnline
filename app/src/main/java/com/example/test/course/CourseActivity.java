package com.example.test.course;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.test.R;

public class CourseActivity extends AppCompatActivity {
    View layout;
    LinearLayout btnCourse, btnTest, btnDoc, btnAcc;
    TextView titleMath, titleEnglish, titleHistory, titleLiterature ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        init();
        handle();
    }

    private void handle() {
        titleMath.setText("Math");
        titleEnglish.setText("English");
        titleHistory.setText("History");
        titleLiterature.setText("Literature");
    }

    private void init() {
        layout = findViewById(R.id.layout);
        btnCourse = layout.findViewById(R.id.ll_course);
        btnTest = layout.findViewById(R.id.ll_test);
        btnDoc = layout.findViewById(R.id.ll_document);
        btnAcc = layout.findViewById(R.id.ll_acc);
        titleMath = btnCourse.findViewById(R.id.text_course);
        titleEnglish = btnTest.findViewById(R.id.text_test);
        titleHistory = btnDoc.findViewById(R.id.text_doc);
        titleLiterature = btnAcc.findViewById(R.id.text_acc);
    }
}