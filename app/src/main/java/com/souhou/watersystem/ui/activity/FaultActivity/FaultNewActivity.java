package com.souhou.watersystem.ui.activity.FaultActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.BXRepairBean;
import com.souhou.watersystem.common.BaseBackActivity;
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

public class FaultNewActivity extends BaseBackActivity {

    @BindView(R.id.list_new_fault)
    ListView listNewFault;
    @BindView(R.id.fail_text)
    TextView failText;
    private BXRecordAdapter adapter;
    private BXRepairBean bxRepairBean;
    private List<BXRepairBean.BaoXiuJiLuBean> mList = new ArrayList<>();
    MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fault_new);
        ButterKnife.bind(this);
        setTitle("新增故障处理");
        request();
        adapter = new BXRecordAdapter(mList, this);
        listNewFault.setAdapter(adapter);
        listNewFault.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id = bxRepairBean.getBaoXiuJiLu().get(i).getId() + "";
                String ID = bxRepairBean.getBaoXiuJiLu().get(i).getProcess_ID() + "";
                Intent intent = new Intent();
                intent.setClass(FaultNewActivity.this, FaultSubNewActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("ID", ID);
                startActivity(intent);
            }
        });
    }

    private void request() {
        app = (MyApplication) getApplication();
        OkHttpUtils
                .get()
                .url(ServerConfig.BX_RECORD_URL)
                .addParams("loginName", app.getUsername())
                .addParams("state", "0")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        bxRepairBean = JsonMananger.jsonToBean(response, BXRepairBean.class);
                        mList.addAll(bxRepairBean.getBaoXiuJiLu());
                        if (mList.size() > 0) {
                            failText.setVisibility(View.GONE);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}
