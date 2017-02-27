package com.example.commons.pushlibrary.service;

import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.push.MessageReceiver;
import com.alibaba.sdk.android.push.notification.CPushMessage;

import java.util.Map;

/**
 * Created by LiLi on 2017/2/8.
 * Func:
 * Desc:
 */

public class CustomMsgReceiver extends MessageReceiver{
    /**
     * 通知回调
     * @param context
     * @param title
     * @param summary
     * @param map
     */
    @Override
    protected void onNotification(Context context, String title, String summary, Map<String, String> map) {
        super.onNotification(context, title, summary, map);
        Log.d(TAG, "title" + title + "\nsummary" + summary + "\nmap" + map.toString());

    }

    /**
     * 消息回调
     * @param context
     * @param cPushMessage 可以获取消息Id、消息标题和内容
     */
    @Override
    protected void onMessage(Context context, CPushMessage cPushMessage) {
        super.onMessage(context, cPushMessage);
        Log.d("customreceive", "\nmsg" + cPushMessage.getTitle());
        Log.e("customreceive", "\nmsg" + cPushMessage.getTitle());
        Log.i("customreceive", "\nmsg" + cPushMessage.getTitle());

        BeepManager.getInstance(context).playBeepSoundAndVibrate();
    }

    /**
     * 通知打开回调
     * @param context
     * @param s
     * @param s1
     * @param s2
     */
    @Override
    protected void onNotificationOpened(Context context, String s, String s1, String s2) {
        super.onNotificationOpened(context, s, s1, s2);
    }

    /**
     * 通知删除回调
     * @param context
     * @param s
     */
    @Override
    protected void onNotificationRemoved(Context context, String s) {
        super.onNotificationRemoved(context, s);
    }

    /**
     * 通知到达回调
     * @param context
     * @param s
     * @param s1
     * @param map
     * @param i
     * @param s2
     * @param s3
     */
    @Override
    protected void onNotificationReceivedInApp(Context context, String s, String s1, Map<String, String> map, int i, String s2, String s3) {
        super.onNotificationReceivedInApp(context, s, s1, map, i, s2, s3);
    }
}
