package com.example.project18;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.content.res.ResourcesCompat;

        import android.app.AlertDialog;
        import android.app.DatePickerDialog;
        import android.content.ContentValues;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.graphics.Typeface;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.Toast;
        import java.text.SimpleDateFormat;
        import java.util.Calendar;
        import java.util.Locale;
        import static com.example.project18.DatabaseContract.UserNotes.TABLE_NOTES_NAME;

public class editnotes extends AppCompatActivity {

    EditText ed1,ed2,ed3;
    AlertDialog.Builder builder;
    String dateofnote,title,notes;
    ContentValues values;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Long USERID,id1;
    int NotesId;
    String myFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editnotes);
        Intent intent = getIntent();
        USERID=Long.valueOf(intent.getStringExtra("Userid"));
        id1=Long.valueOf(intent.getStringExtra("id"));
        String b1=intent.getStringExtra("fontstyle");
        String a2=intent.getStringExtra("datestyle");
        String a3=intent.getStringExtra("fontsize");
        ed1 = findViewById(R.id.date);
        ed2=findViewById(R.id.title);
        ed3=findViewById(R.id.body);
        if(a2!=null)
        {
            myFormat=a2;
        }
        else
        {
            myFormat="dd/MM/yy";
        }
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

        ed1.setText(intent.getStringExtra("date"));
        ed2.setText(intent.getStringExtra("title"));
        ed3.setText(intent.getStringExtra("notes"));
        dbHelper=new DatabaseHelper(this);
        db=dbHelper.getWritableDatabase();
        values=new ContentValues();
        builder = new AlertDialog.Builder(this);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.writermenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle item selection
        if (item.getItemId() == R.id.confirm)
        {
            builder.setMessage("Are you sure?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            finish();
                            dateofnote=ed1.getText().toString();
                            title=ed2.getText().toString();
                            notes=ed3.getText().toString();
                            values.put(DatabaseContract.UserNotes.COL_DATE,dateofnote);
                            values.put(DatabaseContract.UserNotes.Col_TITLE,title);
                            values.put(DatabaseContract.UserNotes.Col_NOTES,notes);
                            values.put(DatabaseContract.UserNotes.COL_UserID, USERID);
                           // NotesId=db.update(DatabaseContract.UserNotes.TABLE_NOTES_NAME,values,DatabaseContract.UserNotes.COL_ID+"=?",new String[]{String.valueOf(id)});
                            NotesId=db.update(TABLE_NOTES_NAME, values, DatabaseContract.UserNotes.COL_ID +"="+ id1, null);
                            if(NotesId>0) {
                                Toast.makeText(editnotes.this, "Record Updated: ", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(editnotes.this, MainActivity.class);
                                intent.putExtra("userid",String.valueOf(USERID));
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(editnotes.this, " Record NOT Inserted: ", Toast.LENGTH_SHORT).show();


                            }
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            //  Action for 'NO' Button
                        }
                    });
            AlertDialog alert = builder.create();
            alert.setTitle("Save Notes");
            alert.show();
            return true;
        }
       else {
            return super.onOptionsItemSelected(item);
        }
    }

    final Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth)
        {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };
    private void updateLabel()
    {
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        ed1.setText(sdf.format(myCalendar.getTime()));
    }
    public void datepicker (View view){
        new DatePickerDialog(editnotes.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}
