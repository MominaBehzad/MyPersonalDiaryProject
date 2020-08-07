package com.example.project18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class loginadmin extends AppCompatActivity
{
    String name="admin@gmail.com",password="admin";
    Button signin;
    EditText us,p;
    DatabaseHelper DdbHelper ;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginadmin);
        RelativeLayout relativeLayout=findViewById(R.id.layoutadmin);
        signin=findViewById(R.id.siginas);
        us=findViewById(R.id.adusername);
        p=findViewById(R.id.adpass);
        us.addTextChangedListener(loginTextWatcher);
        p.addTextChangedListener(loginTextWatcher);
        signin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(us.getText().toString().trim().equals(name) && p.getText().toString().trim().equals(password))
                {
                    Intent i=new Intent(loginadmin.this,adminview.class);
                    startActivity(i);
                }
                else
                {
                    if(!us.getText().toString().trim().equals(name))
                    {
                        us.setError("Email not recognized for admin pannel");
                    }
                    else
                    {
                        p.setError("password incorrect! Try Again. ");
                    }
                }

            }
        });



    }

    private TextWatcher loginTextWatcher=new TextWatcher()
    {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            String fullnameinput=us.getText().toString().trim();
            String emailinput=p.getText().toString().trim();
            signin.setEnabled(!fullnameinput.isEmpty() && !emailinput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s)
        {

        }


    };


}
