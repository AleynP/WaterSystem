package com.souhou.watersystem.ui.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.souhou.watersystem.R;
import com.souhou.watersystem.bean.UserInfo;
import com.souhou.watersystem.common.BaseActivity;
import com.souhou.watersystem.common.ServerConfig;
import com.souhou.watersystem.utils.JsonMananger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class UserActivity extends BaseActivity {


    @BindView(R.id.name_search)
    EditText nameSearch;
    @BindView(R.id.bt_search)
    Button btSearch;
    @BindView(R.id.tv_name2)
    TextView tvName2;
    @BindView(R.id.tv_number2)
    TextView tvNumber2;
    @BindView(R.id.tv_peopel2)
    TextView tvPeopel2;
    @BindView(R.id.tv_address2)
    TextView tvAddress2;
    @BindView(R.id.tv_time2)
    TextView tvTime2;
    @BindView(R.id.tv_water_num2)
    TextView tvWaterNum2;
    @BindView(R.id.tv_az_time2)
    TextView tvAzTime2;
    @BindView(R.id.tv_state2)
    TextView tvState2;
    @BindView(R.id.tv_modle2)
    TextView tvModle2;
    private UserInfo user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("用户资料");
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = nameSearch.getText().toString();
                if (num.equals("")) {
                    Snackbar.make(btSearch, "请输入手机号或用户编号!", Snackbar.LENGTH_SHORT).show();
                } else {
                    Okhttp(num);
                }
            }
        });

    }

    private void Okhttp(String num) {
        OkHttpUtils
                .get()
                .url(ServerConfig.USER_URL)
                .addParams("strWhere", num)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Snackbar.make(btSearch, "请求失败", Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        user = JsonMananger.jsonToBean(response, UserInfo.class);
                        if (user.getUserInfo().getUser_Number() != 0) {
                            initText(user);
                        } else {
                            Snackbar.make(btSearch, "请核对输入信息是否正确", Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void initText(UserInfo user) {
        tvName2.setText(user.getUserInfo().getUser_Name());
        tvNumber2.setText(user.getUserInfo().getUser_Number() + "");
        tvPeopel2.setText(user.getUserInfo().getUser_Quantity() + "");
        tvAddress2.setText(user.getUserInfo().getUser_Site());
        tvTime2.setText(user.getUserInfo().getUser_Time() + "");
        tvState2.setText(user.getUserInfo().getWaterMeter_State() + "");
        tvWaterNum2.setText(user.getUserInfo().getWaterMeter_Number());
        tvAzTime2.setText((String) user.getUserInfo().getWaterMeter_Time());
        tvModle2.setText(user.getUserInfo().getWaterType_Name());
    }

}
