package livroandroid.lib.utils;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <pre>
 * Salva arquvos no formato chave e valor.
 *
 * Se chamar o método setString("nome","Ricardo"), vai salvar o arquivo
 *
 * /data/data/app/files.nome.txt
 *
 * </pre>
 */
public class PrefsFile {
    public static final String PREF_ID = "prefs";
    private static final String TAG = "PrefsFile";

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
        try {
            chave += ".txt";
            File file = context.getFileStreamPath(chave);
            FileOutputStream out = context.openFileOutput(chave, Context.MODE_PRIVATE);
            IOUtils.writeString(out, valor);
            Log.d(TAG, "PrefsFile.setString file: " + file + " >  " + valor);
        } catch (IOException ex) {
            Log.e(TAG, ex.getMessage(), ex);
        }
    }

    public static String getString(Context context, String chave) {
        chave += ".txt";
        File f = context.getFileStreamPath(chave);
        String s = IOUtils.readString(f);
        Log.d(TAG, "PrefsFile.getString file: " + f + " >  " + s);
        return s;
    }
}


