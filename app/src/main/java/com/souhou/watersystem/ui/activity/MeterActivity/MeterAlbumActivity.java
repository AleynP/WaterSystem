package com.souhou.watersystem.ui.activity.MeterActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.CBMeterBookBean;
import com.souhou.watersystem.common.BaseBackActivity;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.ui.MyApplication;
import com.souhou.watersystem.ui.adapter.CBMeterBookAdapter;
import com.souhou.watersystem.utils.JsonMananger;
import com.souhou.watersystem.utils.LoadingDialog;
import com.souhou.watersystem.utils.Toasts;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class MeterAlbumActivity extends BaseBackActivity {
    @BindView(R.id.list_item)
    ListView listItem;
    @BindView(R.id.bc_text)
    TextView bcText;
    private String name;
    private List<CBMeterBookBean.MeterBookListBean> mList = new ArrayList<>();
    private CBMeterBookBean cbMeterBookBean;
    private CBMeterBookAdapter adapter;
    MyApplication app;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meter_album);
        ButterKnife.bind(this);
        setTitle("表册分类");
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initData() {
        intent = getIntent();
        app = (MyApplication) getApplication();
        name = app.getUsername();
        resquest();
        adapter = new CBMeterBookAdapter(mList, this);
        listItem.setAdapter(adapter);
        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id = cbMeterBookBean.getMeterBookList().get(i).getId() + "";
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                bundle.putString("str", intent.getStringExtra("str"));
                startActivity(MeterNotRecordActivity.class, bundle);
            }
        });
    }

    public void resquest() {
        LoadingDialog.createLoadingDialog(this, "");
        OkHttpUtils
                .get()
                .url(ServerConfig.CB_Meter_BOOK_URL)
                .addParams("loginName", name)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LoadingDialog.closeDialog();
                        Toasts.setText(MeterAlbumActivity.this, "请求错误" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LoadingDialog.closeDialog();
                        cbMeterBookBean = JsonMananger.jsonToBean(response, CBMeterBookBean.class);
                        mList.addAll(cbMeterBookBean.getMeterBookList());
                        adapter.notifyDataSetChanged();
                        if (mList.isEmpty()) {
                            bcText.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
