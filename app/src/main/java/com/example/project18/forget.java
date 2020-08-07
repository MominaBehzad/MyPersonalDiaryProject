package com.example.project18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class forget extends AppCompatActivity
{
    EditText email,hint;
    CheckBox done;
    TextView yourpassis;
    TextView pasdisplay;
    DatabaseHelper dbHelper ;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        email=findViewById(R.id.newusername);
        done=findViewById(R.id.c11);
        hint=findViewById(R.id.hintt);
        dbHelper = new DatabaseHelper(this);
        yourpassis=findViewById(R.id.des);
        pasdisplay=findViewById(R.id.passtextview);

        email.addTextChangedListener(loginTextWatcher);
        hint.addTextChangedListener(loginTextWatcher);


        email.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void  onFocusChange(View v, boolean hasFocus)
            {
                if(hasWindowFocus())
                {
                    if(email.getText().toString().trim().isEmpty())
                    {
                        email.setError("Feild can't be left Empty! ");
                    }

                }
                else
                {
                    email.setError(null);
                }
            }
        });

        hint.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void  onFocusChange(View v, boolean hasFocus)
            {
                if(hasWindowFocus())
                {
                    if(hint.getText().toString().trim().isEmpty())
                    {
                        hint.setError("Feild can't be left Empty! ");
                    }
                }
                else
                {
                    hint.setError(null);
                }
            }
        });


        done.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String emailusername=new String();
                String hintagainstusername=new String();
                boolean flag=true;
                db=dbHelper.getWritableDatabase();
                if(done.isChecked())
                {
                    String[] columns = { DatabaseContract.Users.Col_Uemail,DatabaseContract.Users.Col_Hint, DatabaseContract.Users.Col_Password };
                    Cursor c = db.query(DatabaseContract.Users.Table_Name, columns, null, null, null, null, null);
                    while (c.moveToNext())
                    {
                        emailusername= c.getString(0) ;
                        hintagainstusername= c.getString(1) ;
                        String password= c.getString(2);
                        if(email.getText().toString().trim().equals(emailusername)  &&  hint.getText().toString().trim().equals(hintagainstusername))
                        {
                            yourpassis.setVisibility(v.VISIBLE);
                            pasdisplay.setText(password);
                            pasdisplay.setVisibility(v.VISIBLE);
                            flag=false;
                        }
                    }
                    c.close();
                    db.close();

                    if(flag==true)
                    {
                        if(!email.getText().toString().trim().equals(emailusername))
                        {
                            email.setError("Invalid Email");
                            email.setText(null);
                        }
                        if(!hint.getText().toString().trim().equals(hintagainstusername))
                        {
                            hint.setError("Invalid Hint");
                            hint.setText(null);
                        }
                        done.setEnabled(false);
                        done.setChecked(false);
                    }
                }
                else
                {
                    yourpassis.setVisibility(v.GONE);
                    pasdisplay.setVisibility(v.GONE);
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
            String emailinput=email.getText().toString().trim();
            String hintinput=hint.getText().toString().trim();
            done.setEnabled(!emailinput.isEmpty() && !hintinput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s)
        {

        }


    };

}
