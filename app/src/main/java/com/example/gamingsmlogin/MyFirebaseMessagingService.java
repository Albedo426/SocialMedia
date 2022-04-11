package com.example.gamingsmlogin;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService  {

    private static final String TAG = "MyFirebaseMsgService";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //bildirim gelince ikisi alışıyor düzelt
        /*if (remoteMessage.getData().size() > 0) { // Data mesajı içeriyor mu
            Log.e(TAG, "Mesaj data içeriği: " + remoteMessage.getData());
            //sendNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        }*/
        if (remoteMessage.getNotification() != null) { //Notification mesajı içeriyor mu
            Log.e(TAG, "Notification Tittle: " + remoteMessage.getNotification().getTitle() +" "+"getNotificationGetBody: " + remoteMessage.getNotification().getBody() );
            staticHolder.SingleChatHolder=true;
            staticHolder.BoobleChatHolder=true;
            if(!SingleMessageChatActivity.active){
                sendNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
            }
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendNotification(String messageTitle, String messageBody) {
        String CHANNEL_ID = "pushNotfcatonSmGaming";
        String CHANNEL_NAME = "Notficaton Gaming";
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableVibration(true);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        int NOTIFICATION_ID = 52;//Notification id si, channel ile ilgisi bulunmuyor
        Notification notification = new Notification.Builder(this, CHANNEL_ID)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true)
                .build();
        NotificationManager managere = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        managere.notify(NOTIFICATION_ID, notification);
    }

}
