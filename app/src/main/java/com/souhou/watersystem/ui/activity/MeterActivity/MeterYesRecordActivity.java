package com.souhou.watersystem.ui.activity.MeterActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.CBMeterBean;
import com.souhou.watersystem.common.BaseBackActivity;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.ui.adapter.CBYesMeterAdapter;
import com.souhou.watersystem.utils.JsonMananger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class MeterYesRecordActivity extends BaseBackActivity {

    @BindView(R.id.list_item2)
    ListView listview;
    @BindView(R.id.fail_text)
    TextView failText;
    private CBMeterBean cbMeterBean;
    private CBYesMeterAdapter adapter;
    private List<CBMeterBean.BenYueYiChaoBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meter_yes_record);
        ButterKnife.bind(this);
        setTitle("本月已抄");
        request();
        adapter = new CBYesMeterAdapter(mList, this);
        listview.setAdapter(adapter);
    }

    private void request() {
        OkHttpUtils
                .get()
                .url(ServerConfig.CB_Meter_URL)
                .addParams("str", "1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        cbMeterBean = JsonMananger.jsonToBean(response, CBMeterBean.class);
                        mList.addAll(cbMeterBean.getBenYueYiChao());
                        if (mList.size() > 0) {
                            failText.setVisibility(View.GONE);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}
