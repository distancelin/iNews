package com.jason.inews.Utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by distancelin on 2017/2/16.
 */

public class ImageLoaderUtil {
    public static void loadImage(Fragment fragment, String url, ImageView to) {
        Glide.with(fragment).load(url).into(to);
    }

    public static void loadImage(Context context, String url, ImageView to) {
        Glide.with(context).load(url).into(to);
    }
}
