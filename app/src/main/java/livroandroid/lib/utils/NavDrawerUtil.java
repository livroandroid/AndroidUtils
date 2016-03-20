//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package livroandroid.lib.utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.livroandroid.androidutils.R.id;

public class NavDrawerUtil {
    public NavDrawerUtil() {
    }

    public static void setHeaderValues(View navDrawerView, int listViewContainerId, int imgNavDrawerHeaderId, int imgUserUserPhotoId, int stringNavUserName, int stringNavUserEmail) {
        View view = navDrawerView.findViewById(listViewContainerId);
        if(view != null) {
            view.setVisibility(0);
            ImageView imgUserBackground = (ImageView)view.findViewById(id.imgUserBackground);
            if(imgUserBackground != null) {
                imgUserBackground.setImageResource(imgNavDrawerHeaderId);
                TextView tUserName = (TextView)view.findViewById(id.tUserName);
                TextView tUserEmail = (TextView)view.findViewById(id.tUserEmail);
                ImageView imgUserPhoto = (ImageView)view.findViewById(id.imgUserPhoto);
                if(imgUserPhoto != null) {
                    imgUserPhoto.setImageResource(imgUserUserPhotoId);
                }

                if(tUserName != null && tUserEmail != null) {
                    tUserName.setText(stringNavUserName);
                    tUserEmail.setText(stringNavUserEmail);
                }

            }
        }
    }
}
