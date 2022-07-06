package com.example.animalsays;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private ImageView loadingScreen;
    private TextView loadingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loadingText = findViewById(R.id.loading_Txt);
        loadingScreen = findViewById(R.id.loading_Screen);
        final Animation ImageAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        loadingScreen.startAnimation(ImageAnimation);

        new Handler().postDelayed((Runnable) () -> {
            loadingText.setText("Mewloading is DONE");
        },1500);

        new Handler().postDelayed((Runnable) () -> {
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        },3000);
    }

}