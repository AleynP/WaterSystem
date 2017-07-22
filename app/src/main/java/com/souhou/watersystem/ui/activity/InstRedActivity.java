package com.souhou.watersystem.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.InsRedBean;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.ui.MyApplication;
import com.souhou.watersystem.ui.adapter.InstRedAdapter;
import com.souhou.watersystem.utils.JsonMananger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class InstRedActivity extends AppCompatActivity {

    @BindView(R.id.list_inst_red)
    ListView listInstRed;
    private List<InsRedBean.ZhuangBiaoJiLuBean> mList = new ArrayList<>();
    private InstRedAdapter adapter;
    private InsRedBean insRedBean;
    MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inst_red);
        ButterKnife.bind(this);
        response();
        adapter = new InstRedAdapter(mList, this);
        listInstRed.setAdapter(adapter);
    }

    private void response() {
        app = (MyApplication) getApplication();
        OkHttpUtils
                .get()
                .url(ServerConfig.AZ_JL_URL)
                .addParams("state", "1")
                .addParams("loginName", app.getUsername())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        insRedBean = JsonMananger.jsonToBean(response, InsRedBean.class);
                        mList.addAll(insRedBean.getZhuangBiaoJiLu());
                    }
                });
    }

}
