package com.souhou.watersystem.ui;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by Administrator on 2017/7/16.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化二维码工具类
        ZXingLibrary.initDisplayOpinion(this);
    }
}
