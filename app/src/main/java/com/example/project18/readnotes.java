package com.example.project18;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class readnotes extends AppCompatActivity {

    String date,title,notes;
    TextView ed1,ed2,ed3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readnotes);
        Intent intent=getIntent();
        date=intent.getStringExtra("date");
        title=intent.getStringExtra("title");
        notes=intent.getStringExtra("notes");
        ed1=findViewById(R.id.date);
        ed2=findViewById(R.id.title);
        ed3=findViewById(R.id.body);
        String b1=intent.getStringExtra("fontstyle");
        String a2=intent.getStringExtra("datestyle");
        String a3=intent.getStringExtra("fontsize");
        ed1 = findViewById(R.id.date);
        ed2=findViewById(R.id.title);
        ed3=findViewById(R.id.body);
        if(a3!=null)
        {
            float a=Float.valueOf(a3);
            ed1.setTextSize(a);
            ed2.setTextSize(a);
            ed3.setTextSize(a);
        }
        else
        {
            ed1.setTextSize(16);
            ed2.setTextSize(16);
            ed3.setTextSize(16);
        }
        if(b1!=null)
        {
            if(b1.equals("Sans-Serif")) {

                Typeface typeface = ResourcesCompat.getFont(this,R.font.a3);
                ed1.setTypeface(typeface);
                ed2.setTypeface(typeface);
                ed3.setTypeface(typeface);
            }
            else if(b1.equals("Cursive"))
            {
                ed1.setTypeface(Typeface.MONOSPACE);
                ed2.setTypeface(Typeface.MONOSPACE);
                ed3.setTypeface(Typeface.MONOSPACE);
            }
            else if(b1.equals("Sans-Serif Medium"))
            {
                Typeface typeface = ResourcesCompat.getFont(this,R.font.a1);
                ed1.setTypeface(typeface);
                ed2.setTypeface(typeface);
                ed3.setTypeface(typeface);
            }
            else if(b1.equals("Sans-Serif Light"))
            {
                Typeface typeface = ResourcesCompat.getFont(this,R.font.a2);
                ed1.setTypeface(typeface);
                ed2.setTypeface(typeface);
                ed3.setTypeface(typeface);
            }
            else
            {

                ed1.setTypeface(Typeface.SANS_SERIF);
                ed2.setTypeface(Typeface.SANS_SERIF);
                ed3.setTypeface(Typeface.SANS_SERIF);
            }
        }

        ed1.setText(date);
        ed2.setText(title);
        ed3.setText(notes);


    }
}
