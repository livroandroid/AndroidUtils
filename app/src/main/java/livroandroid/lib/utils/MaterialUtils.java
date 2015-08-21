package livroandroid.lib.utils;

import android.util.TypedValue;
import android.widget.FrameLayout;

/**
 * Created by ricardo on 01/01/15.
 */
public class MaterialUtils {

    public static void setRippleBackgroundEffect(FrameLayout view) {
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int[] attrs = new int[] { android.R.attr.selectableItemBackground};
            TypedArray ta = view.getContext().obtainStyledAttributes(attrs);
            Drawable drawable = ta.getDrawable(0);
            ta.recycle();
            view.setForeground(drawable);
        }*/

        TypedValue outValue = new TypedValue();
        view.getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground,
                outValue, true);
        view.setBackgroundResource(outValue.resourceId);
    }
}
