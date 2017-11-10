package com.souhou.watersystem.ui.activity.FaultActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.BXdateBean;
import com.souhou.watersystem.common.BaseBackActivity;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.ui.MyApplication;
import com.souhou.watersystem.ui.adapter.BXPicAdapter;
import com.souhou.watersystem.utils.DatetoStringFormat;
import com.souhou.watersystem.utils.JsonMananger;
import com.souhou.watersystem.utils.LoadingDialog;
import com.souhou.watersystem.utils.LogUtils;
import com.souhou.watersystem.utils.SnackBar;
import com.souhou.watersystem.utils.Toasts;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.tv_complete)
    TextView tvComplete;
    @BindView(R.id.Lin_main)
    LinearLayout LinMain;

    private BXdateBean bXdateBean;
    private List<String> mList = new ArrayList<>();
    private String repairsID;
    private BXPicAdapter adapter;
    MyApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fault_data);
        ButterKnife.bind(this);
        setTitle("详细信息");
        LinMain.setVisibility(View.INVISIBLE);
        tvComplete.setVisibility(View.INVISIBLE);
        Intent intent = getIntent();
        repairsID = intent.getStringExtra("id");
        respones(repairsID);
    }

    private void initViews(BXdateBean bXdateBean) {
        tvUserName.setText(bXdateBean.getRepairs_User() + "");
        tvTime.setText(DatetoStringFormat.StringToStrLong(bXdateBean.getRepairs_Time() + ""));
        tvPhone.setText(bXdateBean.getRepairs_Phone() + "");
        tvAddress.setText(bXdateBean.getUser_Site());
        tvShuoming.setText(bXdateBean.getRepairs_Content());
        adapter = new BXPicAdapter(mList, this);
        gridView.setAdapter(adapter);
    }

    private void respones(String repairsID) {
        LoadingDialog.createLoadingDialog(this, getString(R.string.Loading));
        OkHttpUtils
                .get()
                .url(ServerConfig.BX_MES_DATA_URL)
                .addParams("repairsID", repairsID)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LoadingDialog.closeDialog();
                        Toasts.setText(FaultDataActivity.this, "请求错误" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        bXdateBean = JsonMananger.jsonToBean(response, BXdateBean.class);
                        mList.add(bXdateBean.getFILE_PATH0());
                        mList.add(bXdateBean.getFILE_PATH1());
                        mList.add(bXdateBean.getFILE_PATH2());
                        mList.add(bXdateBean.getFILE_PATH3());
                        LinMain.setVisibility(View.VISIBLE);
                        initViews(bXdateBean);
                        LoadingDialog.closeDialog();
                    }
                });
    }


    @OnClick({R.id.bt_yes, R.id.bt_not, R.id.tv_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_yes:
                Response(repairsID, "1");
                break;
            case R.id.bt_not:
                ADialog(repairsID);
                break;
            case R.id.tv_complete:
                finish();
                break;
        }
    }

    private void Response(final String repairsID, final String type) {
        application = (MyApplication) getApplication();
        LoadingDialog.createLoadingDialog(this, "正在提交");
        OkHttpUtils
                .get()
                .url(ServerConfig.BX_MES_DATA_Choice_URL)
                .addParams("repairsID", repairsID)
                .addParams("loginName", application.getUsername())
                .addParams("state", type)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LoadingDialog.closeDialog();
                        SnackBar.make(tvAddress, "请求失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (type.equals("1")) {
                            LoadingDialog.closeDialog();
                            SnackBar.make(tvAddress, "接单成功");
                            tvComplete.setVisibility(View.VISIBLE);
                            btYes.setVisibility(View.INVISIBLE);
                            btNot.setVisibility(View.INVISIBLE);
                        } else if (type.equals("2")) {
                            finish();
                        }
                    }
                });
    }

    public void ADialog(final String repairsID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(FaultDataActivity.this);
        builder.setMessage("您确定要拒绝吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Response(repairsID, "2");
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

}
