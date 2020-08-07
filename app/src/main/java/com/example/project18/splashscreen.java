package com.example.project18;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splashscreen extends AppCompatActivity {
    ImageView ii1;
    TextView tt1,tt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        ii1=(ImageView)findViewById(R.id.i1);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        ii1.startAnimation(animation); //we want image view to start this annimation
        tt1=(TextView)findViewById(R.id.t1);
        tt2=(TextView)findViewById(R.id.t2);
        tt1.setAnimation(animation);
        tt2.setAnimation(animation);
        Thread timer=new Thread()
        {
            @Override
            public void run() {
                try {
                    sleep(7000);
                    Intent intent=new Intent(getApplicationContext(),login.class);
                    finish();
                    startActivity(intent);
                    super.run();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        timer.start();
    }

}