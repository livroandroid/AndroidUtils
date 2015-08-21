package livroandroid.lib.activity;

import android.os.Bundle;
import android.util.Log;

/**
 * Created by Ricardo Lecheta on 22/09/2014.
 */
public class DebugActivity extends android.support.v7.app.AppCompatActivity {
    protected static final String TAG = "livroandroid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isLogLifecycle()) {
            log(getClassName() + ".onCreate(): " + savedInstanceState);
        }
    }

    protected void onStart() {
        super.onStart();
        if (isLogLifecycle()) {
            log(getClassName() + ".onStart().");
        }
    }

    protected void onRestart() {
        super.onRestart();
        if (isLogLifecycle()) {
            log(getClassName() + ".onRestart().");
        }
    }

    protected void onResume() {
        super.onResume();
        if (isLogLifecycle()) {
            log(getClassName() + ".onResume().");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (isLogLifecycle()) {
            log(getClassName() + ".onSaveInstanceState().");
        }
    }

    protected void onPause() {
        super.onPause();
        if (isLogLifecycle()) {
            log(getClassName() + ".onPause().");
        }
    }

    protected void onStop() {
        super.onStop();
        if (isLogLifecycle()) {
            log(getClassName() + ".onStop().");
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (isLogLifecycle()) {
            log(getClassName() + ".onDestroy().");
        }
    }

    public String getClassName() {
        // Retorna o nome da classe sem o pacote
        Class cls = ((Object) this).getClass();
        String s = cls.getSimpleName();
        return s;
    }

    protected void log(String msg) {
        if (isLogOn()) {
            Log.d(TAG, msg);
        }
    }

    protected boolean isLogOn() {
        return true;
    }

    protected boolean isLogLifecycle() {
        return false;
    }
}
