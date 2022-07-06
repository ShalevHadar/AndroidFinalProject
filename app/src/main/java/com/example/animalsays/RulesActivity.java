package com.example.animalsays;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
    }

    public void onClickReturnToMenu(View view) {
        Intent intent = new Intent(this,MenuActivity.class);
        startActivity(intent);
    }
}