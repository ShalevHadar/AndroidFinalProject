package com.example.animalsays;


import android.content.Context;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class scoreBoardSharedPreferences {

    /**
     * Android SharedPreferences can only work with some data structures.
     * I've Chosen to go with Gson (JSON).
     */

    private static final String LIST_KEY = "list_key";

    /**
     * Write into shared preferences
     * Creating a new GSON Object
     * Converting GSON to JSON
     * Getting the Shared Preference
     * Applying editor to write to the new scoreboard
     * Insert the arraylist into the scoreboard
     */

    public static void writeToSharedPreferences(Context context, ArrayList<HighscoreObject> list) {
        Gson gson = new Gson();
        String firstPlace = gson.toJson(list);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(LIST_KEY, firstPlace);
        editor.apply();
    }

    /**
     * read from shared preferences
     * Getting current shared preferences
     * Pull highest score
     * Storing the data from shared preferences into an arrayList
     */

    public static ArrayList<HighscoreObject> readFromSharedPreferences(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String firstPlace = pref.getString(LIST_KEY,"");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<HighscoreObject>>(){}.getType();
        ArrayList<HighscoreObject> list = gson.fromJson(firstPlace,type);
        return list;
    }

}

