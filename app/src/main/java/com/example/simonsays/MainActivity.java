package com.example.simonsays;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText enterUserName;
    Intent intent;
    ImageView animal;
    TextView helloWhatsYourName;
    Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animal = findViewById(R.id.animal_says);
        sendBtn = findViewById(R.id.enter_name_btn);
        helloWhatsYourName = findViewById(R.id.whats_your_name);
        enterUserName = findViewById(R.id.enter_name_edit_text);
        animal.animate().translationX(0).withEndAction(() -> {
            animal.animate().translationX(0).setDuration(1750).start();
            animal.animate().alpha(1).setDuration(3500).start();
        });
        helloWhatsYourName.animate().translationX(0).withEndAction(() -> {
            helloWhatsYourName.animate().translationX(0).setDuration(1750).start();
            helloWhatsYourName.animate().alpha(1).setDuration(3500).start();
        });
        enterUserName.animate().translationX(0).withEndAction(() -> {
            enterUserName.animate().translationX(0).setDuration(1750).start();
            enterUserName.animate().alpha(1).setDuration(3500).start();
        });
        sendBtn.animate().translationX(0).withEndAction(() -> {
            sendBtn.animate().translationX(0).setDuration(1750).start();
            sendBtn.animate().alpha(1).setDuration(3500).start();
        });
    }

    public void onClickOnSendName(View view) {

        String name = enterUserName.getText().toString();
        if(name.trim().equals(""))
        {
            name = "Random Animal";
        }
        intent = new Intent(this, MenuActivity.class);
        intent.putExtra("hello_name_txt",name);
        startActivity(intent);
    }
}