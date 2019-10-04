package org.lenve.databinding1.data;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.lenve.databinding1.util.UILRequestManager;

/**
 * Created by Administrator on 2017/8/8.
 */

public class User {
    private String username;
    private String photo;

    public User() {
    }

    public User(String photo, String username) {
        this.photo = photo;
        this.username = username;
    }

    @BindingAdapter("photo")
    public static void getInternetImage(ImageView iv, String photo) {
        UILRequestManager.displayImage(photo, iv);
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
