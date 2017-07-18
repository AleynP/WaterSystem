package com.souhou.watersystem.ui.activity.MsgActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.NewsList;
import com.souhou.watersystem.bean.Result;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.ui.adapter.NewsAdapter;
import com.souhou.watersystem.utils.JsonMananger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class NewsActivity extends AppCompatActivity {

    @BindView(R.id.list_news)
    ListView listNews;
    private List<NewsList.InstallationBean> mList = new ArrayList<>();
    private NewsList newsList;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        Respons();
        newsAdapter = new NewsAdapter(this, mList);
        listNews.setAdapter(newsAdapter);
    }

    public void Respons() {
        Result result = new Result();
        OkHttpUtils
                .get()
                .url(ServerConfig.USER_INSTA_URL)
                .addParams("loginName", "admin_zz")
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
