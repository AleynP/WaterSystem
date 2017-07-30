package com.souhou.watersystem.ui.activity.MeterActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.CBRecordBean;
import com.souhou.watersystem.common.BaseBackActivity;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.ui.MyApplication;
import com.souhou.watersystem.ui.adapter.CBRecordAdapter;
import com.souhou.watersystem.utils.JsonMananger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class MeterRecordctivity extends BaseBackActivity {
    @BindView(R.id.list_item)
    ListView listItem;
    private List<CBRecordBean.RecordBean> mList = new ArrayList<>();
    private CBRecordBean cbRecordBean;
    private CBRecordAdapter adapter;
    MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meter_recordctivity);
        ButterKnife.bind(this);
        setTitle("抄表记录");
        adapter = new CBRecordAdapter(mList, this);
        listItem.setAdapter(adapter);
        request();
    }

    private void request() {
        app = (MyApplication) getApplication();
        OkHttpUtils
                .get()
                .url(ServerConfig.CB_RECORD_URL)
                .addParams("loginName", app.getUsername())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        cbRecordBean = JsonMananger.jsonToBean(response, CBRecordBean.class);
                        mList.addAll(cbRecordBean.getRecord());
                    }
                });
    }
}
