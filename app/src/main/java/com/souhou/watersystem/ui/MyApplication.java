package com.souhou.watersystem.ui;

import android.app.Application;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.souhou.watersystem.utils.PicassoImageLoader;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2017/7/16.
 */

public class MyApplication extends Application {
    public String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化二维码工具类
        ZXingLibrary.initDisplayOpinion(this);
        //初始化极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(getApplicationContext());
        //初始化九宫格图片参数
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new PicassoImageLoader());   //设置图片加载器
//        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
//        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }
}
