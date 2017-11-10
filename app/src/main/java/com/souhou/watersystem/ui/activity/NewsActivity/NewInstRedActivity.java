package com.souhou.watersystem.ui.activity.NewsActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.InsRedBean;
import com.souhou.watersystem.common.BaseBackActivity;
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
import okhttp3.Call;

public class NewInstRedActivity extends BaseBackActivity {

    @BindView(R.id.list_inst_red)
    ListView listInstRed;
    @BindView(R.id.fail_text)
    TextView failText;
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
        setTitle("新装记录");
        adapter = new InstRedAdapter(mList, this);
        listInstRed.setAdapter(adapter);
        listInstRed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("id", mList.get(i).getId() + "");
                startActivity(YesHandelDetilsActivity.class, bundle);
            }
        });
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
                        if (mList.size() > 0) {
                            failText.setVisibility(View.GONE);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

}
