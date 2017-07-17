package com.souhou.watersystem.ui.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.UserInfo;
import com.souhou.watersystem.common.BaseActivity;
import com.souhou.watersystem.ui.adapter.UserAdapter;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class UserActivity extends BaseActivity {

    private ListView listView;
    private UserAdapter userAdapter;
    private List<UserInfo> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("用户资料");
        setContentView(R.layout.activity_user);
        listView = (ListView) findViewById(R.id.list_user);
        Okhttp();
        userAdapter = new UserAdapter(mList, this);
        listView.setAdapter(userAdapter);
    }

    private void Okhttp() {
        OkHttpUtils
                .get()
                .addParams("User_Name","12")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });
    }

}
