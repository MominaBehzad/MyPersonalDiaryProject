package com.example.project18;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.lang.reflect.Method;
import java.util.ArrayList;

import petrov.kristiyan.colorpicker.ColorPicker;

public class colors extends AppCompatActivity {

    LinearLayout l;
    String textstyles,datestyles,textsizes;
    int t=0,t2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_colors);
        Intent intent = getIntent();
        l = findViewById(R.id.colorlayout);
    }

    public void oncolor(View view) {
    final ColorPicker colorPicker = new ColorPicker(colors.this);
        final ArrayList<String> colors = new ArrayList<>();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        colors.add("#82B926");
        colors.add("#a276eb");
        colors.add("#6a3ab2");
        colors.add("#666666");
        colors.add("#FFFF00");
        colors.add("#3C8D2F");
        colors.add("#FA9F00");
        colors.add("#FF0000");
        colorPicker
                .setDefaultColorButton(Color.parseColor("#f84c44"))
                .setColors(colors)
                .setColumns(4)
                .setRoundColorButton(true)
                .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color)
                    {
                        Toast toast;
                        switch (colors.get(position))
                        {
                            case "#82B926":
                                toast=Toast.makeText(colors.this,"Color Green",Toast.LENGTH_LONG);
                                toast.show();
                                getApplicationContext().setTheme(R.style.green1);
                                recreate();
                                break;
                            case "#a276eb":
                                toast=Toast.makeText(colors.this,"Color Purple",Toast.LENGTH_LONG);
                                toast.show();
                                getApplicationContext().setTheme(R.style.green1);
                                recreate();
                                break;
                        }

                    }
                    @Override
                    public void onCancel() {
                    }
                }).show();
    }
    public void datestyle(View view) {
        final CharSequence datestyle[] = new CharSequence[]{"MM-DD-YY", "DD-MM-YY", "YY-MM-DD"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick a format");
        builder.setItems(datestyle, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // the user clicked on colors[which]
                datestyles=datestyle[which].toString();
            }
        });
        builder.show();
    }
    public void textstyle(View view) {
        final CharSequence textstyle[] = new CharSequence[]{"Monospace", "Sans-Serif", "Sans-Serif Medium", "Sans-Serif Light"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick a Font Family");
        builder.setItems(textstyle, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // the user clicked on colors[which]
                 textstyles=textstyle[which].toString();
            }
        });
        builder.show();

    }
    public void textsize(View view) {
        final AlertDialog.Builder popDialog = new AlertDialog.Builder(this);

        final SeekBar seek = new SeekBar(this);
        seek.setMax(100);
        seek.setPadding(20,30,20,30);

        popDialog.setTitle("Please Select Text Size");

        popDialog.setView(seek);

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
//Do something here with new value
                t = t + (progress-t2);
                t2 = progress;
            }

            public void onStartTrackingTouch(SeekBar arg0) {

// TODO Auto-generated method stub

            }


            public void onStopTrackingTouch(SeekBar seekBar) {

// TODO Auto-generated method stub

            }

        });

// Button OK

        popDialog.setPositiveButton("OK",

                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }

                });

        popDialog.create();
        popDialog.show();
    }
    public void save(View view)
    {
        Intent intent=new Intent(colors.this,MainActivity.class);
        if(textstyles==null) {
            textstyles = "Sans-Serif";}
            intent.putExtra("fontfamily", textstyles);
        if(datestyles==null) {
            datestyles = "DD-MM-YY";}
        intent.putExtra("datestyle", datestyles);
        if(textsizes==null) {
            textsizes = String.valueOf(t2);}
        intent.putExtra("textsize", textsizes);
        setResult(1,intent);
        finish();
    }
}