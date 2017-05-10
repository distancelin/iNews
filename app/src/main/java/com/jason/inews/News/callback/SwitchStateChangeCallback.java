package com.jason.inews.News.callback;

/**
 * Created by distancelin on 2017/4/18.
 */

public interface SwitchStateChangeCallback {

    /**
     * 开关选中时回调
     */
    void onSwitchChecked(String newsTittle);

    /**
     * 开关关闭时回调
     */
    void onSwitchDischecked(String newsTittle);
}
