package com.souhou.watersystem.ui.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.NotHandelBean;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.ui.MyApplication;
import com.souhou.watersystem.ui.adapter.NotHandelAdapter;
import com.souhou.watersystem.utils.JsonMananger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class NotHandelActivity extends AppCompatActivity {

    @BindView(R.id.list_not_handel)
    ListView listNotHandel;
    private List<NotHandelBean.WeichuLiBaoZhuangBean> mList = new ArrayList<>();
    private NotHandelAdapter adapter;
    private NotHandelBean weichuli;
    MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_handel);
        ButterKnife.bind(this);
        respons();
        adapter = new NotHandelAdapter(this, mList);
        listNotHandel.setAdapter(adapter);
    }

    private void respons() {
        app = (MyApplication) getApplication();
        OkHttpUtils
                .get()
                .url(ServerConfig.BAOZHUANG_URL)
                .addParams("state", "0")
                .addParams("loginName", app.getUsername())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        weichuli = JsonMananger.jsonToBean(response, NotHandelBean.class);
                        mList.addAll(weichuli.getWeichuLiBaoZhuang());
                    }
                });
    }
}
