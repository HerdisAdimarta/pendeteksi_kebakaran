package android.skripsi.pendeteksikebakaran.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.skripsi.pendeteksikebakaran.utils.MyFirebaseMessagingService;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent myIntent = new Intent(context, MyFirebaseMessagingService.class);
        context.startService(myIntent);

    }
}
