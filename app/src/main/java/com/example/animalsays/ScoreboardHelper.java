package com.example.animalsays;

/**
 * Class to help scoreboard to pull the highest score user from shared preferences
 * Mainly getters, setters and a compareAble function
 */
public class ScoreboardHelper implements Comparable{
    private final String username;
    private String score;

    public ScoreboardHelper(String username, String score) {
        this.username = username;
        this.score = score;
    }

    public String getScore() {
        return score;
    }

    public String getUsername() {
        return username;
    }


    @Override
    public int compareTo(Object p1) {
        int score1=Integer.parseInt(score);
        int score2=Integer.parseInt(((ScoreboardHelper)p1).getScore());

        return score2-score1;
    }
}

