package com.example.test;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.core.retrofit.ApiClient;
import com.example.test.core.retrofit.RequestApi;
import com.example.test.home.HomeActivity;
import com.example.test.login.LoginActivity;
import com.example.test.login.ViewPagerAdapter;
import com.example.test.model.ResponseDTO;
import com.example.test.model.UserInfo;
import com.example.test.register.RegisterActivity;
import com.example.test.utils.AppData;
import com.example.test.utils.DataServices;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    AppCompatButton btnLogin, btnRegister;
    int NUM_PAGES = 2;
    Timer timer;
    final long DELAY_MS = 3000;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.
    CircleIndicator layoutDots;
    final RequestApi requestAPI = ApiClient.getClient().create(RequestApi.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        viewPage();

        handle();
    }

    private void viewPage() {
        ViewPagerAdapter  viewPagerAdapter = new ViewPagerAdapter(this);
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

    private void initView() {
        viewPager = findViewById(R.id.view_pager);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        layoutDots = findViewById(R.id.layout_dots);
    }

    private void handle() {
       String test = DataServices.getInstance(MainActivity.this).getToken()+"";
       Log.d("LUUdev", test);
        if(DataServices.getInstance(MainActivity.this).getToken() != null){
            requestAPI.getToken(test).enqueue(new Callback<ResponseDTO<UserInfo>>() {
                @Override
                public void onResponse(Call<ResponseDTO<UserInfo>> call, Response<ResponseDTO<UserInfo>> response) {
                    if(response.body() != null && response.body().data != null && response.body().error==0){
                        DataServices.getInstance(MainActivity.this).storeToken(response.body().data.getToken());
                        DataServices.getInstance(MainActivity.this).save();
                    }

                }

                @Override
                public void onFailure(Call<ResponseDTO<UserInfo>> call, Throwable t) {

                }
            });
            Intent intentHome = new Intent(MainActivity.this, HomeActivity.class);
            intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentHome);
        }
        else {
            btnLogin.setOnClickListener(view -> {
                Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentLogin);
            });
            btnRegister.setOnClickListener(view -> {
                Intent intentRegister = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intentRegister);
            });
        }
    }

    @Override
    protected void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }
}