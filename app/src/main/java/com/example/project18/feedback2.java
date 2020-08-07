package com.example.project18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class feedback2 extends AppCompatActivity {
    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    String star,id,feedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback2);
        dbHelper=new DatabaseHelper(this);
        Intent intent=getIntent();
        id=intent.getStringExtra("userid");
        star=intent.getStringExtra("rate");
        feedback=intent.getStringExtra("feedback");

    }
    public void show(View v)
    {
        db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(DatabaseContract.Review._ID,id);
        values.put(DatabaseContract.Review.col_rate,star);
        values.put(DatabaseContract.Review.col_feedback,feedback);
        long row=db.insert(DatabaseContract.Review.Table_Name_REVIEW,null,values);
        if(row>0)
        {
            Toast.makeText(this, "successfull", Toast.LENGTH_SHORT).show();
          //  Intent intent=new Intent(this,MainActivity5.class);
            //startActivity(intent);
        }
        else
        {

            int ro1=db.update(DatabaseContract.Review.Table_Name_REVIEW,values,"_ID=?",new String[]{id});
            if(ro1>0)
            {
                Toast.makeText(this, "successfully updated", Toast.LENGTH_SHORT).show();
                //Intent intent=new Intent(this,MainActivity5.class);
                //startActivity(intent);
            }
        }

    }
}