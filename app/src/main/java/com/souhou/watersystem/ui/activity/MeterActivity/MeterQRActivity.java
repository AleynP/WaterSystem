package com.souhou.watersystem.ui.activity.MeterActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.CBWaterMeterBean;
import com.souhou.watersystem.common.BaseBackActivity;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.utils.ClearEditText;
import com.souhou.watersystem.utils.JsonMananger;
import com.souhou.watersystem.utils.SnackBar;
import com.souhou.watersystem.utils.Toasts;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class MeterQRActivity extends BaseBackActivity {
    public static final int REQUEST_CODE = 1;

    private CBWaterMeterBean cbWaterMeterBean;
    private String Water_LiuLiang, water_BianHao;
    @BindView(R.id.ed_input)
    ClearEditText ed_input;
    @BindView(R.id.bt_qr_input)
    ImageButton btQrInput;
    @BindView(R.id.bt_ok)
    Button btOk;
    @BindView(R.id.bt_no)
    Button btNo;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meter_qr);
        ButterKnife.bind(this);
        setTitle("智能抄表");

    }

    @OnClick({R.id.bt_qr_input, R.id.bt_ok, R.id.bt_no})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_qr_input:
                intent = new Intent(this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.bt_ok:
                water_BianHao = ed_input.getText().toString();
                if (!water_BianHao.equals("")) {
                    request(water_BianHao);
                } else {
                    ed_input.setShakeAnimation();
                    SnackBar.make(ed_input, "水表不能为空");
                }
                break;
            case R.id.bt_no:
                finish();
                break;
        }
    }

    private void request(String bianhao) {
        OkHttpUtils
                .get()
                .url(ServerConfig.CB_SC_WATERREC_URL)
                .addParams("WaterMeterNumber", bianhao)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toasts.setText(MeterQRActivity.this, "请求失败");
//                        SnackBar.make(btOk, "请求失败" + e.getMessage().toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        cbWaterMeterBean = JsonMananger.jsonToBean(response, CBWaterMeterBean.class);
                        int size = cbWaterMeterBean.getShangCiChaoBiaoJiLu().size();
                        if (size != 0) {
                            Water_LiuLiang = cbWaterMeterBean.getShangCiChaoBiaoJiLu().get(id).getMeterReading_Number();
                            String Water_Ladder = cbWaterMeterBean.getShangCiChaoBiaoJiLu().get(id).getUser_Ladder() + "";
                            String Water_sort = cbWaterMeterBean.getShangCiChaoBiaoJiLu().get(id).getWaterSort_Name();
                            intent = new Intent(MeterQRActivity.this, MeterSubActivity.class);
                            intent.putExtra("water_number", Water_LiuLiang);
                            intent.putExtra("water_num", water_BianHao);
                            intent.putExtra("UserLadder", Water_Ladder);
                            intent.putExtra("WaterSortID", Water_sort);
                            startActivity(intent);
                        } else {
                            SnackBar.make(btOk, "没有该水表");
                        }
                    }
                });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    request(result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    SnackBar.make(ed_input, "解析二维码失败");
                }
            }
        }
    }
}
