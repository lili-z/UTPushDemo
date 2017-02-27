package com.example.commons.pushlibrary.service;

/**
 * Created by LiLi on 2017/2/8.
 * Func:
 * Desc:
 */

public interface IBaseCallback {

    void onSuccess(String msg);

    void onFailed(String code, String msg);

}
