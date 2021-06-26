package com.skripsi.pendeteksikebakaran.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.skripsi.pendeteksikebakaran.R;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.RemoteMessage;
import com.skripsi.pendeteksikebakaran.MainActivity;

import java.util.Map;

public class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService  {
    AppCompatActivity mActivity;
    private static final String TAG = "FirebaseMessagingServic";
    public static int Status_Notif = 0;


    public MyFirebaseMessagingService() {
        Log.e("Notifikasi cuuy 2:", "Ini Notif2" );


    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.e(TAG, "dataBack: ");
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Message data payload: " + remoteMessage.getData());

            sendNotification(remoteMessage);

        }
        Log.e("Notifikasi cuuy :", "Ini Notif" );
        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            String title = remoteMessage.getNotification().getTitle(); //get title
            String body = remoteMessage.getNotification().getBody(); //get message
            String image = remoteMessage.getNotification().getIcon(); //get image
            String action = "url"; //get image
            String action_destination = remoteMessage.getNotification().getClickAction(); //get click_action

            Log.e(TAG, "Full Notif :" +remoteMessage.getNotification());
            Log.d(TAG, "Message Notification Title: " + title);
            Log.d(TAG, "Message Notification Body: " + body);
            Log.d(TAG, "Message Notification action_destination: " + action_destination);
            Log.e(TAG,"ini pesan->"+body);

            if(body.equalsIgnoreCase("Silahkan lanjutkan ke cek fisik")){
//                localBroadcast(body);
            }
            Map<String, String> data_notif = remoteMessage.getData();
            for (String key : data_notif.keySet()) {
                Object value = data_notif.get(key);
                Log.e("data_notif", String.format("%s %s (%s)", key,
                        value.toString(), value));
            }

            String aksi = data_notif.get("action");
            switch (aksi){
                case "cek_hp2" :
                    Intent intent1 = new Intent(this, MainActivity.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_SINGLE_TOP |
                            Intent.FLAG_ACTIVITY_NO_HISTORY);
//                    intent1.putExtra("id_cek",data_notif.get("id_cek"));
//                    intent1.putExtra("kode_unik",data_notif.get("kode_unik"));
                    startActivity(intent1);
                    break;
            }
        }
    }

    void localBroadcast(String message){
        Intent intent = new Intent("Result");
        // You can also include some extra data.
        intent.putExtra("Status", message);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
        Log.e("pesan masuk:", ""+message);
    }

    void localBroadcastPrice(String kode_unik){
        Intent intent = new Intent("Result_kode");
        // You can also include some extra data.
        intent.putExtra("kode_unik", kode_unik);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);

    }
    //
    @Override
    public void onDeletedMessages() {
        Log.e("pesan_delete:", "delete");
    }

    private void sendNotification(RemoteMessage remoteMessage) {
        Map<String, String> data_notif = remoteMessage.getData();
        Intent intent = null;
        String title = remoteMessage.getNotification().getTitle(); //get title
        String body = remoteMessage.getNotification().getBody(); //get message
        String image = remoteMessage.getNotification().getIcon(); //get image
        String action = "url"; //get image
        String action_destination = remoteMessage.getNotification().getClickAction(); //get click_action
        for (String key : data_notif.keySet()) {
            Object value = data_notif.get(key);
            Log.e("data_notif", String.format("%s %s (%s)", key,
                    value.toString(), value));
        }

        Log.e("ini_notif","bodi__"+body);

        String aksi = data_notif.get("action");
        switch (aksi){
            case "cek_hp2" :
                intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("id_cek",data_notif.get("id_cek"));
                intent.putExtra("kode_unik",data_notif.get("kode_unik"));
//                startActivity(intent);
                break;
        }

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, getString(R.string.default_notification_channel_id))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

    }

    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
        Log.e("messageSent",s);
    }

    @Override
    public void onSendError(String s, Exception e) {
        super.onSendError(s, e);
        Log.e("messageError",s+" eksepsi"+e.getLocalizedMessage());
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("newToken",s);
    }

    private Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Log.e("onCreate","ada_duaan");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy","ada_duaan");
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.e("ini_onRebin","benar");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("ini_unbind","benar");
        return super.onUnbind(intent);
    }
}