package com.souhou.watersystem.ui.activity.MeterActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.CBMeterBean;
import com.souhou.watersystem.common.BaseBackActivity;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.ui.adapter.CBNotMeterAdapter;
import com.souhou.watersystem.utils.JsonMananger;
import com.souhou.watersystem.utils.LoadingDialog;
import com.souhou.watersystem.utils.LogUtils;
import com.souhou.watersystem.utils.Toasts;
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
    private CBNotMeterAdapter adapter;
    private String id, str;
    private List<CBMeterBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meter_not_record);
        ButterKnife.bind(this);
        initDate();
    }

    private void initDate() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        str = intent.getStringExtra("str");
        if (str.equals("0")) {
            setTitle("本月未抄");
        } else if (str.equals("1")) {
            setTitle("本月已抄");
        }
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("water_num", mList.get(i).getWaterMeter_Number() + "");
                if (str.equals("0")) {
                    startActivity(MeterSubActivity.class, bundle);
                } else if (str.equals("1")) {
                    startActivity(MeterYesDetilesActivity.class, bundle);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        request();
    }

    private void request() {
        LoadingDialog.createLoadingDialog(this, "正在加载...");
        OkHttpUtils
                .get()
                .url(ServerConfig.CB_Meter_URL)
                .addParams("str", str)
                .addParams("bcID", id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LoadingDialog.closeDialog();
                        Toasts.setText(MeterNotRecordActivity.this, "请求失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LoadingDialog.closeDialog();
                        LogUtils.d(response.toString());
                        mList = JsonMananger.jsonToList(response, CBMeterBean.class);
                        if (mList.isEmpty()) {
                            failText.setVisibility(View.VISIBLE);
                        } else {
                            adapter = new CBNotMeterAdapter(mList, MeterNotRecordActivity.this);
                            listview.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mList.clear();
    }
}
