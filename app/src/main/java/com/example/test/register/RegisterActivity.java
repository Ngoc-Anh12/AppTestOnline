package com.example.test.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.test.R;
import com.example.test.core.retrofit.ApiClient;
import com.example.test.core.retrofit.RequestApi;
import com.example.test.home.HomeActivity;
import com.example.test.login.LoginActivity;
import com.example.test.model.ResponseDTO;
import com.example.test.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    ImageView imageButtonBack;
    AppCompatButton btnRegister;
    EditText edit_text_username,edit_text_pass;
     RequestApi requestAPI = ApiClient.getClient().create(RequestApi.class);
     private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        handle();
    }

    private void handle() {
        imageButtonBack.setOnClickListener(view ->{
            finish();
        });

        btnRegister.setOnClickListener(view ->{
            Intent intentHome = new Intent(RegisterActivity.this, HomeActivity.class);
            startActivity(intentHome);
        });
    }

    private void init() {
        imageButtonBack = findViewById(R.id.image_button_back);
        btnRegister = findViewById(R.id.btn_register);
        edit_text_username = findViewById(R.id.edit_text_username);
        edit_text_pass = findViewById(R.id.edit_text_pass);
    }
    private void handleRegister(){
        user.setUsername(edit_text_username.getText().toString());
        user.setPassword((edit_text_pass.getText().toString()));
        requestAPI.RegisterImp(user).enqueue(new Callback<ResponseDTO<Boolean>>() {
            @Override
            public void onResponse(Call<ResponseDTO<Boolean>> call, Response<ResponseDTO<Boolean>> response) {
                if(response.body()!= null && response.body().data == true && response.body().error==0){
                    Toast.makeText(RegisterActivity.this,"Success !",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO<Boolean>> call, Throwable t) {

            }
        });


    }
}