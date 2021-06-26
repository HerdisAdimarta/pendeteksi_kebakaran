package android.skripsi.pendeteksikebakaran.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferencesProvider {
    private static SharedPreferencesProvider instance = new SharedPreferencesProvider();
    private static SharedPreferences preferences;

    public static SharedPreferencesProvider getInstance() {
        return instance;
    }
    SharedPreferences.Editor editor;

    private final String pref_id_user = "pref_id_user";
    private final String pref_fcm_token = "pref_fcm_token";

    public void clearSession(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

    public void set_pref_id_user(Context context, String datas) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
        editor.putString(pref_id_user, datas);
        editor.commit();
    }
    public String get_pref_id_user(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(pref_id_user, "");
    }

    public void set_pref_fcm_token(Context context, String datas) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
        editor.putString(pref_fcm_token, datas);
        editor.commit();
    }

    public String get_pref_fcm_token(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(pref_fcm_token, "");
    }

}