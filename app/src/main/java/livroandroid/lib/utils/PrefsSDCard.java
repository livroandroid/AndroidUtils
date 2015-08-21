package livroandroid.lib.utils;

import android.content.Context;
import android.util.Log;

import java.io.File;

/**
 * Created by Ricardo Lecheta on 01/02/2015.
 */
public class PrefsSDCard {
    public static final String PREF_ID = "prefs";
    private static final String TAG = "PrefsSDCard";

    public static void setBoolean(Context context, String chave, boolean on) {
        setString(context, chave, on ? "1" : "0");
    }

    public static boolean getBoolean(Context context, String chave) {
        String s = getString(context, chave);
        boolean on = "1".equals(s);
        return on;
    }

    public static void setInteger(Context context, String chave, int valor) {
        setString(context, chave, String.valueOf(valor));
    }

    public static int getInteger(Context context, String chave) {
        String s = getString(context, chave);
        if (s != null) {
            return Integer.parseInt(s);
        }
        return 0;
    }

    public static void setString(Context context, String chave, String valor) {
        File f = SDCardUtils.getPublicFile("prefs", chave + ".txt");
        IOUtils.writeString(f, valor);
    }

    public static String getString(Context context, String chave) {
        File f = SDCardUtils.getPublicFile("prefs", chave + ".txt");
        Log.d("livroandroid", "getString: " + f);
        return IOUtils.readString(f);
    }
}
