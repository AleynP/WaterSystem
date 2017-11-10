package com.souhou.watersystem.ui.activity.MeterActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.CBWaterMeterBean;
import com.souhou.watersystem.common.BaseBackActivity;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.ui.MyApplication;
import com.souhou.watersystem.utils.ClearEditText;
import com.souhou.watersystem.utils.JsonMananger;
import com.souhou.watersystem.utils.LoadingDialog;
import com.souhou.watersystem.utils.LogUtils;
import com.souhou.watersystem.utils.SnackBar;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class MeterSubActivity extends BaseBackActivity {

    MyApplication app;
    @BindView(R.id.user_Name)
    TextView userName;
    @BindView(R.id.user_number)
    TextView userNumber;
    @BindView(R.id.user_address)
    TextView userAddress;
    @BindView(R.id.water_sort)
    TextView waterSort;
    @BindView(R.id.water_price_type)
    TextView waterPriceType;
    @BindView(R.id.water_sc_num)
    TextView waterScNum;
    @BindView(R.id.water_bc_num)
    ClearEditText waterBcNum;
    private String ladder, water_BianHao, water_LiuLiang, sort, BC_LiuLiang;
    private CBWaterMeterBean cbWaterMeterBean;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meter_sub);
        ButterKnife.bind(this);
        setTitle("抄表提交页面");
        app = (MyApplication) getApplication();
        bundle = getIntent().getExtras();
        water_BianHao = bundle.getString("water_num");
        request(water_BianHao);
    }

    private void inView(CBWaterMeterBean.ShangCiChaoBiaoJiLuBean JiLuBean) {
        ladder = JiLuBean.getUser_Ladder() + "";
        water_LiuLiang = JiLuBean.getMeterReading_Number() + "";
        sort = JiLuBean.getWaterSort_Name();
        BC_LiuLiang = JiLuBean.getWaterSort_Name();

        userName.setText(JiLuBean.getUser_Name());
        userNumber.setText(JiLuBean.getUser_Number() + "");
        waterSort.setText(sort);
        userAddress.setText(JiLuBean.getUser_Site());
        if (ladder.equals("0")) {
            waterPriceType.setText("非阶梯");
        } else {
            waterPriceType.setText("阶梯");
        }
        waterScNum.setText(JiLuBean.getMeterReading_Number() + "");
    }

    private void request(String bianhao) {
        LoadingDialog.createLoadingDialog(this, "正在加载...");
        OkHttpUtils
                .get()
                .url(ServerConfig.CB_SC_WATERREC_URL)
                .addParams("WaterMeterNumber", bianhao)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LoadingDialog.closeDialog();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LoadingDialog.closeDialog();
                        cbWaterMeterBean = JsonMananger.jsonToBean(response, CBWaterMeterBean.class);
                        inView(cbWaterMeterBean.getShangCiChaoBiaoJiLu());
                    }
                });
    }

    private void response(String bc) {
        LoadingDialog.createLoadingDialog(this, "正在提交...");
        OkHttpUtils
                .post()
                .url(ServerConfig.CB_SC_WATER_SUB_URL)
                .addParams("loginName", app.getUsername())
                .addParams("UserLadder", ladder)
                .addParams("WaterMeterNumber", water_BianHao)
                .addParams("WaterSortID", sort)
                .addParams("bcMeterReadingNumber", bc)
                .addParams("scMeterReadingNumber", water_LiuLiang)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LoadingDialog.closeDialog();
                        Toast.makeText(MeterSubActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LoadingDialog.closeDialog();
                        subDialog();
                    }
                });
    }

    @OnClick({R.id.bu_sub, R.id.bt_not})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bu_sub:
                BC_LiuLiang = waterBcNum.getText().toString();
                if (!BC_LiuLiang.equals("")) {
                    ADialog(BC_LiuLiang);
                } else {
                    waterBcNum.setShakeAnimation();
                    SnackBar.make(waterBcNum, "水表数不能为空");
                }
                break;
            case R.id.bt_not:
                finish();
                break;
        }
    }

    private void ADialog(String bc) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MeterSubActivity.this);
        builder.setIcon(R.drawable.login_logo);
        builder.setTitle("请确认本次水表数");
        builder.setMessage(bc);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                response(BC_LiuLiang);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    private void subDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MeterSubActivity.this);
        builder.setIcon(R.drawable.login_logo);
        builder.setTitle("提交结果");
        builder.setMessage("提交成功");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.show();
    }

}
