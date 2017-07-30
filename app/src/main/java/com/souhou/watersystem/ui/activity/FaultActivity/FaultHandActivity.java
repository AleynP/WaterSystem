package com.souhou.watersystem.ui.activity.FaultActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.BXHandelBean;
import com.souhou.watersystem.common.BaseActivity;
import com.souhou.watersystem.common.BaseBackActivity;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.ui.MyApplication;
import com.souhou.watersystem.ui.adapter.BxHandelAdapter;
import com.souhou.watersystem.utils.JsonMananger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class FaultHandActivity extends BaseBackActivity {

    @BindView(R.id.list_bx_handel)
    ListView listBxHandel;
    private BxHandelAdapter adapter;
    private List<BXHandelBean.YiChuLiBaoXiuBean> mList = new ArrayList<>();
    private BXHandelBean bxHandelBean;
    MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fault_hand);
        ButterKnife.bind(this);
        setTitle("已处理处理");
        Response();
        adapter = new BxHandelAdapter(mList, this);
        listBxHandel.setAdapter(adapter);
    }

    private void Response() {
        app = (MyApplication) getApplication();
        OkHttpUtils
                .get()
                .url(ServerConfig.BX_HANDEL_URL)
                .addParams("loginName", app.getUsername())
                .addParams("state", "1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        bxHandelBean = JsonMananger.jsonToBean(response, BXHandelBean.class);
                        mList.addAll(bxHandelBean.getYiChuLiBaoXiu());
                    }
                });
    }
}
