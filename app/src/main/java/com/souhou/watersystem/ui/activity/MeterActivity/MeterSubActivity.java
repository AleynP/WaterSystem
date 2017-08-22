package com.souhou.watersystem.ui.activity.MeterActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.common.BaseBackActivity;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.ui.MyApplication;
import com.souhou.watersystem.utils.ClearEditText;
import com.souhou.watersystem.utils.SnackBar;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class MeterSubActivity extends BaseBackActivity {

    @BindView(R.id.tv_loginName)
    TextView tvLoginName;
    @BindView(R.id.tv_water_sort)
    TextView tvWaterSort;
    @BindView(R.id.tv_water_num)
    TextView tvWaterNum;
    @BindView(R.id.tv_ladder_price)
    TextView tvLadderPrice;
    @BindView(R.id.tv_water_sc_num)
    TextView tvWaterScNum;
    @BindView(R.id.ed_water_bc_num)
    ClearEditText edWaterBcNum;
    @BindView(R.id.bu_sub)
    Button buSub;
    @BindView(R.id.bt_not)
    Button btNot;
    MyApplication app;
    private String ladder, water_BianHao, water_LiuLiang, sort, BC_LiuLiang;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meter_sub);
        ButterKnife.bind(this);
        setTitle("抄表提交页面");
        inView();
    }

    private void inView() {
        app = (MyApplication) getApplication();
        intent = getIntent();
        water_LiuLiang = intent.getStringExtra("water_number");
        water_BianHao = intent.getStringExtra("water_num");
        ladder = intent.getStringExtra("UserLadder");
        sort = intent.getStringExtra("WaterSortID");

        tvLoginName.setText(app.getUsername());
        tvWaterNum.setText(water_BianHao);
        tvLadderPrice.setText(ladder);
        tvWaterSort.setText(sort);
        tvWaterScNum.setText(water_LiuLiang);
    }


    private void request(String bc) {
        OkHttpUtils
                .get()
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
                        SnackBar.make(tvWaterNum, "请求失败" + e.getMessage().toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        SnackBar.make(tvWaterNum, "提交成功");
                    }
                });
    }

    @OnClick({R.id.bu_sub, R.id.bt_not})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bu_sub:
                BC_LiuLiang = edWaterBcNum.getText().toString();
                if (!BC_LiuLiang.equals("")) {
                    ADialog(BC_LiuLiang);
                } else {
                    edWaterBcNum.setShakeAnimation();
                    SnackBar.make(tvWaterNum, "水表数不能为空");
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
        builder.setTitle("请确认本次水表号");
        builder.setMessage(bc);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                request(BC_LiuLiang);
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
}
