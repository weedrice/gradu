package a3pmplusalpha.gradu.model.repository.Local.preference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class GraduPreference {
    public static final String PREF_NAME_ID="Id";
    public static final String PREF_NAME_PASSWORD="Password";

    private static GraduPreference graduPreference = null;

    private SharedPreferences pref;

    private GraduPreference(Context context) {
        final String PREF_NAME = context.getPackageName();
        pref = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
    }

    public static GraduPreference getSharedPreferences(Context context) {
        if(graduPreference == null) {
            graduPreference = new GraduPreference(context);
        }

        return graduPreference;
    }

    public void putPrefString(String key, String str) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, str);
        editor.apply();
    }

    public String getPrefString(String key, String str) {
        try {
            return pref.getString(key, str);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public void clearPrefString(String key) {
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        editor.apply();
    }
}
