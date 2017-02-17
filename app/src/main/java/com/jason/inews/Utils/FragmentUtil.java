package com.jason.inews.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.jason.inews.R;

/**
 * Created by distancelin on 2017/2/16.
 */

public class FragmentUtil {
    public static void addFragment(FragmentManager fragmentManager, Fragment fragment) {
        fragmentManager.beginTransaction().add(R.id.container, fragment).commit();
    }

    public static void switchFragment(FragmentManager manager, Fragment to) {
        manager.beginTransaction().replace(R.id.container, to).commit();
    }
}
