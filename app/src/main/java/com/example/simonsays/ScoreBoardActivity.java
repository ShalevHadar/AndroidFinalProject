package com.example.simonsays;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class ScoreBoardActivity extends AppCompatActivity {

    private TextView txt;
    private Context context;
    ListView myListView;
    ArrayList<HighscoreObject> highScoreObjectArrayList;
    private Button resetButton;
    private ListviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        highScoreObjectArrayList = new ArrayList<>();
        resetButton = findViewById(R.id.reset_score_board);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highScoreObjectArrayList = new ArrayList<>();
                scoreBoardSharedPreferences.writeToSharedPreferences(getApplicationContext(), highScoreObjectArrayList);
                adapter.clear();
                adapter.addAll(highScoreObjectArrayList);
                adapter.notifyDataSetChanged();
            }
        });

        highScoreObjectArrayList = scoreBoardSharedPreferences.readFromSharedPreferences(this); // Get highscore data from shared preferences
        if ( highScoreObjectArrayList ==null)
            highScoreObjectArrayList = new ArrayList<>();

        myListView=findViewById(R.id.listViewhighScores);

        Collections.sort(highScoreObjectArrayList); // Sort the list in descending order so the highest score will be first

        adapter = new ListviewAdapter(ScoreBoardActivity.this, R.layout.listviewline_design, highScoreObjectArrayList);
        myListView.setAdapter(adapter);
    }
}