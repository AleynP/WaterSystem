package com.souhou.watersystem.ui.activity.MsgActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.NewsList;
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

public class NewsActivity extends AppCompatActivity {

    @BindView(R.id.list_news)
    ListView listNews;
    private List<NewsList.InstallationBean> mList = new ArrayList<>();
    private NewsList newsList;
    private MyApplication app;
    private NewsAdapter newsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        app = (MyApplication) getApplication();
        String name = app.getUsername();
        Respons(name);
        newsAdapter = new NewsAdapter(this, mList);
        listNews.setAdapter(newsAdapter);
        listNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id = newsList.getInstallation().get(i).getId()+"";
                Intent intent = new Intent();
                intent.setClass(NewsActivity.this,UserdetailsActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
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
                    }
                });
    }


}
