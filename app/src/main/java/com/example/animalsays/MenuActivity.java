package com.example.animalsays;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    private String userName;
    private ImageButton leaderBoardButton;
    private ImageButton soundButton;
    private ImageButton musicButton;
    private ImageButton settingsButton;
    private ImageButton hebrewButton;
    private ImageButton englishButton;
    boolean settingsButtonClicked = false;
    boolean soundIsOn = true;
    boolean musicIsOn = true;
    LanguageManager lang;
    MediaPlayer mainThemeMusic;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        /**
         * Relate variable to their related image/text
         */
        hebrewButton = findViewById(R.id.hebrew_btn);
        englishButton = findViewById(R.id.english_btn);
        settingsButton = findViewById(R.id.settings_btn);
        leaderBoardButton = findViewById(R.id.leaderboard_btn);
        Button gameButton = findViewById(R.id.game_btn);
        Button guideButton = findViewById(R.id.guide_btn);
        soundButton = findViewById(R.id.sound_btn);
        musicButton = findViewById(R.id.music_btn);
        TextView presentName = findViewById(R.id.hello_name_txt);

        /**
         * set main theme music
         */
        mainThemeMusic = MediaPlayer.create(this,R.raw.main_theme_song);
        mainThemeMusic.start();
        userName = getIntent().getStringExtra("hello_name_txt");

        /**
         * set language && user name
         */
        presentName.setText("Hello " +userName);
        lang = new LanguageManager(this);

        /**
         * How to - rules
         */
        guideButton.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this, Rules.class);
            startActivity(intent);

        });

        /**
         * Set variables that we got from welcome screen
         */
        gameButton.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this,GameActivity.class);
            intent.putExtra("boolSoundIsOn",soundIsOn);
            intent.putExtra("hello_name_txt",userName);
            startActivity(intent);
        });

        /**
         * Settings button
         */
        settingsButton.setOnClickListener(view -> {
            RotateAnimation rotateAnimation = new RotateAnimation(0,
                    90, RotateAnimation.RELATIVE_TO_SELF,
                    .5f, RotateAnimation.RELATIVE_TO_SELF
                    ,.5f);

            rotateAnimation.setDuration(700);
            settingsButton.startAnimation(rotateAnimation);
            openOrCloseSettingsMenu();
        });

        /**
         * Supporting both english and hebrew
         */
        hebrewButton.setOnClickListener(view -> {
            lang.updateResource("iw");
            settingsButtonClicked = true;
            mainThemeMusic.pause();
            recreate();
            Toast.makeText(MenuActivity.this, getResources().getString(R.string.language_has_changed),
                    Toast.LENGTH_SHORT).show();
        });

        /**
         * reload page once language is changed
         */
        englishButton.setOnClickListener(view -> {
            lang.updateResource("en");
            settingsButtonClicked = true;
            mainThemeMusic.pause();
            recreate();
            Toast.makeText(MenuActivity.this, getResources().getString(R.string.language_has_changed),
                    Toast.LENGTH_SHORT).show();
        });

        /**
         * show scoreboard screen
         */
        leaderBoardButton.setOnClickListener(view -> {

            Intent intent = new Intent(MenuActivity.this,ScoreBoardActivity.class);
            startActivity(intent);
        });

        /**
         * control sound button
         */
        soundButton.setOnClickListener(view -> {

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

        });

        /**
         * control background music
         */
        musicButton.setOnClickListener(view -> {

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
        });

    }

    /**
     * close settings menu
     * a bit scuffy but works fine
     */
    public void openOrCloseSettingsMenu() {

        if(!settingsButtonClicked) {
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