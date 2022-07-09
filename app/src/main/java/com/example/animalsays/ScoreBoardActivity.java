package com.example.animalsays;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;


/**
 * This class is made to manage the Score Board with a shared preference database (ehh..)
 */
public class ScoreBoardActivity extends AppCompatActivity {

    ListView myListView;
    ArrayList<ScoreboardHelper> highScoreObjectArrayList;
    private Button resetButton;
    private ListViewHelper adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        highScoreObjectArrayList = new ArrayList<>();
        resetButton = findViewById(R.id.reset_score_board);

        /**
         * Button that reset the scoreboard
         */
        resetButton.setOnClickListener(view -> {
            highScoreObjectArrayList = new ArrayList<>();
            scoreBoardSharedPreferences.writeToSharedPreferences(getApplicationContext(), highScoreObjectArrayList);
            adapter.clear();
            adapter.addAll(highScoreObjectArrayList);
            adapter.notifyDataSetChanged();
        });

        /**
         * Get highscore data from shared preferences
         */
        highScoreObjectArrayList = scoreBoardSharedPreferences.readFromSharedPreferences(this);
        if ( highScoreObjectArrayList ==null)
            highScoreObjectArrayList = new ArrayList<>();

        myListView=findViewById(R.id.listViewhighScores);

        /**
         * Sort the list in descending order so the highest score will be first
         */
        Collections.sort(highScoreObjectArrayList);

        adapter = new ListViewHelper(ScoreBoardActivity.this, R.layout.listviewline_design, highScoreObjectArrayList);
        myListView.setAdapter(adapter);
    }
}