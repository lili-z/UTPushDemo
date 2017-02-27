package com.example.pushlibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.alibaba.sdk.android.push.notification.BasicCustomPushNotification;
import com.alibaba.sdk.android.push.notification.CustomNotificationBuilder;
import com.example.pushlibrary.service.IBaseCallback;
import com.example.pushlibrary.service.ICallback;
import com.example.pushlibrary.service.ISetting;

/**
 * Created by LiLi on 2017/2/8.
 * Func:
 * Desc:
 */

public class UTSetting implements ISetting {
    public static final String SETTINGS_ACT = "settings-act";

    private static UTSetting utSetting = new UTSetting();

    public static UTSetting getUtSetting() {
        return utSetting;
    }

    @Override
    public void register(Context var1, ICallback var2) {

    }

    @Override
    public void register(Context var1, String var2, String var3, ICallback var4) {

    }

    @Override
    public void onAppStart() {

    }

    @Override
    public void bindAccount(final String acountStr, final ICallback var2) {
        if (!TextUtils.isEmpty(acountStr)) {
            PushServiceFactory.getCloudPushService().bindAccount(acountStr, new CommonCallback() {
                @Override
                public void onSuccess(String response) {
                    // 本地存储绑定的用户名
                    Log.i(SETTINGS_ACT, "@用户绑定账号 ：" + acountStr + " 成功");
                    var2.onSuccess(response);
                }

                @Override
                public void onFailed(String errorCode, String errorMessage) {
                    Log.i(SETTINGS_ACT, "@用户绑定账号 ：" + acountStr + " 失败，原因 ： " + errorMessage);
                    var2.onFailed(errorCode, errorMessage);
                }
            });
        } else {
            var2.onError("请传入正确账号");
        }
    }

    @Override
    public void unbindAccount(final IBaseCallback var1) {
        PushServiceFactory.getCloudPushService().unbindAccount(
                new CommonCallback() {
                    @Override
                    public void onSuccess(String response) {
                        Log.i(SETTINGS_ACT, "@用户解绑账户 ：" + "成功");
                        // 删除本地存储的用户名
                        var1.onSuccess(response);
                    }

                    @Override
                    public void onFailed(String errorCode, String errorMessage) {
                        Log.i(SETTINGS_ACT, "@用户解绑账户 ：" + " 失败，原因 ：" + errorMessage);
                        var1.onFailed(errorCode, errorMessage);
                    }
                }
        );

    }

    /**
     * @param var1 1、绑定标签到设备 2、绑定标签到账号 3、绑定标签到别名
     * @param var2 标签集合
     * @param var3 别名，仅当var1等于3时有效，可以为null
     * @param var4
     */
    @Override
    public void bindTag(int var1, String[] var2, String var3, final ICallback var4) {
        PushServiceFactory.getCloudPushService().bindTag(var1, var2, var3, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Log.i(SETTINGS_ACT, "绑定标签到设备成功.");
                var4.onSuccess(response);
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.e(SETTINGS_ACT, "绑定标签到设备失败，errorCode: " + errorCode + ", errorMessage：" + errorMessage);
                var4.onFailed(errorCode, errorMessage);
            }
        });
    }

    /**
     * @param var1 1、绑定标签到设备 2、绑定标签到账号 3、绑定标签到别名
     * @param var2 标签集合
     * @param var3 别名，仅当var1等于3时有效，可以为null
     * @param var4
     */

    @Override
    public void unbindTag(int var1, String[] var2, String var3, final ICallback var4) {
        PushServiceFactory.getCloudPushService().unbindTag(2, var2, var3, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Log.i(SETTINGS_ACT, "解绑账号标签成功.");
                var4.onSuccess(response);
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.e(SETTINGS_ACT, "解绑账号标签失败，errorCode: " + errorCode + ", errorMessage：" + errorMessage);
                var4.onFailed(errorCode, errorMessage);
            }
        });
    }

    /**
     * 查询目标绑定的标签
     *
     * @param var1 目标类型，1: 本设备
     * @param var2
     */
    @Override
    public void listTags(int var1, final ICallback var2) {
        PushServiceFactory.getCloudPushService().listTags(1, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Log.i(SETTINGS_ACT, "解绑别名标签成功，标签：" + response);
                var2.onSuccess(response);
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                var2.onFailed(errorCode, errorMessage);
                Log.e(SETTINGS_ACT, "查询设备标签失败，errorCode: " + errorCode + ", errorMessage：" + errorMessage);
            }
        });
    }

    @Override
    public void addAlias(String aliasStr, final ICallback var2) {
        if (!TextUtils.isEmpty(aliasStr)) {
            PushServiceFactory.getCloudPushService().addAlias(aliasStr, new CommonCallback() {
                @Override
                public void onSuccess(String response) {
                    Log.i(SETTINGS_ACT, "添加别名成功.");
                    var2.onSuccess(response);
                }

                @Override
                public void onFailed(String errorCode, String errorMessage) {
                    Log.e(SETTINGS_ACT, "添加别名失败，errorCode: " + errorCode + ", errorMessage：" + errorMessage);
                    var2.onFailed(errorCode, errorMessage);
                }
            });
        } else {
            var2.onError("别名不能为空");
        }
    }

    @Override
    public void removeAlias(String aliasStr, final ICallback var2) {
        if (!TextUtils.isEmpty(aliasStr)) {
            PushServiceFactory.getCloudPushService().removeAlias(aliasStr, new CommonCallback() {
                @Override
                public void onSuccess(String response) {
                    Log.i(SETTINGS_ACT, "删除别名成功.");
                    var2.onSuccess(response);
                }

                @Override
                public void onFailed(String errorCode, String errorMessage) {
                    Log.e(SETTINGS_ACT, "删除别名失败，errorCode: " + errorCode + ", errorMessage：" + errorMessage);
                    var2.onFailed(errorCode, errorMessage);
                }
            });
        } else {
            var2.onError("别名不能为空");
        }

    }

    @Override
    public void listAliases(final ICallback var1) {
        PushServiceFactory.getCloudPushService().listAliases(new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Log.i(SETTINGS_ACT, "解绑别名标签成功，标签：" + response);
                var1.onSuccess(response);
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.e(SETTINGS_ACT, "查询设备别名失败，errorCode: " + errorCode + ", errorMessage：" + errorMessage);
                var1.onFailed(errorCode, errorMessage);
            }
        });
    }

    @Override
    public String getDeviceId() {
        if (PushServiceFactory.getCloudPushService() != null)
            return PushServiceFactory.getCloudPushService().getDeviceId();
        return null;
    }

    @Override
    public String getUTDeviceId() {
        if (PushServiceFactory.getCloudPushService() != null)
            return PushServiceFactory.getCloudPushService().getUTDeviceId();
        return null;
    }

    @Override
    public void setLogLevel(int var1) {
        PushServiceFactory.getCloudPushService().setLogLevel(var1);
    }

    /**
     * @param var1 免打扰的起始时间（小时），24小时制，取值范围：0-23
     * @param var2 免打扰起始时间（分钟），取值范围：0-59
     * @param var3 免打扰的结束时间（小时），24小时制，取值范围：0-23
     * @param var4 免打扰结束时间（分钟），取值范围：0-59
     * @param var5
     */
    @Override
    public void setDoNotDisturb(int var1, int var2, int var3, final int var4, final ICallback var5) {
        PushServiceFactory.getCloudPushService().setDoNotDisturb(var1, var2, var3, var4, new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                var5.onSuccess(s);
            }

            @Override
            public void onFailed(String s, String s1) {
                var5.onFailed(s, s1);
            }
        });
    }

    @Override
    public void closeDoNotDisturbMode() {
        PushServiceFactory.getCloudPushService().closeDoNotDisturbMode();
    }

    /**
     * 设置声音文件，若不调用则获取默认文件
     *
     * @param var1 声音文件路径
     */
    @Override
    public void setNotificationSoundFilePath(String var1) {
        PushServiceFactory.getCloudPushService().setNotificationSoundFilePath(var1);
    }

    @Override
    public void setNotificationLargeIcon(Bitmap var1) {
        PushServiceFactory.getCloudPushService().setNotificationLargeIcon(var1);
    }

    @Override
    public void setNotificationSmallIcon(int var1) {
        PushServiceFactory.getCloudPushService().setNotificationSmallIcon(var1);
    }

    @Override
    public void clearTips() {

    }

    /**
     * 设置提示方式
     * @param type 0、无声 1、震动 2、铃声
     */
    @Override
    public void setRemindType(int type) {
        BasicCustomPushNotification notification = new BasicCustomPushNotification();
        notification.setRemindType(type);
//        notification.setStatusBarDrawable(R.drawable.logo_yuanjiao_120);//设置状态栏图标
        boolean res = CustomNotificationBuilder.getInstance().setCustomNotification(1, notification);//注册该通知,并设置ID为1
    }


}
