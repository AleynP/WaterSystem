package com.souhou.watersystem.ui.activity.NewsActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.YesHandelBean;
import com.souhou.watersystem.common.BaseBackActivity;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.ui.MyApplication;
import com.souhou.watersystem.ui.adapter.YesHandelAdapter;
import com.souhou.watersystem.utils.JsonMananger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class YesHandelActivity extends BaseBackActivity {

    @BindView(R.id.list_yes_handel)
    ListView listYesHandel;
    @BindView(R.id.fail_text)
    TextView failText;
    private List<YesHandelBean.YiChuLiBaoZhuangBean> mList = new ArrayList<>();
    private YesHandelAdapter adapter;
    private YesHandelBean weichuli;
    MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yes_handel);
        ButterKnife.bind(this);
        setTitle("已处理");
        btn_right.setVisibility(View.INVISIBLE);
        btn_left.setBackgroundResource(R.drawable.ic_back);
        respons();
        adapter = new YesHandelAdapter(this, mList);
        listYesHandel.setAdapter(adapter);
        listYesHandel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("id", mList.get(i).getId() + "");
                startActivity(YesHandelDetilsActivity.class, bundle);
            }
        });
    }

    private void respons() {
        app = (MyApplication) getApplication();
        OkHttpUtils
                .get()
                .url(ServerConfig.BAOZHUANG_URL)
                .addParams("state", "1")
                .addParams("loginName", app.getUsername())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        weichuli = JsonMananger.jsonToBean(response, YesHandelBean.class);
                        mList.addAll(weichuli.getYiChuLiBaoZhuang());
                        if (mList.size() > 0) {
                            failText.setVisibility(View.GONE);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}
