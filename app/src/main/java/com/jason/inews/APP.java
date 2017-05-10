package com.jason.inews;

import android.app.Application;
import android.content.Context;

import java.util.Arrays;
import java.util.List;

/**
 * Created by distancelin on 2017/4/4.
 */

public class APP extends Application {
    private static Application sApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }

    public static Application getApplication() {
        return sApplication;
    }

    public static List<String> getAllNewsType() {
        return Arrays.asList(getApplication().getResources().getStringArray(R.array.allNewsTypes));
    }
}
