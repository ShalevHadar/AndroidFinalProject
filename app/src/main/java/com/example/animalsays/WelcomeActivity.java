package com.example.animalsays;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;


public class WelcomeActivity extends AppCompatActivity {

    private EditText enterUserName;
    TextView whatsYourNameStart;
    Button sendBtn;
    Intent intent;
    ImageView animal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animal = findViewById(R.id.animal_says);
        sendBtn = findViewById(R.id.enter_name_btn);
        whatsYourNameStart = findViewById(R.id.whats_your_name);
        enterUserName = findViewById(R.id.enter_name_edit_text);
        animal.animate().translationX(0).withEndAction(() -> {
            animal.animate().translationX(0).setDuration(1750).start();
            animal.animate().alpha(1).setDuration(3500).start();
        });
        whatsYourNameStart.animate().translationX(0).withEndAction(() -> {
            whatsYourNameStart.animate().translationX(0).setDuration(1750).start();
            whatsYourNameStart.animate().alpha(1).setDuration(3500).start();
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

    /**
     * after getting name from user, set it to 'hello_name_txt'
     * @param view
     */
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