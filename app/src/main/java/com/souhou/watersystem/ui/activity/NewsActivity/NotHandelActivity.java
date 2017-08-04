package com.souhou.watersystem.ui.activity.NewsActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.NotHandelBean;
import com.souhou.watersystem.common.BaseBackActivity;
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

public class NotHandelActivity extends BaseBackActivity {

    @BindView(R.id.list_not_handel)
    ListView listNotHandel;
    @BindView(R.id.fail_text)
    TextView failText;
    private List<NotHandelBean.WeichuLiBaoZhuangBean> mList = new ArrayList<>();
    private NotHandelAdapter adapter;
    private NotHandelBean weichuli;
    MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_handel);
        ButterKnife.bind(this);
        setTitle("未处理");
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
                        if (mList.size() > 0) {
                            failText.setVisibility(View.GONE);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}
