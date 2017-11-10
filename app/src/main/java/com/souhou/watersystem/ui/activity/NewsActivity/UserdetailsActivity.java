package com.souhou.watersystem.ui.activity.NewsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.UserInfo;
import com.souhou.watersystem.common.BaseBackActivity;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.ui.MyApplication;
import com.souhou.watersystem.utils.DatetoStringFormat;
import com.souhou.watersystem.utils.JsonMananger;
import com.souhou.watersystem.utils.LoadingDialog;
import com.souhou.watersystem.utils.SnackBar;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class UserdetailsActivity extends BaseBackActivity {

    @BindView(R.id.tv_names)
    TextView tvNames;
    @BindView(R.id.tv_instatime)
    TextView tvInstatime;
    @BindView(R.id.tv_water_num)
    TextView tvWaterNum;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.bt_true)
    Button btTrue;
    @BindView(R.id.bt_false)
    Button btFalse;
    @BindView(R.id.bt_close)
    Button btClose;
    private String id;
    private UserInfo userinfo;
    MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetails);
        ButterKnife.bind(this);
        setTitle("接单详情");
        app = (MyApplication) getApplication();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        response(id);
    }

    private void response(String id) {
        OkHttpUtils
                .get()
                .url(ServerConfig.AZ_MES_DETAILS_URL)
                .addParams("userID", id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        userinfo = JsonMananger.jsonToBean(response, UserInfo.class);
                        if (!userinfo.equals("")){
                            initView();
                        }else {

                        }
                    }
                });
    }

    private void initView() {
        tvNames.setText(userinfo.getInstallation_User());
        tvPhone.setText(userinfo.getInstallation_Userphone() + "");
        tvInstatime.setText(DatetoStringFormat.StringToStrLong(userinfo.getInstallation_Time() + ""));
        tvAddress.setText(userinfo.getInstallation_Address());
        tvWaterNum.setText(userinfo.getWaterType_Name());
    }

    @OnClick({R.id.bt_true, R.id.bt_false, R.id.bt_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_true:
                OkHttpUtils
                        .get()
                        .url(ServerConfig.WORK_ORDERS_URL)
                        .addParams("state", "1")
                        .addParams("installationID", id)
                        .addParams("loginName", app.getUsername())
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                btTrue.setVisibility(View.GONE);
                                btFalse.setVisibility(View.GONE);
                                btClose.setVisibility(View.VISIBLE);
                                SnackBar.make(btTrue, "服务器错误");
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                JSONObject json = JSONObject.parseObject(response);
                                SnackBar.make(btTrue, json.getString("msg").toString());
                                btTrue.setVisibility(View.GONE);
                                btFalse.setVisibility(View.GONE);
                                btClose.setVisibility(View.VISIBLE);
                            }
                        });
                break;
            case R.id.bt_false:
                OkHttpUtils
                        .get()
                        .addParams("state", "2")
                        .addParams("installationID", id)
                        .addParams("loginName", app.getUsername())
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SnackBar.make(btTrue, "操作失败" + e.getMessage().toString());
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                finish();
                            }
                        });
                break;
            case R.id.bt_close:
                finish();
                break;
        }
    }
}
