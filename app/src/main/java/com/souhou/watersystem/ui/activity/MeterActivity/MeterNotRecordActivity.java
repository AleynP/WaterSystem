package com.souhou.watersystem.ui.activity.MeterActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.CBMeterBean;
import com.souhou.watersystem.common.BaseBackActivity;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.ui.adapter.CBNotMeterAdapter;
import com.souhou.watersystem.utils.JsonMananger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class MeterNotRecordActivity extends BaseBackActivity {

    @BindView(R.id.list_item)
    ListView listview;
    @BindView(R.id.fail_text)
    TextView failText;
    private CBMeterBean cbMeterBean;
    private CBNotMeterAdapter adapter;
    private String id;
    private List<CBMeterBean.BenYueWeiChaoBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meter_not_record);
        ButterKnife.bind(this);
        setTitle("本月未抄");
        request();
        adapter = new CBNotMeterAdapter(mList, this);
        listview.setAdapter(adapter);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
    }

    private void request() {
        OkHttpUtils
                .get()
                .url(ServerConfig.CB_Meter_URL)
                .addParams("str", "0")
                .addParams("bcID", id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        cbMeterBean = JsonMananger.jsonToBean(response, CBMeterBean.class);
                        mList.addAll(cbMeterBean.getBenYueWeiChao());
                        if (mList.size() > 0) {
                            failText.setVisibility(View.GONE);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}
