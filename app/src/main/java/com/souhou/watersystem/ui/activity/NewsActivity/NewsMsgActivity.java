package com.souhou.watersystem.ui.activity.NewsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.NewsList;
import com.souhou.watersystem.common.BaseBackActivity;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.ui.MyApplication;
import com.souhou.watersystem.ui.adapter.NewsAdapter;
import com.souhou.watersystem.utils.JsonMananger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class NewsMsgActivity extends BaseBackActivity {

    @BindView(R.id.list_news)
    ListView listNews;
    @BindView(R.id.fail_text)
    TextView failText;
    private List<NewsList.InstallationBean> mList = new ArrayList<>();
    private NewsList newsList;
    private MyApplication app;
    private NewsAdapter newsAdapter;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        setTitle("新装消息");
        app = (MyApplication) getApplication();
        name = app.getUsername();
//        Respons(name);
        newsAdapter = new NewsAdapter(this, mList);
        listNews.setAdapter(newsAdapter);
        listNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id = newsList.getInstallation().get(i).getId() + "";
                Intent intent = new Intent();
                intent.setClass(NewsMsgActivity.this, UserdetailsActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Respons(name);
        newsAdapter.notifyDataSetChanged();
    }

    public void Respons(String name) {
        OkHttpUtils
                .get()
                .url(ServerConfig.USER_INSTA_URL)
                .addParams("loginName", name)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        newsList = JsonMananger.jsonToBean(response, NewsList.class);
                        mList.addAll(newsList.getInstallation());
                        if (mList.size() > 0) {
                            failText.setVisibility(View.GONE);
                            newsAdapter.notifyDataSetChanged();
                        }
                        Intent intent = new Intent();
                        intent.putExtra("size", mList.size());
                        setResult(RESULT_OK, intent);
                    }
                });
    }


}
