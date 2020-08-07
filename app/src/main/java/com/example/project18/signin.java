package com.example.project18;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
public class signin extends AppCompatActivity {
    CheckBox iagree; Button getstart; Button tap,admin;
    EditText fullname,email,pass,confirmpass,hint;
    TextView description;
    DatabaseHelper dbHelper ;
    SQLiteDatabase db;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        final RelativeLayout relativeLayout=findViewById(R.id.layout1);

        iagree=findViewById(R.id.c1);
        description=findViewById(R.id.desofNotification);
        getstart=findViewById(R.id.getstarted);
        tap=findViewById(R.id.tapme);
        fullname=findViewById(R.id.fullusername);
        email=findViewById(R.id.useremail);
        pass=findViewById(R.id.passs);
        confirmpass=findViewById(R.id.confirmpasss);
        hint=findViewById(R.id.hint);

        fullname.addTextChangedListener(loginTextWatcher);
        email.addTextChangedListener(loginTextWatcher);
        pass.addTextChangedListener(loginTextWatcher);
        confirmpass.addTextChangedListener(loginTextWatcher);
        hint.addTextChangedListener(loginTextWatcher);

        fullname.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void    onFocusChange(View v, boolean hasFocus)
            {
                if(hasWindowFocus())
                {
                    if (fullname.getText().toString().trim().isEmpty())
                    {
                        fullname.setError("Feild can't be left Empty! ");
                        tap.setEnabled(false);
                    }
                    if(fullname.getText().toString().trim().length()<4 && fullname.getText().toString().trim().length()>1)
                    {
                        fullname.setError("Required Min 4 characters ");
                        tap.setEnabled(false);
                    }
                    if(fullname.getText().toString().trim().length()>10)
                    {
                        fullname.setError("Max 10 characters allowed");
                        tap.setEnabled(false);
                    }
                }
                else
                {
                    fullname.setError(null);
                }
            }
        });

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
                        tap.setEnabled(false);
                    }

                    if(email.getText().toString().trim().length()>24)
                    {
                        email.setError(" Max 24 characters allowed ");
                        tap.setEnabled(false);
                    }

                    if(email.getText().toString().trim().length()<8 && email.getText().toString().trim().length()>1)
                    {
                        email.setError("Required Min 8 characters ");
                        tap.setEnabled(false);
                    }

                }
                else
                {
                    email.setError(null);
                }
            }
        });


        pass.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {

                if(hasWindowFocus())
                {
                    if (pass.getText().toString().trim().isEmpty())
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

        hint.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {

                if(hasWindowFocus())
                {
                    if (hint.getText().toString().trim().isEmpty())
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

        confirmpass.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {

                if(hasWindowFocus())
                {
                    if (confirmpass.getText().toString().trim().isEmpty())
                    {
                        confirmpass.setError("Feild can't be left Empty! ");
                    }
                }
                else
                {
                    confirmpass.setError(null);
                }

            }
        });

        dbHelper = new DatabaseHelper(this);

        tap.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                db = dbHelper.getWritableDatabase();
                if (pass.getText().toString().equals(confirmpass.getText().toString()))
                {

                    ContentValues values = new ContentValues();
                    values.put(DatabaseContract.Users.Col_UFullName, fullname.getText().toString());
                    values.put(DatabaseContract.Users.Col_Uemail, email.getText().toString());
                    values.put(DatabaseContract.Users.Col_Password, pass.getText().toString());
                    values.put(DatabaseContract.Users.Col_Hint, hint.getText().toString());
                    long newRowId = db.insert(DatabaseContract.Users.Table_Name, null, values);

                    if (newRowId > 0)
                    {
                        Toast.makeText(getApplicationContext(), "New Record Inserted: " + newRowId, Toast.LENGTH_SHORT).show();
                        layoutInflater=(LayoutInflater)getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                        ViewGroup container=(ViewGroup)layoutInflater.inflate(R.layout.popupforcongrats,null);
                        popupWindow=new PopupWindow(container,800,500,true);
                        popupWindow.showAtLocation(relativeLayout, Gravity.NO_GRAVITY,150,700);
                        container.setOnTouchListener(new View.OnTouchListener()
                        {
                            @Override
                            public boolean onTouch(View v, MotionEvent event)
                            {
                                popupWindow.dismiss();
                                return false;
                            }
                        });
                        iagree.setVisibility(v.VISIBLE);
                        description.setVisibility(v.VISIBLE);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Error occured while inserting", Toast.LENGTH_SHORT).show();
                        email.setError("Email already exist,Try another username.");
                        iagree.setVisibility(v.GONE);
                        description.setVisibility(v.GONE);
                    }
                    db.close();

                }
                else
                {
                    pass.setError("password doesn't match.. RE_TYPE your password please!");
                    confirmpass.setError("password doesn't match.. RE_TYPE your password please!");
                    iagree.setVisibility(v.GONE);
                    description.setVisibility(v.GONE);
                }
            }
        });

        iagree.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(iagree.isChecked())
                {
                    getstart.setEnabled(true);
                    getstart.setVisibility(v.VISIBLE);
                }
                else
                {
                    getstart.setEnabled(false);
                    getstart.setVisibility(v.GONE);
                }
            }
        });
        getstart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
       Intent i=new Intent(signin.this,login.class);
       startActivity(i);
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
            String fullnameinput=fullname.getText().toString().trim();
            String emailinput=email.getText().toString().trim();
            String passinput=pass.getText().toString().trim();
            String confirmpassinput=confirmpass.getText().toString().trim();
            String hintinput=hint.getText().toString().trim();
            tap.setEnabled(!fullnameinput.isEmpty() && !emailinput.isEmpty() && !passinput.isEmpty() && !confirmpassinput.isEmpty() && !hintinput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s)
        {

        }


    };

   /* boolean inputcountcheck() {
        if (fullname.getText().toString().trim().length() >= 8 && email.getText().toString().trim().length() >= 16) {
            return true;
        }
        else
        {
            if((fullname.getText().toString().trim().length() <= 8))
            {
                Toast.makeText(getApplicationContext(), "Enter Atleast 8 characters in name feild", Toast.LENGTH_SHORT);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Enter Atleast 16 characters in email feild", Toast.LENGTH_SHORT);
            }
            return false;
        }
    }*/
}