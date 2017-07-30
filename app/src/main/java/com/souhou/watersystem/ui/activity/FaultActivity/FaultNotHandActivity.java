package com.souhou.watersystem.ui.activity.FaultActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.BXNotHandelBean;
import com.souhou.watersystem.common.BaseActivity;
import com.souhou.watersystem.common.BaseBackActivity;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.ui.MyApplication;
import com.souhou.watersystem.ui.adapter.BXNOTHandelAdapter;
import com.souhou.watersystem.utils.JsonMananger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class FaultNotHandActivity extends BaseBackActivity {

    @BindView(R.id.list_not_handel)
    ListView listNotHandel;
    private List<BXNotHandelBean.WeiChuLiBaoXiuBean> mList = new ArrayList<>();
    private BXNotHandelBean bxNotHandelBean;
    private BXNOTHandelAdapter adapter;
    private MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fauit_not_hand);
        ButterKnife.bind(this);
        setTitle("未处理");
        request();
        adapter = new BXNOTHandelAdapter(mList, this);
        listNotHandel.setAdapter(adapter);
    }

    private void request() {
        app = (MyApplication) getApplication();
        OkHttpUtils
                .get()
                .url(ServerConfig.BX_HANDEL_URL)
                .addParams("loginName", app.getUsername())
                .addParams("state", "0")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        bxNotHandelBean = JsonMananger.jsonToBean(response, BXNotHandelBean.class);
                        mList.addAll(bxNotHandelBean.getWeiChuLiBaoXiu());
                    }
                });
    }
}
