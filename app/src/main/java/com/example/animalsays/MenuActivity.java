package com.example.animalsays;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    //String
    private String userName;

    // text views
    private TextView presentName;

    // Buttons
    private Button gameButton;
    private Button guideButton;

    // ImageButtons
    private ImageButton settingsButton;
    private ImageButton hebrewButton;
    private ImageButton englishButton;
    private ImageButton leaderBoardButton;
    private ImageButton soundButton;
    private ImageButton musicButton;

    // booleans
    boolean settingsButtonClicked = false;
    boolean soundIsOn = true;
    boolean musicIsOn = true;

    // language manager
    LanguageManager lang;

    // media player
    MediaPlayer mainThemeMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        /* Find view by Id section */
        hebrewButton = findViewById(R.id.hebrew_btn);
        englishButton = findViewById(R.id.english_btn);
        settingsButton = findViewById(R.id.settings_btn);
        leaderBoardButton = findViewById(R.id.leaderboard_btn);
        gameButton = findViewById(R.id.game_btn);
        guideButton = findViewById(R.id.guide_btn);
        soundButton = findViewById(R.id.sound_btn);
        musicButton = findViewById(R.id.music_btn);
        presentName = findViewById(R.id.hello_name_txt);

        /* Activate language */
        lang = new LanguageManager(this);

        /* Activate music */
        mainThemeMusic = MediaPlayer.create(this,R.raw.main_theme_song);
        mainThemeMusic.start();
        userName = getIntent().getStringExtra("hello_name_txt");

        /*  if user entered his name -> hello + userName. else -> hello */
        presentName.setText("Hello " +userName);

        /* Listeners */

        /* gameButton:
        *   Enters GameActivity and allows the user to play
        */
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuActivity.this,GameActivity.class);
                intent.putExtra("boolSoundIsOn",soundIsOn);
                intent.putExtra("hello_name_txt",userName);
                startActivity(intent);
            }
        });

        /* guideButton:
        * Enters RulesActivity and allows the user to watch the rules
        */
        guideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuActivity.this,RulesActivity.class);
                startActivity(intent);

            }
        });

        /* settings:
        *   allows the user to use the settings (makes them unhidden).
        *   if clicked again closes the settings menu and makes the settings menu hidden
        */
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                /*
                settingsButton.animate().rotationX(180).rotationY(90).setDuration(1000).withEndAction(new Runnable() {
                   @Override
                    public void run() {
                        settingsButton.animate().rotationX(0).rotationY(0).setDuration(1000);
                        openOrCloseSettingsMenu();
                    }


                });
                */

                RotateAnimation rotateAnimation = new RotateAnimation(0,
                        90, RotateAnimation.RELATIVE_TO_SELF,
                        .5f, RotateAnimation.RELATIVE_TO_SELF
                        ,.5f);

                rotateAnimation.setDuration(700);
                settingsButton.startAnimation(rotateAnimation);
                openOrCloseSettingsMenu();
            }
        });

        /* a button that changes the language to hebrew and reloads the page */
        hebrewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lang.updateResource("iw");
                settingsButtonClicked = true;
                mainThemeMusic.pause();
                recreate();
                Toast.makeText(MenuActivity.this, getResources().getString(R.string.language_has_changed),
                        Toast.LENGTH_SHORT).show();
            }

        });

        /* a button that changes the language to english and reloads the page */
        englishButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View view) {
                lang.updateResource("en");
                settingsButtonClicked = true;
                mainThemeMusic.pause();
                recreate();
                Toast.makeText(MenuActivity.this, getResources().getString(R.string.language_has_changed),
                        Toast.LENGTH_SHORT).show();
            }
        });

        /* a button that takes the user to ScoreBoardActivity there he can see all the players whom
        *   played the game on the device.
        */
        leaderBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuActivity.this,ScoreBoardActivity.class);
                startActivity(intent);
            }
        });

        /*
        * a button that activates in game sound
        */
        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(soundIsOn == true)
                {
                    soundIsOn = false;
                    soundButton.setImageResource(R.drawable.sound_off_icon);
                    Toast.makeText(MenuActivity.this, getResources().getString(R.string.sound_is_off),
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                    soundIsOn = true;
                    soundButton.setImageResource(R.drawable.sound_on_icon);
                    Toast.makeText(MenuActivity.this, getResources().getString(R.string.sound_is_on),
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        /*
         * a button that activates background music
         */
        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(musicIsOn == true)
                {
                    musicIsOn = false;
                    musicButton.setImageResource(R.drawable.music_cut);
                    mainThemeMusic.pause();
                    Toast.makeText(MenuActivity.this, getResources().getString(R.string.music_is_off),
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                    musicIsOn = true;
                    musicButton.setImageResource(R.drawable.music);
                    mainThemeMusic.start();
                    Toast.makeText(MenuActivity.this, getResources().getString(R.string.music_is_on),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    // a function that opens and closes the Settings menu
    public void openOrCloseSettingsMenu() {

        if(settingsButtonClicked == false) {
            //hebrew
            hebrewButton.setVisibility(View.VISIBLE);
            hebrewButton.setClickable(true);

            //english
            englishButton.setVisibility(View.VISIBLE);
            englishButton.setClickable(true);

            // leaderBoard
            leaderBoardButton.setVisibility(View.VISIBLE);
            leaderBoardButton.setClickable(true);

            // music
            musicButton.setVisibility(View.VISIBLE);
            musicButton.setClickable(true);

            // sound
            soundButton.setVisibility(View.VISIBLE);
            soundButton.setClickable(true);

            settingsButtonClicked = true;
        }
        else
        {
            hebrewButton.setVisibility(View.INVISIBLE);
            hebrewButton.setClickable(false);

            englishButton.setVisibility(View.INVISIBLE);
            englishButton.setClickable(false);

            // leaderBoard
            leaderBoardButton.setVisibility(View.INVISIBLE);
            leaderBoardButton.setClickable(false);

            // music
            musicButton.setVisibility(View.INVISIBLE);
            musicButton.setClickable(false);

            // sound
            soundButton.setVisibility(View.INVISIBLE);
            soundButton.setClickable(false);

            settingsButtonClicked = false;
        }

    }

    @Override
    public void onBackPressed() {
        mainThemeMusic.pause();
        super.onBackPressed();
    }

}