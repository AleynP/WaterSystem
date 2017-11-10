package com.souhou.watersystem.ui.activity.FaultActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.BxHandelDestilBean;
import com.souhou.watersystem.common.BaseBackActivity;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.ui.MyApplication;
import com.souhou.watersystem.ui.adapter.BXPicAdapter;
import com.souhou.watersystem.utils.DatetoStringFormat;
import com.souhou.watersystem.utils.JsonMananger;
import com.souhou.watersystem.utils.LoadingDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class FaultHandDetailsActivity extends BaseBackActivity {


    public BxHandelDestilBean DestilBean;
    @BindView(R.id.handel_user_name)
    TextView handelUserName;
    @BindView(R.id.fault_name)
    TextView faultName;
    @BindView(R.id.fault_count)
    TextView faultCount;
    @BindView(R.id.fault_time)
    TextView faultTime;
    @BindView(R.id.detil_gridView)
    GridView detilGridView;
    @BindView(R.id.Liner_Not_Det)
    LinearLayout LinerNotDet;
    @BindView(R.id.zanWu)
    TextView zanWu;
    private BXPicAdapter picAdapter;
    private List<String> mList = new ArrayList<>();
    MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fault_hand_details);
        ButterKnife.bind(this);
        setTitle("已处理详情");
        Bundle bundle = getIntent().getExtras();
        request(bundle.getString("id"));
    }

    public void request(String id) {
        LoadingDialog.createLoadingDialog(this, "正在加载...");
        OkHttpUtils
                .get()
                .url(ServerConfig.BX_HANDEL_DITEAL)
                .addParams("processID", id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        zanWu.setVisibility(View.VISIBLE);
                        LoadingDialog.closeDialog();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LoadingDialog.closeDialog();
                        DestilBean = JsonMananger.jsonToBean(response, BxHandelDestilBean.class);
                        mList.add(DestilBean.getFILE_PATH0());
                        mList.add(DestilBean.getFILE_PATH1());
                        mList.add(DestilBean.getFILE_PATH2());
                        mList.add(DestilBean.getFILE_PATH3());
                        if (!DestilBean.getProcess_Name().equals("")) {
                            LinerNotDet.setVisibility(View.VISIBLE);
                            initDate();
                        } else {
                            zanWu.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    private void initDate() {
        app = (MyApplication) getApplication();
        handelUserName.setText(DestilBean.getRepairs_User());
        faultName.setText(app.getUsername());
        faultCount.setText(DestilBean.getRepairs_Content());
        faultTime.setText(DatetoStringFormat.StringToStrLong(DestilBean.getProcess_Time() + ""));
        picAdapter = new BXPicAdapter(mList, this);
        detilGridView.setAdapter(picAdapter);
    }
}
