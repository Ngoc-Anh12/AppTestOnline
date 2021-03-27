package com.example.test.login;

import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.example.test.R;
import com.example.test.core.retrofit.ApiClient;
import com.example.test.core.retrofit.RequestApi;
import com.example.test.home.HomeActivity;
import com.example.test.model.ResponseDTO;
import com.example.test.model.User;
import com.example.test.model.UserInfo;
import com.example.test.utils.AppData;
import com.example.test.utils.DataServices;

import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    ImageView imageButtonBack, fingerprint;
    AppCompatButton btnLogin;
    TextView noiceFinger;

    private EditText edit_text_username,edit_text_pass;
    private User user;
    final RequestApi requestAPI = ApiClient.getClient().create(RequestApi.class);
    boolean iCheck = false;
    boolean btnLoginCheck = false;
    ProgressBar progressBar;
    private androidx.biometric.BiometricPrompt biometricPrompt;
    private androidx.biometric.BiometricPrompt.PromptInfo promptInfo;
    private Executor executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

       // checkFieldForEmpty();
        handle();
        //progressBar.setVisibility(View.VISIBLE);

        executor = ContextCompat.getMainExecutor(this);

        biometricPrompt = new BiometricPrompt(LoginActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {

                super.onAuthenticationError(errorCode, errString);
                noiceFinger.setText("Authentication error:" + errString);
                Toast.makeText(LoginActivity.this, "Authentication" + errString, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {

                super.onAuthenticationSucceeded(result);
                noiceFinger.setText("Authentication succeed ...!");
                Toast.makeText(LoginActivity.this,"Authentication succeed ...!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }

            @Override
            public void onAuthenticationFailed() {

                super.onAuthenticationFailed();
                noiceFinger.setText("Authentication Failed ...!");
                Toast.makeText(LoginActivity.this,"Authentication Failed ...!",Toast.LENGTH_SHORT).show();

            }
        });
        promptInfo= new BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric")
            .setSubtitle("")
            .setDescription("")
            .setNegativeButtonText("Cancel")
            .build();

        fingerprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                biometricPrompt.authenticate(promptInfo);

            }
        });
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
        progressBar = findViewById(R.id.progress_bar);
        fingerprint = findViewById(R.id.fingerprint);
        noiceFinger = findViewById(R.id.noticeFinger);
    }
    private void handleLogin (){
        user = new User();
        progressBar.setVisibility(View.VISIBLE);
        user.setUsername(edit_text_username.getText().toString());
        user.setPassword(edit_text_pass.getText().toString());
        requestAPI.LoginImpl(user).enqueue(new Callback<ResponseDTO<UserInfo>>() {
           @Override
           public void onResponse(Call<ResponseDTO<UserInfo>> call, Response<ResponseDTO<UserInfo>> response) {
               System.out.println(response);
               if(response.body() != null && response.body().data != null && response.body().error==0){
                   AppData.currentUser = response.body().data;
                   progressBar.setVisibility(View.GONE);
                   DataServices.getInstance(LoginActivity.this).storeToken(response.body().data.getToken());
                   DataServices.getInstance(LoginActivity.this).save();
                   Intent intentHome = new Intent(LoginActivity.this, HomeActivity.class);
                   intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                   startActivity(intentHome);
               }
               else {
                   progressBar.setVisibility(View.GONE);
                  return;
               }
           }

           @Override
           public void onFailure(Call<ResponseDTO<UserInfo>> call, Throwable t) {
               progressBar.setVisibility(View.GONE);
           }
       });
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}