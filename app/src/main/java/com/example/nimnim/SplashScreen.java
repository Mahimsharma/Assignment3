package com.example.nimnim;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nimnim.Utils.LogInHandler;

public class SplashScreen extends AppCompatActivity {

    LogInHandler logInHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        logInHandler = LogInHandler.getInstance(this);
        Handler handler = new Handler();
        if (logInHandler.checkCredentials())
        {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(com.example.nimnim.SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            },1000);
        }
        else
        {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(com.example.nimnim.SplashScreen.this, Signn.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);
        }
    }
}
