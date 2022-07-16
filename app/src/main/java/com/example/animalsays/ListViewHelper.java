package com.example.animalsays;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * This class is meant to help show the scoreboard users as a list
 * we get an arraylist from the scoreboardHelper class and display it
 */
public class ListViewHelper extends ArrayAdapter<ScoreboardHelper> {

    private final Context mContext;
    int mResource;

    public ListViewHelper(Context context, int resource, ArrayList<ScoreboardHelper> objects)
    {
        super(context,resource,objects);
        mContext=context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String username = getItem(position).getUsername();
        String score = getItem(position).getScore();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvUserName = convertView.findViewById(R.id.textView2);
        TextView tvScore = convertView.findViewById(R.id.textView3);

        tvUserName.setText(username);
        tvScore.setText(score);

        return convertView;
    }
}
