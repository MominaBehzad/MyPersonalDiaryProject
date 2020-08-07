package com.example.project18;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Reminder extends AppCompatActivity {
    TimePicker tp1;
    long alarmstart;
    Button sb,cb;
    private final String CHANNEL_ID="personal_channel";
    int NOTIFICATION_ID=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        createNotificationChannel();
        sb=(Button)findViewById(R.id.setb1);
        cb=(Button)findViewById(R.id.cancelb2);
        tp1=(TimePicker)findViewById(R.id.tp);
    }
    public void click(View v) {

        //set notification and text
        Intent intent = new Intent(Reminder.this, AlarmReceiver.class);
        intent.putExtra("notify", NOTIFICATION_ID);
        intent.putExtra("todo", "time to write something");
        PendingIntent alarmIntent = PendingIntent.getBroadcast(Reminder.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        switch (v.getId()) {
            case R.id.setb1:
                int hr = tp1.getCurrentHour();
                int min = tp1.getCurrentMinute();
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hr);
                startTime.set(Calendar.MINUTE, min);
                startTime.set(Calendar.SECOND, 0);
                alarmstart = startTime.getTimeInMillis();
                alarm.set(AlarmManager.RTC_WAKEUP, alarmstart, alarmIntent);
                Toast.makeText(this, "Done!!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cancelb2:
                alarm.cancel(alarmIntent);
                Toast.makeText(this, "cancel!!", Toast.LENGTH_LONG);
                break;
        }
    }
    private void createNotificationChannel()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name ="Channel Name";
            String description = "channel_description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }
}