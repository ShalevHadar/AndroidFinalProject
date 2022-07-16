package com.example.animalsays;


import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * This is the biggest class in the project
 * This class is meant to define the game itself and manage it properly
 * Each comment will define it's own purpose.
 */
public class GameActivity extends AppCompatActivity {


    /**
     * declare variables
     */
    private TextView numberOfLevel;
    private final String[] buttonAnimalArray = {"dog_btn", "cat_btn", "cow_btn", "duck_btn"};
    private final Vector<String> gamePattern = new Vector<>();
    private final Vector<String> userClickedPattern = new Vector<>();
    private int level = 0, i;
    private ImageView dog, cat, green, duck;
    private Button startGame;
    private Integer score;
    private TextView showMyScore;
    private String username;
    private pl.droidsonroids.gif.GifImageView message;
    private Handler handler;
    private Runnable runnable;
    private ArrayList<ScoreboardHelper> highScoreList;
    private MediaPlayer Sound_cat, Sound_cow, Sound_dog, Sound_wrong, Sound_duck;
    private boolean soundIsOn;

    /**
     * Once created, set layout to activity_game
     * score to 0
     * hello to the userName
     * the animal images
     * Sounds and etc..
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        username = getIntent().getStringExtra("hello_name_txt");
        score = 0;
        Context context = getApplicationContext();
        numberOfLevel = findViewById(R.id.level_txt);
        dog = findViewById(R.id.dog_btn);
        cat = findViewById(R.id.cat_btn);
        green = findViewById(R.id.cow_btn);
        duck = findViewById(R.id.duck_btn);
        LinearLayout gameLayout = findViewById(R.id.game_layout);
        setButtonClickable(false);
        startGame = findViewById(R.id.begin_btn);
        Sound_cat = MediaPlayer.create(this, R.raw.cat_mew);
        Sound_cow = MediaPlayer.create(this, R.raw.cow);
        Sound_dog = MediaPlayer.create(this, R.raw.dog_bark);
        Sound_duck = MediaPlayer.create(this, R.raw.duck_quack);
        Sound_wrong = MediaPlayer.create(this, R.raw.wrong);
        showMyScore = findViewById(R.id.points_txt);
        message = findViewById(R.id.well_done_gif_img);
        highScoreList = scoreBoardSharedPreferences.readFromSharedPreferences(this); // fetch high score data
        if (highScoreList == null || highScoreList.isEmpty()) {
            highScoreList = new ArrayList<>();
        }

        soundIsOn = getIntent().getExtras().getBoolean("boolSoundIsOn");
    }

    /**
     * Good Luck toast once game start
     * Cannot click after game has started
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void beginGame(View view) {
        Toast.makeText(GameActivity.this, getResources().getString(R.string.start_of_game), Toast.LENGTH_SHORT).show();
        startGame.setText(getString(R.string.new_game_btn));
        startGame.setClickable(false);
        nextSequence();//start fresh new game
    }

    /**
     * Start over function and clear user if failed
     */
    private void startOver() {

        setButtonClickable(false);
        userClickedPattern.removeAllElements();
        gamePattern.removeAllElements();
        score = 0;
        showMyScore.setText(getString(R.string.default_score));
        level = 0;
    }

    /**
     * Choose randomly the next animal
     * Making the animals as array, then choosing a random one with a RandomInt.
     */
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void nextSequence() {

        userClickedPattern.removeAllElements();
        Random randomAnimal = new Random();
        int randomAnimalChooser = randomAnimal.nextInt(4);
        String randomChosenAnimal = buttonAnimalArray[randomAnimalChooser];
        gamePattern.add(randomChosenAnimal);
        int size = gamePattern.size();
        i = 0;

        handler = new Handler();
        runnable = () -> {
            playPattern(gamePattern.elementAt(i));
            i++;
            if (i < size)
                handler.postDelayed(runnable, 900);
            else {
                setButtonClickable(true);

            }
        };
        handler.postDelayed(runnable, 900);
        level++;
        numberOfLevel.setText(getString(R.string.level) +" " + level);
        setButtonClickable(false);
    }

    /**
     * Generate new pattern for the new level
     * use API to use: buttonAnimation
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void playPattern(String color) {
        playSound(color);
        buttonAnimation(color);
    }

    /**
     * After calling the animal, restore it its original form
     * @param view
     * @param curColor
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void restoreButtonColorAndShape(ImageView view, int curColor) {
        Handler handler2 = new Handler();
        Handler handler3 = new Handler();
        Runnable runnable2 = () -> view.setColorFilter(ContextCompat.getColor(view.getContext(), curColor),
                android.graphics.PorterDuff.Mode.SRC_IN);
        Runnable runnable3 = () -> view.setColorFilter(null);
        handler2.postDelayed(runnable2, 100);
        handler3.postDelayed(runnable3, 100);

        view.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
    }

    /**
     * Make animal 'flash' when clicking on it
     * @param name
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void buttonAnimation(String name) {
        switch (name) {
            case "dog_btn":
                restoreButtonColorAndShape(dog, R.color.pitch);
                break;
            case "cat_btn":
                restoreButtonColorAndShape(cat, R.color.pitch);
                break;
            case "cow_btn":
                restoreButtonColorAndShape(green, R.color.pitch);
                break;
            case "duck_btn":
                restoreButtonColorAndShape(duck, R.color.pitch);
                break;
            default:
                break;
        }
    }

    /**
     *a helper function to check if the user marked the correct answer
     * @param color
     * @param currentLevel
     */
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void checkAnswer(String color, int currentLevel) {

        userClickedPattern.add(color);
        if (gamePattern.elementAt(currentLevel - 1).equals(userClickedPattern.elementAt(currentLevel - 1))) {
            playPattern(color);

            if (currentLevel == level) {
                score += 10;
                showMyScore.setText(getString(R.string.current_score) +": " +score.toString());
                nextSequence();
            }
        } else {
            playPattern("wrong");
            gameOver();
        }
    }

    /**
     * Game over function, this function is called after checkAnswer
     */
    private void gameOver() {
        numberOfLevel.setText(getString(R.string.game_over));
        startGame.setText(getString(R.string.bad_luck_try_again));
        startGame.setClickable(true);
        updateHighScores();
        startOver();
    }

    /**
     * play sound according to the animal pressed
     * add a verify fucntion to make sure if sound is already playing don't play sound.
     * @param name
     */
    private void playSound(String name) {
        if (!soundIsOn) {
            return;
        }
        switch (name) {
            case "dog_btn":
                Sound_dog.start();
                break;
            case "cat_btn":
                Sound_cat.start();
                break;
            case "cow_btn":
                Sound_cow.start();
                break;
            case "duck_btn":
                Sound_duck.start();
                break;
            default:
                Sound_wrong.start();
                break;
        }
    }

    /**
     * make animal clickable
     * @param status
     */
    public void setButtonClickable(boolean status) {
        dog.setClickable(status);
        cat.setClickable(status);
        green.setClickable(status);
        duck.setClickable(status);

    }

    /**
     * choose dog
     * @param view
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void dogWasChosen(View view) {
        checkAnswer("dog_btn", userClickedPattern.size() + 1);
    }

    /**
     * choose duck
     * @param view
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void duckWasChosen(View view) {
        checkAnswer("duck_btn", userClickedPattern.size() + 1);
    }

    /**
     * choose dog
     * @param view
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void cowWasChosen(View view) {
        checkAnswer("cow_btn", userClickedPattern.size() + 1);
    }

    /**
     * choose cat
     * @param view
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void catWasChosen(View view) {
        checkAnswer("cat_btn", userClickedPattern.size() + 1);
    }

    /** Update the highest score in scored board once the game is over
     * ScoreboardHelper is implementing a collection sort function
     */
    private void updateHighScores() {
        ScoreboardHelper newHighScore = new ScoreboardHelper(username, score.toString());
        highScoreList.add(newHighScore);
        Collections.sort(highScoreList);
        scoreBoardSharedPreferences.writeToSharedPreferences(getApplicationContext(), highScoreList); // Write list to shared preferences so it would be saved if we re-open the application
    }
}