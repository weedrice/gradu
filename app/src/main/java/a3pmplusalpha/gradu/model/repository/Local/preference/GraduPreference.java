package a3pmplusalpha.gradu.model.repository.Local.preference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashSet;

public class GraduPreference {
    public static final String PREF_NAME_ID="Id";
    public static final String PREF_NAME_PASSWORD="Password";
    public static final String PREF_NAME_COOKIE="Cookies";

    private static GraduPreference graduPreference = null;

    private Context context;
    private SharedPreferences pref;

    public GraduPreference(Context context) {
        this.context = context;
        final String PREF_NAME = context.getPackageName();
        pref = this.context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
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

    public void putPref(String key, HashSet<String> hashSet) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putStringSet(key, hashSet);
        editor.apply();
    }

    public HashSet<String> getPref(String key, HashSet<String> hashSet) {
        try {
            return (HashSet<String>)pref.getStringSet(key, hashSet);
        } catch (Exception e) {
            e.printStackTrace();
            return hashSet;
        }
    }

    public String getPrefString(String key, String str) {
        try {
            return (String)pref.getString(key, str);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public void clearCookies(String key) {
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        Log.d("GP", "Cleared Cookies");
        editor.apply();
    }
}
