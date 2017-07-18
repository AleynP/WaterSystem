package com.souhou.watersystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.bean.Result;
import com.souhou.watersystem.ui.activity.HomeActivity;
import com.souhou.watersystem.utils.JsonMananger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class LoginActivity extends AppCompatActivity implements Serializable{

    private EditText Login_name, Login_pwd;
    private CheckBox cb_rem_pwd;
    private SharedPreferences sp;
    private Result result;
    private List<Result.data> mList =new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Login_name = (EditText) findViewById(R.id.Login_name);
        Login_pwd = (EditText) findViewById(R.id.Login_pwd);
        cb_rem_pwd = (CheckBox) findViewById(R.id.cb_rmb_pwd);
        sp = this.getSharedPreferences("UserInfo", Context.MODE_WORLD_READABLE);
        if (sp.getBoolean("ISCHECK", false)) {
            //设置默认是记录密码状态
            cb_rem_pwd.setChecked(true);
            Login_name.setText(sp.getString("USER_NAME", ""));
            Login_pwd.setText(sp.getString("PASSWORD", ""));
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                Login();
               /* Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.setClass(LoginActivity.this, HomeActivity.class);
                startActivity(intent);*/
                break;
            case R.id.forgot_pwd:
                break;
            case R.id.cb_rmb_pwd:
                if (cb_rem_pwd.isChecked()) {

                    sp.edit().putBoolean("ISCHECK", true).commit();
                } else {
                    sp.edit().putBoolean("ISCHECK", false).commit();
                }
                break;
        }
    }

    private void Login() {
        String name = Login_name.getText().toString();
        String pwd = Login_pwd.getText().toString();
        if (name != null && pwd != null) {
            if (cb_rem_pwd.isChecked()) {
                //记住用户名、密码、
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("USER_NAME", name);
                editor.putString("PASSWORD", pwd);
                editor.commit();
            }
            request(name, pwd);
        } else {
            Snackbar.make(Login_name, "密码或账号不能为空", Snackbar.LENGTH_SHORT).show();
        }
    }

    private void request(String name, String pwd) {
        OkHttpUtils
                .get()
                .url(ServerConfig.LOGIN_URL)
                .addParams("username", name)
                .addParams("password", pwd)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Snackbar.make(Login_pwd, "服务器异常登录失败" + e.getMessage(), Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        result = JsonMananger.jsonToBean(response, Result.class);
                        if (result.getLoginResult().equals("ERROR")) {
                            Snackbar.make(Login_name, result.getMsg(), Snackbar.LENGTH_SHORT).show();
                        } else if (result.getLoginResult().equals("SUCCESS")) {
                            mList.addAll(result.getType());
                            LoginJudge((ArrayList<Result.data>) mList);
                            Snackbar.make(Login_name, result.getMsg(), Snackbar.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    public void LoginJudge(ArrayList<Result.data> arrayList){
       /* int a = 0, b = 0, c = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getGNMC().equals("APP抄表信息")) {
                a = 1;
            }
            if (arrayList.get(i).getGNMC().equals("APP报修处理")) {
                b = 1;
            }
            if (arrayList.get(i).getGNMC().equals("APP报装处理")) {
                c = 1;
            }
        }*/
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, HomeActivity.class);
        Bundle bundle=new Bundle();
        //传递name参数为tinyphp
        bundle.putSerializable("type",arrayList);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}

