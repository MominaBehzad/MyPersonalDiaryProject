package com.example.project18;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity
{
    TextView signup,forgetpass;
    EditText un,pass;
    Button signin;Button admin;
    DatabaseHelper dbHelper ;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        RelativeLayout relativeLayout=findViewById(R.id.layout);


        admin=findViewById(R.id.b2facebook);
        signup=findViewById(R.id.donthaveaccount);
        forgetpass=findViewById(R.id.forgetpass);
        signin=findViewById(R.id.b1);
        un=findViewById(R.id.username);
        pass=findViewById(R.id.pass);
        dbHelper = new DatabaseHelper(this);
        un.addTextChangedListener(loginTextWatcher);
        pass.addTextChangedListener(loginTextWatcher);



        admin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), loginadmin.class);
                startActivity(intent);
            }
        });


        un.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void  onFocusChange(View v, boolean hasFocus)
            {
                if(hasWindowFocus())
                {
                    if(un.getText().toString().trim().isEmpty())
                    {
                        un.setError("Feild can't be left Empty! ");
                    }

                }
                else
                {
                    un.setError(null);
                }
            }
        });
        pass.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void  onFocusChange(View v, boolean hasFocus)
            {
                if(hasWindowFocus())
                {
                    if(pass.getText().toString().trim().isEmpty())
                    {
                        pass.setError("Feild can't be left Empty! ");
                    }
                }
                else
                {
                    pass.setError(null);
                }
            }
        });



        signin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean flag=true;
                db = dbHelper.getReadableDatabase();
                String[] columns = {DatabaseContract.Users._ID, DatabaseContract.Users.Col_Uemail, DatabaseContract.Users.Col_Password };
                Cursor c = db.query(DatabaseContract.Users.Table_Name, columns, null, null, null, null, null);
                while (c.moveToNext())
                {
                    String emailusername= c.getString(1) ;
                    String password= c.getString(2);
                    String Userid=c.getString(0);
                    if(un.getText().toString().trim().equals(emailusername)  &&  pass.getText().toString().trim().equals(password))
                    {
                        Intent i=new Intent(login.this,MainActivity.class);
                        i.putExtra("userid",Userid);
                        startActivity(i);
                        flag=false;
                    }


                }
                c.close();
                db.close();

                if(flag==true)
                {
                    un.setError("Enter the correct username");
                    pass.setError("Enter correct password");
                    un.setText(null);
                    pass.setText(null);
                }

            }
        });








        String text="Don't have an account? sign up";
        String text1="forget password?";
        SpannableString ss=new SpannableString(text);
        SpannableString sss=new SpannableString(text1);

        ClickableSpan clickableSpan1=new ClickableSpan()
        {
            @Override
            public void onClick(@NonNull View widget)
            {
                Intent intent = new Intent(widget.getContext(), forget.class);
                startActivity(intent);
            }
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.WHITE);
            }
        };
        sss.setSpan(clickableSpan1,0,text1.length(),Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        forgetpass.setText(sss);
        forgetpass.setMovementMethod(LinkMovementMethod.getInstance());

        ClickableSpan clickableSpan=new ClickableSpan()
        {
            @Override
            public void onClick(@NonNull View widget)
            {
                Intent intent = new Intent(widget.getContext(), signin.class);
                startActivity(intent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.WHITE);
            }
        };
        ss.setSpan(clickableSpan,23,30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        signup.setText(ss);
        signup.setMovementMethod(LinkMovementMethod.getInstance());

    }


    private TextWatcher loginTextWatcher=new TextWatcher()
    {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            String fullusernameinput=un.getText().toString().trim();
            String passinput=pass.getText().toString().trim();

            signin.setEnabled(!fullusernameinput.isEmpty() && !passinput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s)
        {

        }


    };

}
