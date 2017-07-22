package com.souhou.watersystem.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.InsRedBean;
import com.souhou.watersystem.bean.NewAddBean;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.ui.MyApplication;
import com.souhou.watersystem.ui.adapter.NewAddAdapter;
import com.souhou.watersystem.utils.JsonMananger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class NewAddActivity extends AppCompatActivity {
    @BindView(R.id.list_new_add)
    ListView listNewAdd;
    private List<NewAddBean.XinZengBaoZhuangBean> mList = new ArrayList<>();
    private NewAddBean newAddBean;
    private NewAddAdapter newAddAdapter;
    MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_add);
        ButterKnife.bind(this);
        response();
        newAddAdapter = new NewAddAdapter(mList, this);
        listNewAdd.setAdapter(newAddAdapter);
    }

    private void response() {
        app = (MyApplication) getApplication();
        OkHttpUtils
                .get()
                .url(ServerConfig.AZ_JL_URL)
                .addParams("state", "0")
                .addParams("loginName", app.getUsername())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        newAddBean = JsonMananger.jsonToBean(response, NewAddBean.class);
                        mList.addAll(newAddBean.getXinZengBaoZhuang());
                    }
                });
    }
}
