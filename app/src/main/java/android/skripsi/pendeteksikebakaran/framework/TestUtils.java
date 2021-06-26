package android.skripsi.pendeteksikebakaran.framework;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

class TestUtils {
    static long mLastClickTime = 0;

    public TestUtils(Context context) {

    }

    public TestUtils() {

    }

    public static boolean preventingDoubleClick() {
        Log.d("CT Now", "" + mLastClickTime);
        Log.d("CT Sisa", "" + ((SystemClock.elapsedRealtime() - mLastClickTime)));
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            Log.d("STATUS", "DOUBLE");
            return true;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        return false;

    }
}
