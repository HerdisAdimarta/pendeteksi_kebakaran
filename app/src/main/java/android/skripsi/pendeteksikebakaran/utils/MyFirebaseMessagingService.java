package android.skripsi.pendeteksikebakaran.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.skripsi.pendeteksikebakaran.MainActivity;
import android.skripsi.pendeteksikebakaran.R;
import android.skripsi.pendeteksikebakaran.api.rest.REST_Controller;
import android.skripsi.pendeteksikebakaran.storage.SharedPreferencesProvider;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d(TAG, "onNewToken: " + token);

        sendUpdateToken(token);
    }

    private void sendUpdateToken(String token) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("token", token);
        builder.addFormDataPart("id_user", SharedPreferencesProvider.getInstance().get_pref_id_user(getApplicationContext()));
        RequestBody requestBody = builder.build();
        REST_Controller.CLIENT.updateTokenDevice(requestBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    SharedPreferencesProvider.getInstance().set_pref_fcm_token(getApplicationContext(), token);
                } else {
                    Log.e(TAG, "onResponse: ERROR TOKEN" );
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onResponse: ERROR TOKEN" );
            }
        });
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getNotification() != null) {
            sendNotification(remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getTitle());
        }

    }

    private void sendNotification(String messageBody, String messageTitle) {
        String channelId  = getString(R.string.default_notification_channel_id);
        String channelName = getString(R.string.default_notification_channel_name);

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(messageTitle)
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            /* Create or update. */
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            notificationBuilder.setChannelId(channelId);
            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(channel);
            }
        }
        Notification notification = notificationBuilder.build();
        if (mNotificationManager != null) {
            mNotificationManager.notify(0, notification);
        }

        Intent notifyFinishIntent = new Intent(MainActivity.ACTION_NEW_TASK);
        sendBroadcast(notifyFinishIntent);
    }
}
