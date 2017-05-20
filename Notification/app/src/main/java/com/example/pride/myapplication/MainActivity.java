package com.example.pride.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;

public class MainActivity extends AppCompatActivity {
	
	/*
	
	Normally the notification will send from server to user, but in this case we don't have the server, 
	so the notification will send when the button pushed. User can see and interact with it.
	
	*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /*
    Intent intent = new Intent(this, Any.class); // You can out activity that you want to run after notification.
    TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
    stackBuilder.addParentStack(PushActivity.class);
    stackBuilder.addNextIntent(intent);
    PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
    */
    public void showNotification(View view) {
        Notification notification = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle("Guessing What")
                    .setContentText("Please gimme grade A")
                    .setAutoCancel(true)
                    /*.setContentIntent(intent) // This part will redirect user that touch notification message to next activity*/
                    .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1000, notification);

    }

}