package com.example.project18;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ReviewAdapter extends ArrayAdapter {
    private List<String> titles;
    private List<String> body;
    private  List<String> dates;
    private Activity context;

    public ReviewAdapter(Activity context, List<String> rate, List<String> email,List<String> feedback )
    {
        super(context, R.layout.adminlist, email);
        this.context = context;
        this.titles=email;
        this.body = feedback;
        this.dates=rate;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.adminlist, null, true);
        TextView notedate = (TextView) row.findViewById(R.id.ratingview);
        TextView notetitle = (TextView) row.findViewById(R.id.reviewmail);
        TextView notebody = (TextView) row.findViewById(R.id.feedbackview);
        notedate.setText(dates.get(position));
        notetitle.setText(titles.get(position));
        notebody.setText(body.get(position));
        return  row;
    }
    public void remove(Object position)
    {
    }
}