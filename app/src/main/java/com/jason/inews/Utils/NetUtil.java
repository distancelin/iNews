package com.jason.inews.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.jason.inews.APP;

/**
 * Created by distancelin on 2017/4/4.
 */
/*判断网络状态是否可用*/
public class NetUtil {
    public static boolean isNetworkAvaliable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) APP.getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
//        NetworkInfo info2 = connectivityManager.getActiveNetworkInfo();
//        boolean flag=info.isAvailable();
//        boolean flag2=info.isConnected();
        return false;
    }
}
