package com.example.test.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.login.LoginActivity;

public class AccountActivity extends AppCompatActivity {
    ImageView imgBack, imgHome;
    TextView title;
    View layoutHeader;
    LinearLayout llProfile, llHistory;
    AppCompatButton btnSignOut;
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
        imgBack = findViewById(R.id.image_button_back);
        title = findViewById(R.id.title);
        llProfile = findViewById(R.id.ll_profile);
        llHistory = findViewById(R.id.ll_history);
        btnSignOut = findViewById(R.id.btn_sign_out);
    }

    private void setOnclick() {
        imgBack.setOnClickListener(view ->{
            finish();
        });

        llProfile.setOnClickListener(click -> {
            Intent intentEditProfile = new Intent(AccountActivity.this, EditProfileActivity.class);
            startActivity(intentEditProfile);
        });

        llHistory.setOnClickListener(click ->{

        });

        btnSignOut.setOnClickListener(view ->{
            Intent intent=new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }
}