package com.example.project18;


import androidx.appcompat.app.AppCompatActivity;

        import android.content.ContentValues;
        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

public class rate2 extends AppCompatActivity {
    Button btn;
    TextView text;
    String id;
    String star;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate2);
        btn=(Button)findViewById(R.id.b1);
        text=(TextView)findViewById(R.id.t1);
        Intent intent=getIntent();
        star=intent.getStringExtra("rate");
        id=intent.getStringExtra("userid");
        text.setText("your rating:"+star);
    }
    public void sendF(View v)
    {
        Intent intent=new Intent(this,feedback1.class);
        intent.putExtra("userid",id);
        intent.putExtra("rate",star.toString());
        startActivity(intent);
    }
}