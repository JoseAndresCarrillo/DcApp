package com.example.josand.dc_app.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.josand.dc_app.R;
import com.example.josand.dc_app.ui.Main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Intent homeIntent = new Intent(this,MainActivity.class);
        startActivity(homeIntent);
        finish();
    }
}
