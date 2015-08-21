package livroandroid.lib.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Classe utilitária para salvar criar arquivos no sdcard
 */
public class SDCardUtils {
    private static final String TAG = SDCardUtils.class.getName();

    /**
     * Cria um arquivo público na raiz do sdcard
     */
    public static File getPublicFile(String fileName) {
        File sdCardDir = Environment.getExternalStorageDirectory();
        return createFile(sdCardDir, fileName);
    }

    /**
     * Cria um arquivo público na raiz do sdcard
     *
     * @param type DIRECTORY_MUSIC, DIRECTORY_PODCASTS, DIRECTORY_RINGTONES, DIRECTORY_ALARMS, DIRECTORY_NOTIFICATIONS, DIRECTORY_PICTURES, DIRECTORY_MOVIES, DIRECTORY_DOWNLOADS, ou DIRECTORY_DCIM
     * @return
     */
    public static File getPublicFile(String fileName, String type) {
        File sdCardDir = Environment.getExternalStoragePublicDirectory(type);
        return createFile(sdCardDir, fileName);
    }

    /**
     * Cria um arquivo privado na raiz do sdcard
     *
     * @param context
     * @param fileName
     * @return
     */
    public static File getPrivateFile(Context context, String fileName) {
        File sdCardDir = context.getExternalFilesDir(null);
        return createFile(sdCardDir, fileName);
    }

    /**
     * Cria um arquivo privado na raiz do sdcard
     *
     * @param context
     * @param fileName
     * @param type     @param type DIRECTORY_MUSIC, DIRECTORY_PODCASTS, DIRECTORY_RINGTONES, DIRECTORY_ALARMS, DIRECTORY_NOTIFICATIONS, DIRECTORY_PICTURES, DIRECTORY_MOVIES, DIRECTORY_DOWNLOADS
     * @return
     */
    public static File getPrivateFile(Context context, String fileName, String type) {
        File sdCardDir = context.getExternalFilesDir(type);
        return createFile(sdCardDir, fileName);
    }

    /**
     * Cria o arquivo no SDCard na pasta informada.
     *
     * @param sdCardDir Pasta do sdcard
     * @param fileName  Nome do arquivo
     * @return
     */
    private static File createFile(File sdCardDir, String fileName) {
        if (!sdCardDir.exists()) {
            sdCardDir.mkdir(); // Cria o diretório se não existe
        }
        // Retorna o arquivo para ler ou salvar no sd card
        File file = new File(sdCardDir, fileName);
        return file;
    }
}
