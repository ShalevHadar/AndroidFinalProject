package com.example.simonsays;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class ListviewAdapter extends ArrayAdapter<HighscoreObject> {

    private Context mContext;
    int mResource;

    public ListviewAdapter(Context context, int resource, ArrayList<HighscoreObject> objects)
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

        HighscoreObject highScore = new HighscoreObject(username, score);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvUserName = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvScore = (TextView) convertView.findViewById(R.id.textView3);

        tvUserName.setText(username);
        tvScore.setText(score);

        return convertView;
    }
}
