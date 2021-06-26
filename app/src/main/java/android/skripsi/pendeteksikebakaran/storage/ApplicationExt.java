package android.skripsi.pendeteksikebakaran.storage;

import android.content.Context;
import android.skripsi.pendeteksikebakaran.api.rest.REST_Controller;

import androidx.multidex.MultiDexApplication;

public class ApplicationExt  extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        setUp(this);
    }


    public static void setUp(Context context) {
        REST_Controller.setUp();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }


}
