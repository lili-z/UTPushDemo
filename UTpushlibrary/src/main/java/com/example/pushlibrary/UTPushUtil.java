package com.example.pushlibrary;

import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.alibaba.sdk.android.push.notification.CustomNotificationBuilder;
import com.example.pushlibrary.service.IBaseCallback;

/**
 * Created by LiLi on 2017/2/8.
 * Func:
 * Desc:
 */

public class UTPushUtil {
    final String TAG = getClass().getSimpleName();

    public static UTSetting getUtSetting() {
        return UTSetting.getUtSetting();
    }
    CustomNotificationBuilder customNotificationBuilder;

    public static void init(Context applicationContext, final IBaseCallback callback) {
        PushServiceFactory.init(applicationContext);
        final CloudPushService pushService = PushServiceFactory.getCloudPushService();
        pushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Log.i("UTPushUtil==", "init cloudchannel success");
                String deviceId = pushService.getDeviceId();
                Log.i("deviceid", "deviceid==" + deviceId);
                callback.onSuccess(response);
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.i("UTPushUtil==", "init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
                callback.onFailed(errorCode, errorMessage);
            }
        });
    }

}
