package livroandroid.lib.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Encapsula o acesso ao SharedPreferences
 */
public class Prefs {
    public static final String PREF_ID = "livroandroid";

    public static void setBoolean(Context context, String flag, boolean on) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(flag, on);
        editor.commit();
    }

    public static boolean getBoolean(Context context, String flag) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        boolean b = pref.getBoolean(flag, true);
        return b;
    }

    public static void setInteger(Context context, String flag, int valor) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(flag, valor);
        editor.commit();
    }

    public static int getInteger(Context context, String flag) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        int i = pref.getInt(flag, 0);
        return i;
    }

    public static void setString(Context context, String flag, String valor) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(flag, valor);
        editor.commit();
    }

    public static String getString(Context context, String flag) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        String s = pref.getString(flag, "");
        return s;
    }
}

