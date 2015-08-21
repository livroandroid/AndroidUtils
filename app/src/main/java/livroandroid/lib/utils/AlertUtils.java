package livroandroid.lib.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class AlertUtils {

    public static void alert(Activity activity, String title, String message) {
        alert(activity, title, message, 0, 0);
    }

    public static void alert(Activity activity, String message) {
        alert(activity, null, message, 0, 0);
    }

    public static void alert(Activity activity, String title, String message, int okButton, int icon) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (icon > 0) {
            builder.setIcon(icon);
        }
        if (title != null) {
            builder.setTitle(title);
        }
        builder.setMessage(message);

        String okString = okButton > 0 ? activity.getString(okButton) : "OK";

        AlertDialog dialog = builder.create();
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, okString, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        dialog.show();
    }
}
