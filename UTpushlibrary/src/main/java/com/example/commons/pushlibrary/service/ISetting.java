package com.example.commons.pushlibrary.service;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by LiLi on 2017/2/8.
 * Func:
 * Desc:
 */

public interface ISetting {
    void register(Context var1, ICallback var2);

    void register(Context var1, String var2, String var3, ICallback var4);

    void onAppStart();

    void bindAccount(String var1, ICallback var2);

    void unbindAccount(IBaseCallback var1);

    void bindTag(int var1, String[] var2, String var3, ICallback var4);

    void unbindTag(int var1, String[] var2, String var3, ICallback var4);

    void listTags(int var1, ICallback var2);

    void addAlias(String var1, ICallback var2);

    void removeAlias(String var1, ICallback var2);

    void listAliases(ICallback var1);

    String getDeviceId();

    String getUTDeviceId();

    void setLogLevel(int var1);

    void setDoNotDisturb(int var1, int var2, int var3, int var4, ICallback var5);

    void closeDoNotDisturbMode();

    void setNotificationSoundFilePath(String var1);

    void setNotificationLargeIcon(Bitmap var1);

    void setNotificationSmallIcon(int var1);

    void clearTips();

    void setRemindType(int type);
}
