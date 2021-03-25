package com.example.test.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.test.R;
import com.example.test.core.retrofit.ApiClient;
import com.example.test.core.retrofit.RequestApi;
import com.example.test.home.HomeActivity;
import com.example.test.model.ResponseDTO;
import com.example.test.model.User;
import com.example.test.model.UserInfo;
import com.example.test.utils.AppData;
import com.example.test.utils.DataServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    ImageView imageButtonBack;
    AppCompatButton btnLogin;
    private EditText edit_text_username,edit_text_pass;
    private User user;
    final RequestApi requestAPI = ApiClient.getClient().create(RequestApi.class);
    boolean iCheck = false;
    boolean btnLoginCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

       // checkFieldForEmpty();
        handle();
    }

    void checkFieldForEmpty() {
        String user_name= edit_text_username.getText().toString().trim();
        String pass= edit_text_pass.getText().toString().trim();
        if (user_name.isEmpty()&&pass.isEmpty()){
            btnLogin.setEnabled(false);
        }
        else if(!user_name.equals("")&&pass.equals("")){
            btnLogin.setEnabled(false);
        }
        else if(!pass.equals("")&&user_name.equals("")){
            btnLogin.setEnabled(false);
        }
        else {
            btnLoginCheck= true;

         }
        }
    private void handle() {
        imageButtonBack.setOnClickListener(view ->{
            finish();
        });


            btnLogin.setOnClickListener(view ->{
                handleLogin();

//
            });


    }


    private void init() {
        imageButtonBack = findViewById(R.id.image_button_back);
        btnLogin = findViewById(R.id.btn_login);
        edit_text_username = (EditText) findViewById(R.id.edit_text_username);
        edit_text_pass = (EditText) findViewById(R.id.edit_text_pass);
    }
    private void handleLogin (){
        user = new User();
        user.setUsername(edit_text_username.getText().toString());
        user.setPassword(edit_text_pass.getText().toString());
        requestAPI.LoginImpl(user).enqueue(new Callback<ResponseDTO<UserInfo>>() {
           @Override
           public void onResponse(Call<ResponseDTO<UserInfo>> call, Response<ResponseDTO<UserInfo>> response) {
               System.out.println(response);
               if(response.body() != null && response.body().data != null && response.body().error==0){
                   AppData.currentUser = response.body().data;
                   DataServices.getInstance(LoginActivity.this).storeToken(response.body().data.getToken());
                   DataServices.getInstance(LoginActivity.this).save();
                   Intent intentHome = new Intent(LoginActivity.this, HomeActivity.class);
                   intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                   startActivity(intentHome);
               }
               else {
                  return;
               }
           }

           @Override
           public void onFailure(Call<ResponseDTO<UserInfo>> call, Throwable t) {

           }
       });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}