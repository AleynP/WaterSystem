package com.souhou.watersystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.souhou.watersystem.bean.Result;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.ui.MyApplication;
import com.souhou.watersystem.ui.activity.HomeActivity;
import com.souhou.watersystem.utils.JsonMananger;
import com.souhou.watersystem.utils.SnackBar;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class LoginActivity extends AppCompatActivity implements Serializable {

    @BindView(R.id.ddk)
    ProgressBar ddk;
    @BindView(R.id.Login_name)
    EditText LoginName;
    @BindView(R.id.Login_pwd)
    EditText LoginPwd;
    @BindView(R.id.cb_rmb_pwd)
    CheckBox cbRmbPwd;
    private SharedPreferences sp;
    private Result result;
    private MyApplication app;
    private List<Result.data> mList = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        ddk.setVisibility(View.GONE);
        app = (MyApplication) getApplication();
        sp = this.getSharedPreferences("UserInfolist", Context.MODE_WORLD_READABLE);
        if (sp.getBoolean("ISCHECK", false)) {
            //设置默认是记录密码状态
            cbRmbPwd.setChecked(true);
            LoginName.setText(sp.getString("USER_NAME", ""));
            LoginPwd.setText(sp.getString("PASSWORD", ""));
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                Login();
                ddk.setVisibility(View.VISIBLE);
                break;
            case R.id.forgot_pwd:
                break;
            case R.id.cb_rmb_pwd:
                if (cbRmbPwd.isChecked()) {

                    sp.edit().putBoolean("ISCHECK", true).commit();
                } else {
                    sp.edit().putBoolean("ISCHECK", false).commit();
                }
                break;
        }
    }

    private void Login() {
        String name = LoginName.getText().toString();
        String pwd = LoginPwd.getText().toString();

        if (name != null && pwd != null) {
//            EventBus.getDefault().post(new LoginName(name));
            if (cbRmbPwd.isChecked()) {
                //记住用户名、密码、
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("USER_NAME", name);
                editor.putString("PASSWORD", pwd);
                editor.commit();
            }
            request(name, pwd);
        } else {
            SnackBar.make(LoginName, "密码或账号不能为空");
        }
    }

    private void request(final String name, final String pwd) {
        OkHttpUtils
                .get()
                .url(ServerConfig.LOGIN_URL)
                .addParams("username", name)
                .addParams("password", pwd)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ddk.setVisibility(View.GONE);
                        SnackBar.make(LoginPwd, "服务器异常登录失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        app.setUsername(name);
                        result = JsonMananger.jsonToBean(response, Result.class);
                        if (result.getLoginResult().equals("ERROR")) {
                            SnackBar.make(LoginName, result.getMsg());
                        } else if (result.getLoginResult().equals("SUCCESS")) {
                            mList.addAll(result.getType());
                            LoginJudge((ArrayList<Result.data>) mList);
                        }

                    }
                });
    }

    public void LoginJudge(ArrayList<Result.data> arrayList) {
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, HomeActivity.class);
        Bundle bundle = new Bundle();
        //传递name参数为tinyphp
        bundle.putSerializable("type", arrayList);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mList.clear();
        finish();
    }
}

