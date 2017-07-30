package com.souhou.watersystem.ui.activity.MsgActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.RepairBean;
import com.souhou.watersystem.common.BaseActivity;
import com.souhou.watersystem.common.BaseBackActivity;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.ui.MyApplication;
import com.souhou.watersystem.ui.activity.FaultActivity.FaultDataActivity;
import com.souhou.watersystem.ui.adapter.FaultMesAdapter;
import com.souhou.watersystem.utils.JsonMananger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class FaultActivity extends BaseBackActivity {

    @BindView(R.id.list_repair_msg)
    ListView listview;
    private FaultMesAdapter adapter;
    private List<RepairBean.RepairsBean> mList = new ArrayList<>();
    private RepairBean repairsBean;
    MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fault);
        ButterKnife.bind(this);
        setTitle("故障消息");
        respones();
        adapter = new FaultMesAdapter(mList, this);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id = repairsBean.getRepairs().get(i).getId() + "";
                Intent intent = new Intent();
                intent.setClass(FaultActivity.this, FaultDataActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    private void respones() {
        app = (MyApplication) getApplication();
        OkHttpUtils
                .get()
                .url(ServerConfig.BX_MES_URL)
                .addParams("loginName", app.getUsername())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        repairsBean = JsonMananger.jsonToBean(response, RepairBean.class);
                        mList.addAll(repairsBean.getRepairs());
                    }
                });

    }

}
