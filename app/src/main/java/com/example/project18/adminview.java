package com.example.project18;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class adminview extends AppCompatActivity {
    ListView ll1;
    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    List<String> list1=new ArrayList<String>();

    List<String> list2=new ArrayList<String>();

    List<String> list3=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminview);
        dbHelper=new DatabaseHelper(this);
        db=dbHelper.getReadableDatabase();
        ll1=(ListView)findViewById(R.id.l);
        String col[]={DatabaseContract.Review._ID,DatabaseContract.Review.col_feedback,DatabaseContract.Review.col_rate};
        Cursor c=db.query(DatabaseContract.Review.Table_Name_REVIEW,col,null,null,null,null,null);
        while (c.moveToNext())
        {
            String id=c.getString(0);
            String fb=c.getString(1);
            String r=c.getString(2);
            list1.add("UserID: "+id);
            list2.add(fb);
            list3.add(r);
        }
        ReviewAdapter adapter=new ReviewAdapter(this,list3,list1,list2);
        ll1.setAdapter(adapter);
    }
}