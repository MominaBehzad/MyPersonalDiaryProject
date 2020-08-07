package com.example.project18;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.List;
import static com.example.project18.DatabaseContract.UserNotes.TABLE_NOTES_NAME;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    SharedPreferences sharedPreferences, app_preferences;
    SharedPreferences.Editor editor;
    long USERID, Notesid;
    SQLiteDatabase db;
    String fontsize,fontstyle,datestyle;
    DatabaseHelper dbHelper;
    Cursor c;
    TextView name, email;
    List<NotesUser> list;
    AlertDialog.Builder builder;
    NavigationView navigationView;
    NotesUser[] data;
    String date, title, notes;
    String username, useremail, password;
    ListView NotesList;
    Adapter adapter;
    LazyAdapter adapt;
    List<String> itemsD, itemsT, itemsB;
    NotesUser selected;
    String item1[], item2[], item3[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View inflatedView = getLayoutInflater().inflate(R.layout.nav_header, null);
        Intent intent = getIntent();
        USERID = Long.valueOf(intent.getStringExtra("userid"));
        NotesList = findViewById(R.id.list1);
        list = new ArrayList<NotesUser>(20);
        itemsD = new ArrayList<String>();
        itemsT = new ArrayList<String>();
        itemsB = new ArrayList<String>();
        data = new NotesUser[20];
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle;
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        name = inflatedView.<TextView>findViewById(R.id.usernameid);
        email = inflatedView.findViewById(R.id.useremailid);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();
        String[] columns = {DatabaseContract.Users._ID, DatabaseContract.Users.Col_UFullName, DatabaseContract.Users.Col_Uemail, DatabaseContract.Users.Col_Password};
        Cursor c = db.query(DatabaseContract.Users.Table_Name, columns, null, null, null, null, null);
        while (c.moveToNext()) {
            if (c.getString(0).equals(String.valueOf(USERID)))
            {
                username = c.getString(1);
                useremail = c.getString(2);
                password = c.getString(3);
            }
        }
        c.moveToFirst();
        c.close();
        name.setText(username);
        email.setText(useremail);
        fetchnotes();


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notesmenu, menu);

    }

    public void fetchnotes() {

        String[] columns = {DatabaseContract.UserNotes.COL_ID, DatabaseContract.UserNotes.COL_UserID, DatabaseContract.UserNotes.COL_DATE, DatabaseContract.UserNotes.Col_TITLE, DatabaseContract.UserNotes.Col_NOTES};
        Cursor c = db.query(TABLE_NOTES_NAME, columns, null, null, null, null, null);
        if (c == null) {
            Toast toast = Toast.makeText(this, "NO RECORDS FOUND", Toast.LENGTH_LONG);
        } else {
            int i = 0;
            while (c.moveToNext()) {
                if (c.getString(1).equals(String.valueOf(USERID))) {
                    Notesid = Long.parseLong(c.getString(c.getColumnIndex(DatabaseContract.UserNotes.COL_ID)));
                    date = c.getString(2);
                    title = c.getString(3);
                    notes = c.getString(4);
                    data[i] = new NotesUser(date, title, notes, Notesid);//null array exception
                    list.add(data[i]);
                    itemsD.add(data[i].getDate());
                    itemsB.add(data[i].getNotes());
                    itemsT.add(data[i].getTitle());
                    i++;
                }
            }
            c.moveToFirst();
            c.close();
        }
        if (itemsD != null && itemsT != null && itemsD != null) {
            item1 = new String[itemsD.size()];
            item1 = itemsD.toArray(item1);
            item2 = new String[itemsT.size()];
            item2 = itemsT.toArray(item2);
            item3 = new String[itemsB.size()];
            item3 = itemsB.toArray(item3);
            adapt = new LazyAdapter(MainActivity.this, itemsD, itemsT, itemsB);
            NotesList.setAdapter((ListAdapter) adapt);
            registerForContextMenu(NotesList);
            NotesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    // TODO Auto-generated method stub
                    String value = adapt.getItem(position).toString();

                        if(data[position].getTitle().equals(value))
                        {
                            date = data[position].getDate();
                            title = data[position].getTitle();
                            notes = data[position].getNotes();
                        }

                    Intent intent = new Intent(MainActivity.this, readnotes.class);
                    intent.putExtra("date", date);
                    intent.putExtra("title", title);
                    intent.putExtra("notes", notes);
                    if(datestyle != null)
                    {
                        intent.putExtra("datestyle",datestyle);
                    }
                    if(fontstyle != null)
                    {
                        intent.putExtra("fontstyle",fontstyle);
                    }
                    if(fontsize != null)
                    {
                        intent.putExtra("fontsize",fontsize);
                    }
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.edit:
                Intent intent = new Intent(MainActivity.this, editnotes.class);
                intent.putExtra("Userid", String.valueOf(USERID));
                intent.putExtra("id", String.valueOf(Notesid));
                intent.putExtra("date", date);
                intent.putExtra("title", title);
                intent.putExtra("notes", notes);
                if(datestyle != null)
                {
                    intent.putExtra("datestyle",datestyle);
                }
                if(fontstyle != null)
                {
                    intent.putExtra("fontstyle",fontstyle);
                }
                if(fontsize != null)
                {
                    intent.putExtra("fontsize",fontsize);
                }
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Edit: " + info.position, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.delete:
                selected = list.get(info.position);
                builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Do you want to delete this Note?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                int del = db.delete(TABLE_NOTES_NAME, DatabaseContract.UserNotes.COL_ID + " = ?",
                                        new String[]{String.valueOf(Notesid)});
                                list.remove(info.position);
                                itemsD.remove(info.position);
                                itemsT.remove(info.position);
                                itemsB.remove(info.position);
                                ((BaseAdapter) adapt).notifyDataSetChanged();
                                if (del > 0) {
                                    Toast.makeText(getApplicationContext(), "Note Deleted ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        Intent intent;
        switch (id) {
            case R.id.nav_settings:

                intent = new Intent(this,userupdate.class);
                intent.putExtra("userid", String.valueOf(USERID));
                intent.putExtra("email",useremail);
                intent.putExtra("name",username);
                intent.putExtra("password",password);
                startActivity(intent);
                break;
            case R.id.nav_color:
                intent = new Intent(this, colors.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.nav_reminder:
                intent = new Intent(this, Reminder.class);
                startActivity(intent);
                break;
            case R.id.nav_rate:

                intent = new Intent(this, rate1.class);
                startActivity(intent);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem menuItem=menu.findItem(R.id.search);
        SearchView searchView;
        searchView=(SearchView)menuItem.getActionView();
        searchView.setQueryHint("Type Here To Search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchnotes();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<NotesUser> temp=new ArrayList<NotesUser>();
                List<String> tempd=new ArrayList<>();
                List<String> tempt=new ArrayList<>();
                List<String> tempb=new ArrayList<>();
                for(int i=0;i<list.size();i++)
                {
                    if(list.get(i).getTitle().contains(newText) || list.get(i).getNotes().contains(newText))
                    {
                        temp.add(list.get(i));
                        tempd.add(list.get(i).getDate());
                        tempt.add(list.get(i).getTitle());
                        tempb.add(list.get(i).getNotes());
                    }
                }
                adapt=new LazyAdapter(MainActivity.this,tempd,tempt,tempb);
                NotesList.setAdapter(adapt);
                return false;
            }
        });
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.search) {

        }

        return super.onOptionsItemSelected(item);

    }

    public void addfile(View view) {
        if (view.getId() == R.id.add) {
            Intent intent = new Intent(this, writenotes.class);
            intent.putExtra("Userid", String.valueOf(USERID));
            if(datestyle != null)
            {
                intent.putExtra("datestyle",datestyle);
            }
            if(fontstyle != null)
            {
                intent.putExtra("fontstyle",fontstyle);
            }
            if(fontsize != null)
            {
                intent.putExtra("fontsize",fontsize);
            }
            startActivity(intent);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check if the request code is same as what is passed
        if (requestCode == 1) {
            fontsize=data.getStringExtra("textsize");
            fontstyle=data.getStringExtra("fontfamily");
            datestyle=data.getStringExtra("datestyle");
        }
    }
}