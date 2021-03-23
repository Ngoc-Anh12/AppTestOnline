package com.example.test.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.example.test.R;
import com.example.test.core.AppData;
import com.example.test.core.service.DataService;
import com.example.test.home.HomeActivity;
import com.example.test.utils.GFingerPrint;

import java.util.concurrent.Executor;

import javax.crypto.Cipher;

public class LoginActivity extends AppCompatActivity {
    ImageButton imageButtonBack;
    AppCompatButton btnLogin;

    private androidx.biometric.BiometricPrompt biometricPrompt;
    private androidx.biometric.BiometricPrompt.PromptInfo promptInfo;
    GFingerPrint gFingerPrint;
    private Executor executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        handle();
    }

    private void handle() {
        imageButtonBack.setOnClickListener(view ->{
            finish();
        });

        btnLogin.setOnClickListener(view ->{
            Intent intentHome = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intentHome);
        });
    }

    public void settingFingerPrint(){
        gFingerPrint = new GFingerPrint(this);
        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(this , executor, new BiometricPrompt.AuthenticationCallback(){
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText( LoginActivity.this,
                        "Lỗi vân tay: " + errString, Toast.LENGTH_SHORT)
                        .show();
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);


                Cipher cipher =  result.getCryptoObject().getCipher();
                String encryptPassword = gFingerPrint.encryptPassword(cipher, AppData.getInstance().password);

                DataService.getInstance(null).storeFingerPassword(AppData.getInstance().userUUID,encryptPassword);
                DataService.getInstance(null).save();
            }
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });
        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Sử dụng vân tay để đăng nhập")
                .setNegativeButtonText("Không sử dụng vân tay")
                .build();

    }

    private void init() {
        imageButtonBack = findViewById(R.id.image_button_back);
        btnLogin = findViewById(R.id.btn_login);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}