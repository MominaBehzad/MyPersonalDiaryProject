package com.example.project18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class rate1 extends AppCompatActivity {
    RatingBar r;
    Button b;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate1);
        r=(RatingBar)findViewById(R.id.rate);
        b=(Button)findViewById(R.id.btn);
        Intent intent=getIntent();
        id=intent.getStringExtra("userid");
    }
    public void send(View view) {
        String s = String.valueOf(r.getRating());
        Toast.makeText(this, "rated successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, rate2.class);
        intent.putExtra("rate", s.toString());
        intent.putExtra("userid", id);
        startActivity(intent);
    }
}
