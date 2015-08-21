package livroandroid.lib.utils;

import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Ricardo Lecheta on 08/02/2015.
 */
public class DownloadUtil {
    private static final String TAG = "Download";

    public interface Callback {
        public void onDownlodCompleted(String url, File file);
    }

    /**
     * Faz o download da URL e salva em arquivo
     */
    public static void saveBitmapToFile(Bitmap bitmap, String url, Callback callback) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new RuntimeException("O método saveBitmapToFile não pode ser executado na UI Thread.");
        }
        try {
            if (url == null || bitmap == null) {
                return;
            }

            String fileName = url.substring(url.lastIndexOf("/"));

            File file = SDCardUtils.getPublicFile(fileName, Environment.DIRECTORY_PICTURES);
            if (!file.exists()) {
                FileOutputStream out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.close();

                Log.d(TAG, "Save File: " + file);

                // Salva o arquivo
                IOUtils.writeBitmap(file, bitmap);

                if (callback != null) {
                    callback.onDownlodCompleted(url, file);
                }
            }

        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
}
