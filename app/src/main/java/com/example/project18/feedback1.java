package com.example.project18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class feedback1 extends AppCompatActivity {
    Button btn;

    EditText ee1;
    String id;
    String star;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback1);
        Intent intent=getIntent();
        id=intent.getStringExtra("userid");
        star=intent.getStringExtra("rate");
        btn=(Button)findViewById(R.id.b1);
        ee1=(EditText)findViewById(R.id.e);

    }
    public void feedback(View v)
    {


        Intent intent = new Intent(this, feedback2.class);
        Toast.makeText(this, "feedback given successfully", Toast.LENGTH_SHORT).show();
        intent.putExtra("userid", id);
        intent.putExtra("rate",star.toString());
        intent.putExtra("feedback",ee1.getText().toString());
        startActivity(intent);
    }
}