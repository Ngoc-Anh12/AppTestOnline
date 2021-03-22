package com.example.test.account;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.test.R;

public class AccountActivity extends AppCompatActivity {
    ImageButton imgBack, imgHome;
    TextView title;
    View layoutHeader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        init();
        setOnclick();
        //set title toolbar
        title.setText(R.string.account);
    }

    private void init() {
        layoutHeader = findViewById(R.id.layout_header);
        imgBack = layoutHeader.findViewById(R.id.image_button_back);
        imgHome = layoutHeader.findViewById(R.id.home);
        title = layoutHeader.findViewById(R.id.title);
    }

    private void setOnclick() {
        imgBack.setOnClickListener(view ->{
            finish();
        });

    }
}