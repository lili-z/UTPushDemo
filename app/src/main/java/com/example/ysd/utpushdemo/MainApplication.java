package com.example.ysd.utpushdemo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.example.commons.pushlibrary.UTPushUtil;
import com.example.commons.pushlibrary.service.IBaseCallback;

/**
 * Created by LiLi on 2017/2/7.
 * Func:
 * Desc:
 */

public class MainApplication extends Application{

    public   String TAG=this.getClass().getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
//        initCloudChannel(this);
        //初始化云推送通道
        UTPushUtil.init(this, new IBaseCallback() {
            @Override
            public void onSuccess(String msg) {
                Log.i(TAG+"==", "init cloudchannel success");

            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.i(TAG+"==", "init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);

            }
        });
    }

    private void initCloudChannel(Context applicationContext) {

        PushServiceFactory.init(applicationContext);
        final CloudPushService pushService = PushServiceFactory.getCloudPushService();
        pushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Log.i(TAG+"==", "init cloudchannel success");
                String deviceId = pushService.getDeviceId();
                Log.i(TAG, "deviceid==" + deviceId);
            }
            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.i(TAG+"==", "init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
            }
        });
    }
}
