package com.souhou.watersystem.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.ui.MyApplication;
import com.souhou.watersystem.utils.LogUtils;
import com.souhou.watersystem.utils.TimeUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;

public class LocationService extends Service {

    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            request(amapLocation);
        }
    };

    MyApplication app;
    private Timer mTimer;

    private String name, phone;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        app = (MyApplication) getApplication();
        name = app.getUsername();
        phone = app.getPhone();
        mTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                starLoaction();
            }
        };
        mTimer.scheduleAtFixedRate(task, 0, 3 * 1000);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    protected void starLoaction() {
        //初始化定位
        if (null == mLocationClient) {
            mLocationClient = new AMapLocationClient(this.getApplicationContext());
        }
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
//        mLocationOption.setInterval(3000);
        mLocationOption.setMockEnable(true);
        mLocationOption.setHttpTimeOut(15000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
        Log.d("PCL", "启动定位");
    }

    public void request(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //可在其中解析amapLocation获取相应内容。
                Log.d("PCL", "发送请求");
                OkHttpUtils
                        .get()
                        .url(ServerConfig.GPS_URL)
                        .addParams("name", name)
                        .addParams("phone", phone)
                        .addParams("time", TimeUtils.getNowString())
                        .addParams("B_WGS84", amapLocation.getLatitude() + "")
                        .addParams("L_WGS84", amapLocation.getLongitude() + "")
                        .addParams("state", "0")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.i("PCL", "发送失败" + e.getMessage());
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.i("PCL", "发送成功");
                            }
                        });

                Log.i("PCL", amapLocation.getLatitude() + "\n" + amapLocation.getLongitude() +
                        "\n" + amapLocation.getAddress() +
                        "\n" + name +
                        "\n" + phone);
            } else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                LogUtils.e("location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }

}
