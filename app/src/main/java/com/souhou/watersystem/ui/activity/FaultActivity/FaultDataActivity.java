package com.souhou.watersystem.ui.activity.FaultActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.BXdateBean;
import com.souhou.watersystem.common.BaseBackActivity;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.utils.JsonMananger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class FaultDataActivity extends BaseBackActivity {

    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_shuoming)
    TextView tvShuoming;
    @BindView(R.id.bt_yes)
    Button btYes;
    @BindView(R.id.bt_not)
    Button btNot;
    @BindView(R.id.fault_gridView)
    GridView gridView;
    private BXdateBean bXdateBean;
    private String repairsID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fault_data);
        ButterKnife.bind(this);
        setTitle("详细信息");
        Intent intent = getIntent();
        repairsID = intent.getStringExtra("id");
        respones(repairsID);
    }

    private void initViews(BXdateBean bXdateBean) {
        tvUserName.setText(bXdateBean.getRepairs_User() + "");
        tvTime.setText(bXdateBean.getRepairs_Time() + "");
        tvPhone.setText(bXdateBean.getRepairs_Phone() + "");
        tvAddress.setText(bXdateBean.getRepairs_Site());
        tvShuoming.setText(bXdateBean.getRepairs_Content());

    }

    private void respones(String repairsID) {
        OkHttpUtils
                .get()
                .url(ServerConfig.BX_MES_DATA_URL)
                .addParams("repairsID", repairsID)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        bXdateBean = JsonMananger.jsonToBean(response, BXdateBean.class);
                        initViews(bXdateBean);
                    }
                });
    }


    @OnClick({R.id.bt_yes, R.id.bt_not})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_yes:
                Response(repairsID, "1");
                break;
            case R.id.bt_not:
                Response(repairsID, "2");
                break;
        }
    }

    private void Response(String repairsID, String type) {
        OkHttpUtils
                .get()
                .url(ServerConfig.BX_MES_DATA_Choice_URL)
                .addParams("repairsID", repairsID)
                .addParams("state", type)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Snackbar.make(tvAddress, "接单成功", Snackbar.LENGTH_SHORT).show();
                        btYes.setVisibility(View.INVISIBLE);
                        btNot.setVisibility(View.INVISIBLE);
                    }
                });
    }
}
