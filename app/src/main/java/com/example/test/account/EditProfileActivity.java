package com.example.test.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.R;

public class EditProfileActivity extends AppCompatActivity {
    ImageView imgBack, imgHome;
    TextView title;
    View layoutHeader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        init();
        setOnclick();
        title.setText("Edit profile");
    }
    private void init() {
        layoutHeader = findViewById(R.id.layout_header);
        imgBack = findViewById(R.id.image_button_back);
        title = findViewById(R.id.title);

    }
    private void setOnclick() {
        imgBack.setOnClickListener(view ->{
            finish();
        });

    }
}