package com.example.project18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project18.DatabaseHelper;
import com.example.project18.DatabaseContract;

import static com.example.project18.DatabaseContract.Users.Col_Uemail;
import static com.example.project18.DatabaseContract.Users.Table_Name;

public class userupdate extends AppCompatActivity {

    String email1, username, oldpassword1, oldpassword2, newpassword;
    TextView t1, t2;
    EditText e1, e2;
    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userupdate);
        Intent intent = getIntent();
        id=Long.valueOf(intent.getStringExtra("userid"));
        email1 = intent.getStringExtra("email");
        username = intent.getStringExtra("name");
        oldpassword1 = intent.getStringExtra("password");
        dbHelper = new DatabaseHelper(this);
        t1 = findViewById(R.id.useremail1);
        t2 = findViewById(R.id.fullusername1);
        t1.setText(username);
        t2.setText(email1);
        e1 = findViewById(R.id.passss);
        e2 = findViewById(R.id.confirmpassss);
    }

    public void tapme1(View view) {
        db = dbHelper.getWritableDatabase();
        if (view.getId() == R.id.tapme1) {
            oldpassword2 = e1.getText().toString();
            newpassword = e2.getText().toString();
            if (oldpassword2 != null || newpassword != null) {
                if (oldpassword2.equals(oldpassword1)) {
                    if (newpassword.length() > 7) {
                        ContentValues v = new ContentValues();
                        v.put(DatabaseContract.Users.Col_Password, newpassword);
                       int i= db.update(Table_Name, v, DatabaseContract.Users._ID + "=" + id, null);
                        if (i > 0) {
                            Toast toast = Toast.makeText(userupdate.this, "Password Update", Toast.LENGTH_LONG);
                            toast.show();
                        }


                    } else {
                        Toast toast = Toast.makeText(userupdate.this, "Password too short", Toast.LENGTH_LONG);
                        toast.show();
                    }

                    } else {
                    Toast toast = Toast.makeText(userupdate.this, "Password not matched", Toast.LENGTH_LONG);
                    toast.show();
                }
            } else {
                Toast toast = Toast.makeText(userupdate.this, "Field is Empty", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }
}
