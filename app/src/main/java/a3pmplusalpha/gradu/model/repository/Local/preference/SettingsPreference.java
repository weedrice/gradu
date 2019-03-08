package a3pmplusalpha.gradu.model.repository.Local.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SettingsPreference {
    private static final String PREF_ID="PREF_ID";
    private static final String PREF_PW="PREF_PW";

    static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void saveId(Context context, String id) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(PREF_ID, id);
        editor.apply();
    }

    public static void savePw(Context context, String password) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(PREF_PW, password);
        editor.apply();
    }

    public static String getId(Context context) {
        return getSharedPreferences(context).getString(PREF_ID, "");
    }

    public static String getPw(Context context) {
        return getSharedPreferences(context).getString(PREF_PW, "");
    }

    public static void clearSettings(Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.clear();
        editor.apply();
    }
}
