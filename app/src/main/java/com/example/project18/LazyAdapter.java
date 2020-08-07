package com.example.project18;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class LazyAdapter extends ArrayAdapter {
    private List<String> titles;
    private List<String> body;
    private List<String> dates;
    private Activity context;

    public LazyAdapter(Activity context, List<String> dates, List<String> titles, List<String> body) {
        super(context, R.layout.list_row, titles);
        this.context = context;
        this.titles = titles;
        this.body = body;
        this.dates = dates;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if (convertView == null)
            row = inflater.inflate(R.layout.list_row, null, true);
        TextView notedate = (TextView) row.findViewById(R.id.notedate);
        TextView notetitle = (TextView) row.findViewById(R.id.notetitle);
        TextView notebody = (TextView) row.findViewById(R.id.notebody);
        notedate.setText(dates.get(position));
        notetitle.setText(titles.get(position));
        notebody.setText(body.get(position));
        return row;
    }
    //@Override
    /*public Filter getFilter() {
        return notefilter;
    }

    private Filter notefilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<String> suggestions1 = new ArrayList<>();
            List<String> suggestions2 = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                suggestions1.addAll(titles);
                suggestions2.addAll(body);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (int i = 0; i < titles.size(); i++) {
                    if (titles.get(i).toLowerCase().contains(filterPattern)) {
                        suggestions1.add(titles.get(i));
                        body.add(body.get(i));
                    }

                }

            }
            results.values = suggestions1;
          // results.values = suggestions2;
            results.count = suggestions1.size();
           //results.count = suggestions2.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List) results.values);
            notifyDataSetChanged();
        }
    };*/
}