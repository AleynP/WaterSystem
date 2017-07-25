package com.souhou.watersystem.ui.activity.FaultActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.BXRepairBean;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.ui.MyApplication;
import com.souhou.watersystem.ui.adapter.BXRecordAdapter;
import com.souhou.watersystem.utils.JsonMananger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class FaultRcodHandelActivity extends AppCompatActivity {
    @BindView(R.id.list_record)
    ListView listRecord;
    private List<BXRepairBean.RecordBean> mList = new ArrayList<>();
    private BXRepairBean bxRepairBean;
    private BXRecordAdapter adapter;
    MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fault_rcod_handel);
        ButterKnife.bind(this);
        request();
        adapter = new BXRecordAdapter(mList, this);
        listRecord.setAdapter(adapter);
    }

    private void request() {
        app = (MyApplication) getApplication();
        OkHttpUtils
                .get()
                .url(ServerConfig.BX_RECORD_URL)
                .addParams("loginName", app.getUsername())
                .addParams("state", "1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        bxRepairBean = JsonMananger.jsonToBean(response, BXRepairBean.class);
                        mList.addAll(bxRepairBean.getRecord());
                    }
                });
    }
}
